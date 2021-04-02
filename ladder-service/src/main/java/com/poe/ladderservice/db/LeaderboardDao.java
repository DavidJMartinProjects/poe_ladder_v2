package com.poe.ladderservice.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poe.ladderservice.db.entity.LeaderBoardEntity;
import com.poe.ladderservice.domain.pojo.ladder.LadderDto;
import com.poe.ladderservice.domain.pojo.mapper.LadderMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LeaderboardDao {

    @Autowired
    LadderRepository ladderRepository;

    @Autowired
    LadderMapper mapper;

    public void saveAll(List<LadderDto> ladderEntries) {
        log.info("*** entities.ascendancy : {}", ladderEntries.get(0));
        List<LeaderBoardEntity> entities = mapper.fromDtoListToEntityList(ladderEntries);
        log.info("*** entities.ascendancy : {}", entities.get(0).getAscendancy());
        ladderRepository.saveAll(entities);
    }

}
