package com.poe.ladderservice.db.dao;

import com.poe.ladderservice.db.entity.LeaderBoardEntity;
import com.poe.ladderservice.domain.pojo.ladder.Entry;
import com.poe.ladderservice.domain.pojo.ladder.LadderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class LadderBoardMapper {

    public List<LeaderBoardEntity> mapToEntities(List<LadderDto> ladderDtos) {
        log.debug("mapping ladderDtos to Entities.");
        List<LeaderBoardEntity> entities = new ArrayList<>();
        for (LadderDto ladder : ladderDtos) {
            List<Entry> ladderEntries = ladder.getLadder().getEntries();
            for (Entry ladderEntry : ladderEntries) {
                entities.add(mapToEntity(ladderEntry));
            }
        }
        return entities;
    }

    private LeaderBoardEntity mapToEntity(Entry ladderEntry) {
        log.debug("mapping to entity.");
        return LeaderBoardEntity.builder()
            .account(ladderEntry.getAccount().getName())
            .ascendancy(ladderEntry.getCharacter().getCharacterClass())
            .characterId(ladderEntry.getCharacter().getId())
            .character(ladderEntry.getCharacter().getName())
            .dead(ladderEntry.isDead())
            .experience(ladderEntry.getCharacter().getExperience())
            .level(ladderEntry.getCharacter().getLevel())
            .online(ladderEntry.isOnline())
            .rank(ladderEntry.getRank())
            .realm(ladderEntry.getAccount().getRealm())
            .build();
    }

}

