package com.poe.ladderservice.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poe.ladderservice.domain.entity.LeagueEntity;

@Repository
public interface LeagueRepository extends CrudRepository<LeagueEntity, Long> {
    List<LeagueEntity> findAll();
}

