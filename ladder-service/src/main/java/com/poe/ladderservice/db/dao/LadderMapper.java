package com.poe.ladderservice.db.dao;

import com.poe.ladderservice.db.entity.LeaderBoardEntity;
import com.poe.ladderservice.domain.pojo.ladder.Entry;
import com.poe.ladderservice.domain.pojo.ladder.LadderDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LadderMapper {

    public List<LeaderBoardEntity> fromDtoListToEntityList(List<LadderDto> ladders) {
        List<LeaderBoardEntity> entities = new ArrayList<>();
        for (LadderDto ladder : ladders) {
            List<Entry> ladderEntries = ladder.getLadder().getEntries();
            for (Entry ladderEntry : ladderEntries) {
                entities.add(mapToEntity(ladderEntry));
            }
        }
        return entities;
    }

    private LeaderBoardEntity mapToEntity(Entry ladderEntry) {
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

