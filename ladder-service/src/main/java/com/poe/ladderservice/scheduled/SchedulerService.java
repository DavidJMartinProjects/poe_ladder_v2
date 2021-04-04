package com.poe.ladderservice.scheduled;

import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poe.ladderservice.db.LadderDao;
import com.poe.ladderservice.db.LeaderboardDao;
import com.poe.ladderservice.db.LeaderboardRepository;
import com.poe.ladderservice.db.LeagueDao;
import com.poe.ladderservice.domain.RankMapper;
import com.poe.ladderservice.domain.LeagueMapper;
import com.poe.ladderservice.domain.entity.RankEntity;
import com.poe.ladderservice.domain.enums.LadderTypes;
import com.poe.ladderservice.domain.pojo.ladder.Entry;
import com.poe.ladderservice.domain.pojo.ladder.LadderResponse;
import com.poe.ladderservice.domain.pojo.league.LeagueDto;
import com.poe.ladderservice.scheduled.comparison.RankCalc;
import com.poe.ladderservice.scheduled.urls.UrlsBuilder;
import com.poe.ladderservice.scheduled.facade.RestTemplateFacade;
import com.poe.ladderservice.scheduled.facade.httpEntity.HttpEntityBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SchedulerService {

    public static final String POE_API_LEAGUES_URL = "https://api.pathofexile.com/leagues";

    private List<RankEntity> ranks = new ArrayList<>();

    @Autowired
    private LeagueDao leagueDao;

    @Autowired
    private UrlsBuilder urlBuilder;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    LadderDao ladderDao;

    @Autowired
    private LeaderboardDao leaderboardDao;

    @Autowired
    LeaderboardRepository leaderboardRepository;

    @Autowired
    private RestTemplateFacade restTemplateFacade;

    @Autowired
    RankMapper rankMapper;

    @Autowired
    LeagueMapper leagueMapper;

    @Autowired
    HttpEntityBuilder httpEntityBuilder;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RankCalc rankCalc;

    private List<RankEntity> oldRanks = new ArrayList<>();

    public void persistLatestLeagues() {
        leagueDao.deleteAll();
        leagueDao.saveAll(getLeagues());
    }

    public List<LeagueDto> getLeagues() {
        List<LeagueDto> leaguesDtos = new ArrayList<>();
        log.info("requesting current active leagues.");
        String responseBody = restTemplateFacade.getForString(POE_API_LEAGUES_URL).getBody();
        try {
            leaguesDtos = objectMapper.readValue(responseBody, new TypeReference<List<LeagueDto>>() {});
        } catch (JsonProcessingException ex) {
            log.info("encountered json processing error. {}", ex.getMessage());
        }
        return leaguesDtos;
    }

    public void persistLatestLadders() {
        log.info("fetching latest ladder rankings from pathofexile.com");
        List<RankEntity> newRanks = new ArrayList<>();
        for (Map<String, String> urlsList : urlBuilder.getUrls()) {
            for (Map.Entry<String, String> leagueUrl : urlsList.entrySet()) {
                List<Entry> apiResponseList = getLadderFromPoeApi(leagueUrl.getValue());
                newRanks.addAll(
                    mapResponseToRanks(apiResponseList, leagueUrl.getValue(), leagueUrl.getKey(), String.valueOf(Time.from(Instant.now())))
                );
            }
        }
        if(!oldRanks.isEmpty()) {
            newRanks = rankCalc.calcRankings(oldRanks, newRanks);
        }
        oldRanks = newRanks;
        persistRanksToDb(newRanks);
    }

    private void persistRanksToDb(List<RankEntity> ranks) {
        leaderboardRepository.deleteAll();
        for(RankEntity entity: ranks) {
            leaderboardRepository.save(entity);
        }
        log.info("saved ranks to db.");
    }

    public List<RankEntity> mapResponseToRanks(List<Entry> apiResponseList, String requestUrl, String leagueName, String timestamp) {
        log.debug("mapping api response to leaderboard entity");
        ranks.clear();
        LadderTypes types = resolveLadderType(requestUrl, leagueName);
        for (Entry responseEntry : apiResponseList) {
            RankEntity rank = rankMapper.toRankEntity(leagueName, types, responseEntry, timestamp);
            ranks.add(rank);
        }
        return ranks;
    }

    private LadderTypes resolveLadderType(String url, String leagueName) {
        if (url.contains(String.format(urlBuilder.buildDelveUrls(leagueName)))) {
            return LadderTypes.DELVE;
        } else if (url.contains(urlBuilder.buildUberLabUrls(leagueName))) {
            return LadderTypes.UBERLAB;
        } else if (url.contains(urlBuilder.buildRaceTo100Urls(leagueName))) {
            return LadderTypes.RACETO100;
        }
        return LadderTypes.UNKNOWN;
    }

    public List<Entry> getLadderFromPoeApi(String url)  {
        log.debug("making http request to url: {}", url);
        List<Entry> responseEntriesList = new ArrayList<>();
        try {
            ResponseEntity<LadderResponse> response = restTemplate.exchange(url, HttpMethod.GET, httpEntityBuilder.getConfiguredHttpEntity(), LadderResponse.class);
            log.debug("response: {}, status code: {}", response.getBody(), response.getStatusCode());
            responseEntriesList = response.getBody().getEntries();
        } catch (Exception ex) {
            log.info("poe api response error: {}, {}", ex.getMessage(), ex.getCause());
        } finally {
            sleepBeforeNextApiRequest();
        }
        return responseEntriesList;
    }

    private void sleepBeforeNextApiRequest() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            throw new RuntimeException("encountered an InterruptedException while sleeping. " + ex);
        }
    }

}