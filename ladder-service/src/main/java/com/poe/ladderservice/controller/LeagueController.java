package com.poe.ladderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.poe.ladderservice.domain.entity.LeagueEntity;
import com.poe.ladderservice.service.LeagueService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(LadderController.BASE_URL)
public class LeagueController {

    public static final String LEAGUES_PATH = "/leagues";

    @Autowired
    private LeagueService leagueService;

    @GetMapping(LEAGUES_PATH)
    @ResponseStatus(HttpStatus.OK)
    public List<LeagueEntity> getLeagues() {
        log.info("received GET request to: {}", LEAGUES_PATH);
        return leagueService.getLeagues();
    }
}
