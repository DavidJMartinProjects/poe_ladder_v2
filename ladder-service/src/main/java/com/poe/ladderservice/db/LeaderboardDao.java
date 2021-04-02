package com.poe.ladderservice.db.dao;

import java.util.List;

import com.poe.ladderservice.domain.LeaderboardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poe.ladderservice.domain.entity.LeaderBoardEntity;
import com.poe.ladderservice.domain.pojo.ladder.LadderDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LeaderboardDao {

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    @Autowired
    private LeaderboardMapper mapper;

    public void saveAll(List<LadderDto> ladderDtos) {
        List<LeaderBoardEntity> leaderBoardEntities = mapper.mapToEntities(ladderDtos);
        leaderboardRepository.saveAll(leaderBoardEntities);
        log.debug("successfully saved leaderboard to db.");
    }

    public List<LeaderBoardEntity> findAll() {
        log.debug("fetching all leaderboards from the db.");
        return leaderboardRepository.findAll();
    }
}
