package com.poe.ladderservice.scheduled;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
import com.poe.ladderservice.domain.LeaderboardMapper;
import com.poe.ladderservice.domain.LeagueMapper;
import com.poe.ladderservice.domain.entity.LeaderBoardEntity;
import com.poe.ladderservice.domain.enums.LadderTypes;
import com.poe.ladderservice.domain.pojo.ladder.Entry;
import com.poe.ladderservice.domain.pojo.ladder.Ladder;
import com.poe.ladderservice.domain.pojo.ladder.ResponseEntry;
import com.poe.ladderservice.domain.pojo.league.LeagueDto;
import com.poe.ladderservice.scheduled.config.UrlsBuilder;
import com.poe.ladderservice.scheduled.facade.RestTemplateFacade;
import com.poe.ladderservice.scheduled.facade.config.HttpEntityBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SchedulerService {

    public static final String POE_API_BASE_URL = "https://api.pathofexile.com";
    public static final String LEAGUE_LADDER_URL = POE_API_BASE_URL + "/league/%s/ladder";
    public static final String LEAGUES_URL = POE_API_BASE_URL + "/leagues";

    private List<LeaderBoardEntity> leaderBoardEntityList = new ArrayList<>();

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
    LeaderboardMapper leaderboardMapper;

    @Autowired
    LeagueMapper leagueMapper;

    @Autowired
    HttpEntityBuilder httpEntityBuilder;

    @Autowired
    RestTemplate restTemplate;

    public void persistLatestLeagues() {
        leagueDao.deleteAll();
        leagueDao.saveAll(getLeagues());
    }

    public List<LeagueDto> getLeagues() {
        List<LeagueDto> leaguesDtos = new ArrayList<>();
        log.info("requesting current active leagues.");
        String responseBody = restTemplateFacade.getForString(LEAGUES_URL).getBody();
        try {
            leaguesDtos = objectMapper.readValue(responseBody, new TypeReference<List<LeagueDto>>() {});
        } catch (JsonProcessingException ex) {
            log.info("encountered json processing error. {}", ex.getMessage());
        }
        return leaguesDtos;
    }

    public List<Ladder> getLatestLadders() {
        log.info("retrieving latest ladders from www.pathofexile.com/api");
        List<Ladder> latestLadders = new ArrayList<>();
        for (Map<String, String> urlsList : urlBuilder.getUrls()) {
            requestLadderFromUrl(latestLadders, urlsList);
        }
        return latestLadders;
    }

    private void requestLadderFromUrl(List<Ladder> latestLadders, Map<String, String> urlsList) {
        for (Map.Entry<String, String> ladderUrl : urlsList.entrySet()) {
            Ladder ladder = new Ladder();
            String responseBody = restTemplateFacade.getForString(ladderUrl.getValue()).getBody();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                ladder = objectMapper.readValue(responseBody, Ladder.class);
            } catch (JsonProcessingException | InterruptedException ex) {
                log.info("encountered json processing error. {}", ex.getMessage());
            }
            latestLadders.add(ladder);
            log.info("latestLadders.size(): {}", latestLadders.size());
        }
    }

    public void persistLatestLadders() {
        log.info("getLeaderboardRankings() : attempting to retrieve latest ladders from pathofexile.com");
        List<LeaderBoardEntity> latestLeaderboardEntities = new ArrayList<>();
        for (Map<String, String> urlsList : urlBuilder.getUrls()) {
            for (Map.Entry<String, String> leagueUrl : urlsList.entrySet()) {
                List<Entry> apiResponseList = requestLeaderboardFromPoeApi(leagueUrl.getValue());
                latestLeaderboardEntities.addAll(mapApiResponseToEntity(apiResponseList, leagueUrl.getValue(), leagueUrl.getKey(), String.valueOf(Time.from(Instant.now()))));
            }
        }
//        if(!previousLeaderboardEntities.isEmpty()) {
//            latestLeaderboardEntities = leagueComparisonService.compareLeague(previousLeaderboardEntities, latestLeaderboardEntities);
//        }
//        previousLeaderboardEntities = latestLeaderboardEntities;
        persistEntityToDb(latestLeaderboardEntities);
    }
    private void persistEntityToDb(List<LeaderBoardEntity> leaderboardEntries) {
        log.info("persistEntityToDb() : saving leaderboard results to poe-ladder database.");
        leaderboardRepository.deleteAll();
        for(LeaderBoardEntity entity: leaderboardEntries) {
            leaderboardRepository.save(entity);
        }
    }

    public List<LeaderBoardEntity> mapApiResponseToEntity(List<Entry> apiResponseList, String requestUrl, String leagueName, String timestamp) {
        log.info("mapApiResponseToEntity(): request received to map api response to leaderboard entity");
        leaderBoardEntityList.clear();
        LadderTypes leaderboardType = determineLeaderboardType(requestUrl, leagueName);
        for (Entry responseEntry : apiResponseList) {
            LeaderBoardEntity leaderboardEntity = leaderboardMapper.mapToLeaderboardEntry(leagueName, leaderboardType, responseEntry, timestamp);
            leaderBoardEntityList.add(leaderboardEntity);
        }
        return leaderBoardEntityList;
    }

    public static String getCurrentTimestamp() {
        Date date = new Date();
        Timestamp ts=new Timestamp(date.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(ts);
    }

    public static String formatSecondsToMinutes(String timeInSeconds) {
        Integer theTime = new Integer(timeInSeconds);
        int minutes = theTime/60;
        int seconds = theTime%60;
        return ""+minutes+"min "+seconds+"sec";
    }

    private LadderTypes determineLeaderboardType(String url, String leagueName) {
        if (url.contains(String.format(urlBuilder.buildDelveUrls(leagueName)))) {
            return LadderTypes.DELVE;
        } else if (url.contains(urlBuilder.buildUberLabUrls(leagueName))) {
            return LadderTypes.UBERLAB;
        } else if (url.contains(urlBuilder.buildRaceTo100Urls(leagueName))) {
            return LadderTypes.RACETO100;
        }
        return LadderTypes.UNKNOWN;
    }

    public List<Entry> requestLeaderboardFromPoeApi(String url)  {
        log.info("requestLeaderboardFromPoeApi() : httprequest to {}", url);
        List<Entry> responseEntriesList = new ArrayList<>();
        try {
            ResponseEntity<ResponseEntry> leaderboardApiRequest = restTemplate.exchange(url, HttpMethod.GET, httpEntityBuilder.getConfiguredHttpEntity(), ResponseEntry.class);
            log.info("response: {}, status code: {}", leaderboardApiRequest.getBody(), leaderboardApiRequest.getStatusCode());
            ResponseEntry leaderboardApiResponseBody = leaderboardApiRequest.getBody();
            responseEntriesList = leaderboardApiResponseBody.getEntries();
        } catch (Exception ex) {
            log.info("poe api response error: {}, {}", ex.getMessage(), ex.getCause());
        } finally {
            sleepBeforeNextApiRequest();
        }
        return responseEntriesList;
    }

    private void sleepBeforeNextApiRequest() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            throw new RuntimeException("sleepBeforeNextApiRequest() encountered an InterruptedException : " + ex);
        }
    }

}