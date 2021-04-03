package com.poe.ladderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poe.ladderservice.db.LeagueDao;
import com.poe.ladderservice.domain.entity.LeagueEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LeagueService {

    @Autowired
    LeagueDao leagueDao;

    public List<LeagueEntity> getLeagues() {
        return leagueDao.getLeagues();
    }

}
