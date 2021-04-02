package com.poe.ladderservice.scheduled;

import com.poe.ladderservice.db.dao.LeaderboardDao;
import com.poe.ladderservice.domain.pojo.ladder.LadderDto;
import com.poe.ladderservice.domain.pojo.league.LeagueDto;
import com.poe.ladderservice.service.LadderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class LadderUpdateService {

    @Autowired
    private LadderService ladderService;

    @Autowired
    private LeaderboardDao leaderboardDao;

    public void fetchAndSaveLatestLeaderboards() {
        List<LeagueDto> leaguesDtos = ladderService.getCurrentLeagues();
        for (LeagueDto leagueDto : leaguesDtos) {
            LadderDto ladderDto = ladderService.getLadderByLeague(leagueDto.getId());
            leaderboardDao.saveAll(Collections.singletonList(ladderDto));
        }
    }

}