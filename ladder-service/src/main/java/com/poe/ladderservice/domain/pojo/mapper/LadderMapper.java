package com.poe.ladderservice.domain.pojo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.poe.ladderservice.db.entity.LeaderBoardEntity;
import com.poe.ladderservice.domain.pojo.ladder.Entry;
import com.poe.ladderservice.domain.pojo.ladder.LadderDto;

@Component
public class LadderMapper {

    public List<LeaderBoardEntity> fromDtoListToEntityList(List<LadderDto> ladders) {
        List<LeaderBoardEntity> entities = new ArrayList<>();
        for (LadderDto ladder : ladders) {
            List<Entry> ladderEntries = ladder.getLadder().getEntries();
            for (Entry ladderEntry : ladderEntries) {
                LeaderBoardEntity entity =
                    LeaderBoardEntity.builder()
                        .characterId(ladderEntry.getCharacter().getId())
                        .account(ladderEntry.getAccount().getName())
                        .build();
                entities.add(entity);
            }
        }
        return entities;
    }

}

