package com.poe.ladderservice.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poe.ladderservice.db.entity.LeaderBoardEntity;

@Repository
public interface LadderRepository extends CrudRepository<LeaderBoardEntity, Long> {
    List<LeaderBoardEntity> findAll();
}
