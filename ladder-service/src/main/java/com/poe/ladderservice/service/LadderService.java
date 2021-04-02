package com.poe.ladderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poe.ladderservice.db.dao.LeaderboardDao;
import com.poe.ladderservice.db.entity.LeaderBoardEntity;
import com.poe.ladderservice.domain.pojo.ladder.LadderDto;
import com.poe.ladderservice.domain.pojo.league.LeagueDto;
import com.poe.ladderservice.service.facade.RestTemplateFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LadderService {

    public static final String POE_API_BASE_URL = "https://api.pathofexile.com";
    public static final String LEAGUE_LADDER_URL = POE_API_BASE_URL + "/league/%s/ladder";
    public static final String LEAGUES_URL = POE_API_BASE_URL + "/leagues";

    @Autowired
    private RestTemplateFacade restTemplateFacade;

    @Autowired
    private LeaderboardDao leaderboardDao;

    @Autowired
    private ObjectMapper objectMapper;

    public LadderDto getLadderByLeague(String league) {
        LadderDto ladderDto = new LadderDto();
        log.info("retrieving latest ladder for league: {}", league);
        try {
            String url = String.format(LEAGUE_LADDER_URL, league);
            String responseBody = restTemplateFacade.getForString(url).getBody();
            ladderDto = objectMapper.readValue(responseBody, LadderDto.class);
        } catch (JsonProcessingException ex) {
            log.info("encountered json processing error. {}", ex.getMessage());
        }
        return ladderDto;
    }

    public List<LeagueDto> getCurrentLeagues() {
        List<LeagueDto> leaguesDtos = new ArrayList<>();
        log.info("retrieving current active leagues.");
        try {
            String responseBody = restTemplateFacade.getForString(LEAGUES_URL).getBody();
            leaguesDtos = objectMapper.readValue(responseBody, new TypeReference<List<LeagueDto>>() {});
        } catch (JsonProcessingException ex) {
            log.info("encountered json processing error. {}", ex.getMessage());
        }
        return leaguesDtos;
    }

    public List<LeaderBoardEntity> getAllLeaderboards() {
        return leaderboardDao.findAll();

    }
}
