package com.poe.ladderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.poe.ladderservice.domain.pojo.ladder.LadderDto;
import com.poe.ladderservice.service.LadderService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(LadderController.BASE_URL)
public class LadderController {

    public static final String BASE_URL = "/api";
    public static final String LADDER_PATH = "/ladder";

    @Autowired
    LadderService ladderService;

    @GetMapping(LADDER_PATH)
    @ResponseStatus(HttpStatus.OK)
    public LadderDto getLadders() {
//        return ladderService.getLadderByLeague();
        return null;
    }
}
