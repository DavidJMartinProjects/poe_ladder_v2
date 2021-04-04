package com.poe.ladderservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poe.ladderservice.db.LeagueDao;
import com.poe.ladderservice.domain.entity.LeagueEntity;
import com.poe.ladderservice.domain.pojo.league.LeagueDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LeagueService {

    @Autowired
    LeagueDao leagueDao;

    public List<LeagueEntity> getLeagues() {
        return leagueDao.getLeagues();
    }

    public List<String> getLeagueNames() {
        List<LeagueEntity> leagues = leagueDao.getLeagues();
        List<String> leagueNames = new ArrayList<>();
        for(LeagueEntity league : leagues) {
            leagueNames.add(league.getLeague());
        }
        return leagueNames;
    }

}
