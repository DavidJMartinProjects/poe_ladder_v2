package com.poe.ladderservice.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poe.ladderservice.domain.LeagueMapper;
import com.poe.ladderservice.domain.entity.LeagueEntity;
import com.poe.ladderservice.domain.pojo.league.LeagueDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LeagueDao {

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private LeagueMapper mapper;

    public void saveAll(List<LeagueDto> leagueDtos) {
        List<LeagueEntity> leagueEntities = mapper.mapToEntities(leagueDtos);
        leagueRepository.saveAll(leagueEntities);
        log.info("saved leagues to db.");
    }

    public List<LeagueEntity> getLeagues() {
        log.debug("fetching leagues from db.");
        return leagueRepository.findAll();
    }

    public void deleteAll() {
        leagueRepository.deleteAll();
    }

}
