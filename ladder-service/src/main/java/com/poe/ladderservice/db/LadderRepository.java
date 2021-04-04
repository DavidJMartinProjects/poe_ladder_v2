package com.poe.ladderservice.db;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.poe.ladderservice.domain.entity.LeaderBoardEntity;

@Repository
public interface LadderRepository extends CrudRepository<LeaderBoardEntity, Long>, PagingAndSortingRepository<LeaderBoardEntity, Long> {
    Page<LeaderBoardEntity> findByLeague(String league, Pageable pageable);
}
