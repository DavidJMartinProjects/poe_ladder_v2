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
    LadderRepository ladderRepository;

    @Autowired
    LadderMapper mapper;

    public void saveAll(List<LadderDto> ladderEntries) {
        List<LeaderBoardEntity> entities = mapper.fromDtoListToEntityList(ladderEntries);
        log.info("saving leaderboard to db.");
        ladderRepository.saveAll(entities);
    }

}
