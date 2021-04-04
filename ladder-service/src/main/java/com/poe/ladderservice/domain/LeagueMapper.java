package com.poe.ladderservice.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.poe.ladderservice.domain.entity.LeagueEntity;
import com.poe.ladderservice.domain.pojo.league.LeagueDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LeagueMapper {

    public static List<LeagueEntity> mapToEntities(List<LeagueDto> leagueDtos) {
        log.debug("mapping leagueDto to Entities.");
        List<LeagueEntity> entities = new ArrayList<>();
        for (LeagueDto league : leagueDtos) {
            entities.add(mapToEntity(league));
        }
        return entities;
    }

    private static LeagueEntity mapToEntity(LeagueDto leagueDto) {
        log.debug("mapping to entity.");
        return LeagueEntity.builder()
            .league(leagueDto.getId())
            .realm(leagueDto.getRealm())
            .url(leagueDto.getUrl())
            .startAt(leagueDto.getStartAt())
            .endAt(leagueDto.getEndAt())
            .description(leagueDto.getDescription())
            .delveEvent(leagueDto.isDelveEvent())
            .build();
    }

}
