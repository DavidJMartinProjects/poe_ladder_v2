package com.poe.ladderservice.db;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.poe.ladderservice.domain.entity.LeaderBoardEntity;

@Repository
public interface LeaderboardRepository extends PagingAndSortingRepository<LeaderBoardEntity, Long> {
    Page<LeaderBoardEntity> findAll(Pageable pageable);
    Page<LeaderBoardEntity> findAllByLeague(String league, Pageable pageable);
    Page<LeaderBoardEntity> findByLeague(String league, Pageable pageable);
}
