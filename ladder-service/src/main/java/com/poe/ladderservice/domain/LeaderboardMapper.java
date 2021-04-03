package com.poe.ladderservice.domain;

import com.poe.ladderservice.domain.entity.LeaderBoardEntity;
import com.poe.ladderservice.domain.pojo.ladder.Entry;
import com.poe.ladderservice.domain.pojo.ladder.LadderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class LeaderboardMapper {

    public List<LeaderBoardEntity> mapToEntities(List<LadderDto> ladderDtos) {
        log.debug("mapping ladderDtos to Entities.");
        List<LeaderBoardEntity> entities = new ArrayList<>();
        for (LadderDto ladder : ladderDtos) {
            List<Entry> ladderEntries = ladder.getLadder().getEntries();
            for (Entry ladderEntry : ladderEntries) {
                entities.add(mapToEntity(ladderEntry, ladder.getLeague().getId()));
            }
        }
        return entities;
    }

    private LeaderBoardEntity mapToEntity(Entry ladderEntry, String league) {
        log.debug("mapping to entity.");
        return LeaderBoardEntity.builder()
            .league(league)
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

