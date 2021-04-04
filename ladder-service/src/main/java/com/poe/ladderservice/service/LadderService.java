package com.poe.ladderservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.poe.ladderservice.db.LadderDao;
import com.poe.ladderservice.db.LeaderboardDao;
import com.poe.ladderservice.db.LeagueDao;
import com.poe.ladderservice.domain.entity.RankEntity;
import com.poe.ladderservice.domain.entity.LeagueEntity;
import com.poe.ladderservice.domain.pojo.PageParams;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LadderService {

    @Autowired
    private LeagueDao leagueDao;

    @Autowired
    private LadderDao ladderDao;

    @Autowired
    private LeaderboardDao leaderboardDao;

    public List<RankEntity> getLeaderboardsSummary(PageParams pageParams) {
        List<LeagueEntity> leagues = leagueDao.getLeagues();
        List<RankEntity> topLeaderBoards = new ArrayList<>();
        for(LeagueEntity leagueEntity: leagues) {
            Page<RankEntity> leaderboardPage = ladderDao.findByLeague(leagueEntity.getLeague(), pageParams);
            topLeaderBoards.addAll(leaderboardPage.getContent());
        }
        return topLeaderBoards;
    }

    public Page<RankEntity> getLeaderboardByLeague(String league, PageParams pageParams) {
        return leaderboardDao.findByLeague(league, pageParams);
    }

}
