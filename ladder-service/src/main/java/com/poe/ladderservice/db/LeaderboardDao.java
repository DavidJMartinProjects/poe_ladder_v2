package com.poe.ladderservice.db;

import com.poe.ladderservice.domain.LeaderboardMapper;
import com.poe.ladderservice.domain.entity.LeaderBoardEntity;
import com.poe.ladderservice.domain.pojo.PageParams;
import com.poe.ladderservice.domain.pojo.ladder.Ladder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class LeaderboardDao {

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    @Autowired
    private LeaderboardMapper mapper;

    public Page<LeaderBoardEntity> findByLeague(String league, PageParams pageParams) {
        log.debug("fetching all leaderboards from the db.");
        Pageable pageable = PageRequest.of(pageParams.getOffset(), pageParams.getLimit());
        return leaderboardRepository.findByLeague(league, pageable);
    }


}
