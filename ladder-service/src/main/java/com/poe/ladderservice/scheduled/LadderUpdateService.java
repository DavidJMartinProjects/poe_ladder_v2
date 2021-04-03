package com.poe.ladderservice.scheduled;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poe.ladderservice.db.LeaderboardDao;
import com.poe.ladderservice.db.LeagueDao;
import com.poe.ladderservice.domain.pojo.ladder.LadderDto;
import com.poe.ladderservice.domain.pojo.league.LeagueDto;
import com.poe.ladderservice.scheduled.facade.RestTemplateFacade;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LadderUpdateService {

    public static final String POE_API_BASE_URL = "https://api.pathofexile.com";
    public static final String LEAGUE_LADDER_URL = POE_API_BASE_URL + "/league/%s/ladder";
    public static final String LEAGUES_URL = POE_API_BASE_URL + "/leagues";

    @Autowired
    private LeagueDao leagueDao;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LeaderboardDao leaderboardDao;

    @Autowired
    private RestTemplateFacade restTemplateFacade;

    public void fetchAndPersistLatestLeagues() {
        leagueDao.deleteAll();
        leagueDao.saveAll(getCurrentLeagues());
    }

    public List<LeagueDto> getCurrentLeagues() {
        List<LeagueDto> leaguesDtos = new ArrayList<>();
        log.info("requesting current active leagues.");
        try {
            String responseBody = restTemplateFacade.getForString(LEAGUES_URL).getBody();
            leaguesDtos = objectMapper.readValue(responseBody, new TypeReference<List<LeagueDto>>() {});
        } catch (JsonProcessingException ex) {
            log.info("encountered json processing error. {}", ex.getMessage());
        }
        return leaguesDtos;
    }

    public void fetchAndPersistLatestLeaderboards() {
        List<LeagueDto> leaguesDtos = getCurrentLeagues();
        for (LeagueDto leagueDto : leaguesDtos) {
            LadderDto ladderDto = getLadderByLeague(leagueDto.getId());
            leaderboardDao.saveAll(Collections.singletonList(ladderDto));
        }
    }

    public LadderDto getLadderByLeague(String league) {
        LadderDto ladderDto = new LadderDto();
        log.info("requesting latest ladder for league: {}", league);
        try {
            String url = String.format(LEAGUE_LADDER_URL, league);
            String responseBody = restTemplateFacade.getForString(url).getBody();
            ladderDto = objectMapper.readValue(responseBody, LadderDto.class);
        } catch (JsonProcessingException ex) {
            log.info("encountered json processing error. {}", ex.getMessage());
        }
        return ladderDto;
    }

}