package com.poe.ladderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.poe.ladderservice.domain.entity.LeaderBoardEntity;
import com.poe.ladderservice.domain.pojo.PageParams;
import com.poe.ladderservice.service.LadderService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(LadderController.BASE_URL)
public class LadderController {

    public static final String BASE_URL = "/api";
    public static final String LADDER_PATH = "/ladder";
    public static final String LADDER_SUMMARY_PATH = LADDER_PATH + "/summary";
    
    public static final String DEFAULT_LEAGUE = "";
    public static final String DEFAULT_LIMIT = "5";
    public static final String DEFAULT_OFFSET = "0";

    @Autowired
    private LadderService ladderService;

    @GetMapping(LADDER_PATH)
    @ResponseStatus(HttpStatus.OK)
    public Page<LeaderBoardEntity> getAllLeaderboards(
        @RequestParam(defaultValue = DEFAULT_LEAGUE, required = false) String league,
        @RequestParam(defaultValue = DEFAULT_LIMIT, required = false) int limit,
        @RequestParam(defaultValue = DEFAULT_OFFSET, required = false) int offset) {
        log.info("received GET request to: {}", LADDER_PATH);
        return ladderService.getAllLeaderboards(league, new PageParams(league, offset, limit));
    }

    @GetMapping(LADDER_SUMMARY_PATH)
    @ResponseStatus(HttpStatus.OK)
    public List<LeaderBoardEntity> getTopLeaderboardsSummary() {
        log.info("received GET request to: {}", LADDER_PATH);
        return ladderService.getTopLeaderboards(new PageParams(DEFAULT_OFFSET, DEFAULT_LIMIT));
    }

}
