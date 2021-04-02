package com.poe.ladderservice.db.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poe.ladderservice.domain.entity.LeaderBoardEntity;

@Repository
public interface LeaderboardRepository extends CrudRepository<LeaderBoardEntity, Long> {
    List<LeaderBoardEntity> findAll();
}
