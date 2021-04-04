package com.poe.ladderservice.domain;

import com.poe.ladderservice.domain.entity.RankEntity;
import com.poe.ladderservice.domain.enums.LadderTypes;
import com.poe.ladderservice.domain.pojo.ladder.Entry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LeaderboardMapper {

    public RankEntity mapToLeaderboardEntry(String leagueName, LadderTypes types, Entry entry, String timestamp) {
        RankEntity entity = RankEntity.builder()
            .character(entry.getCharacter().getName())
            .account(entry.getAccount().getName())
            .online(entry.getOnline())
            .ascendancy(entry.getCharacter().getCharClass())
            .timeStamp(timestamp)
            .league(leagueName)
            .leaderboard(types.getType())
            .rank(entry.getRank())
            .rankDifference("0")
            .experience(0)
            .characterId(entry.getCharacter().getCharacterId())
            .build();
        resolveLeagueSpecificFields(types, entry, entity);
        return entity;
    }

    public void resolveLeagueSpecificFields(LadderTypes types, Entry entry, RankEntity leaderboard) {
        if (types == LadderTypes.DELVE) {
            leaderboard.setDepth(entry.getCharacter().getDepth().getSolo().toString());
            leaderboard.setDead(entry.getDead());
        } else if (types == LadderTypes.UBERLAB) {
            leaderboard.setTime(entry.getTime());
            leaderboard.setTimeFormatted(entry.getTime());
        } else if(types == LadderTypes.RACETO100) {
            leaderboard.setDead(entry.getDead());
            leaderboard.setLevel(entry.getCharacter().getLevel());
//            String experience = entry.getCharacter().getExperience();
//            String level = leaderboard.getLevel();
//            String levelProgress = progressBarService.getProgressPercentage(level, experience);
//            leaderboard.setProgress(levelProgress);
//            String formattedXp = formatExperience(experience);
//            leaderboard.setExperience(formattedXp);
        }
    }

//    private String formatExperience(@NonNull String xp) {
//        return FormattingUtils.formatStringToDouble(xp);
//    }


}

