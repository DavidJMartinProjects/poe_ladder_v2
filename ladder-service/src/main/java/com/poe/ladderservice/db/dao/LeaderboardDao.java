package com.poe.ladderservice.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poe.ladderservice.db.entity.LeaderBoardEntity;
import com.poe.ladderservice.domain.pojo.ladder.LadderDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LeaderboardDao {

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    @Autowired
    private LadderBoardMapper mapper;

    public void saveAll(List<LadderDto> ladderEntries) {
        List<LeaderBoardEntity> entities = mapper.mapToEntities(ladderEntries);
        leaderboardRepository.saveAll(entities);
        log.debug("successfully saved leaderboard to db.");
    }

    public List<LeaderBoardEntity> findAll() {
        log.debug("fetching all leaderboards from the db.");
        return leaderboardRepository.findAll();
    }
}
