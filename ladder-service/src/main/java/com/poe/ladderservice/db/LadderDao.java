package com.poe.ladderservice.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poe.ladderservice.domain.entity.LeaderBoardEntity;
import com.poe.ladderservice.domain.pojo.PageParams;

@Service
public class LadderDao {

    @Autowired
    LadderRepository ladderRepository;

    public void saveAll(List<LeaderBoardEntity> latestLadders) {
        ladderRepository.saveAll(latestLadders);
    }

    public Page<LeaderBoardEntity> findByLeague(String league, PageParams pageParams) {
        Pageable pageable = PageRequest.of(pageParams.getOffset(), pageParams.getLimit());
        return ladderRepository.findByLeague(league, pageable);
    }

}
