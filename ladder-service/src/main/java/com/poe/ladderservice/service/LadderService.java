package com.poe.ladderservice.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poe.ladderservice.db.dao.LeaderboardDao;
import com.poe.ladderservice.domain.pojo.ladder.LadderDto;
import com.poe.ladderservice.service.facade.RestTemplateFacade;

@Service
public class LadderService {

    public static String POE_LADDER_API_URL = "https://api.pathofexile.com/league/Ritual/ladder";

    @Autowired
    RestTemplateFacade restTemplateFacade;

    @Autowired
    LeaderboardDao leaderboardDao;

    public LadderDto getLadderResponse() {
        LadderDto ladderDto = restTemplateFacade.getForLadderResponse(POE_LADDER_API_URL);
        leaderboardDao.saveAll(Collections.singletonList(ladderDto));
        return ladderDto;
    }

}
