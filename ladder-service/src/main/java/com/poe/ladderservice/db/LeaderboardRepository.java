package com.poe.ladderservice.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.poe.ladderservice.domain.entity.RankEntity;

@Repository
public interface LeaderboardRepository extends CrudRepository<RankEntity, Long>, PagingAndSortingRepository<RankEntity, Long> {
    Page<RankEntity> findByLeague(String league, Pageable pageable);
}
