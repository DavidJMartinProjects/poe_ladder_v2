package com.poe.ladderservice.domain;

import com.poe.ladderservice.domain.entity.LeaderBoardEntity;
import com.poe.ladderservice.domain.enums.LadderTypes;
import com.poe.ladderservice.domain.pojo.ladder.Entry;
import com.poe.ladderservice.domain.pojo.ladder.LadderDto;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class LeaderboardMapper {

    public List<LeaderBoardEntity> mapToEntities(List<LadderDto> ladderDtos) {
        log.info("mapping ladderDtos to Entities.");
        List<LeaderBoardEntity> entities = new ArrayList<>();
        for (LadderDto ladder : ladderDtos) {
            List<Entry> ladderEntries = ladder.getLadder().getEntries();
            for (Entry ladderEntry : ladderEntries) {
                entities.add(mapToEntity(ladderEntry, ladder.getLeague().getId()));
            }
        }
        return entities;
    }

    private LeaderBoardEntity mapToEntity(Entry ladderEntry, String league) {
        log.info("mapping to entity.");
        return LeaderBoardEntity.builder()
            .league(league)
            .account(ladderEntry.getAccount().getName())
            .ascendancy(ladderEntry.getCharacter().getCharacterClass())
            .characterId(ladderEntry.getCharacter().getId())
            .character(ladderEntry.getCharacter().getName())
            .dead(ladderEntry.isDead())
            .experience(ladderEntry.getCharacter().getExperience())
            .level(ladderEntry.getCharacter().getLevel())
            .online(ladderEntry.isOnline())
            .rank(ladderEntry.getRank())
            .realm(ladderEntry.getAccount().getRealm())
            .build();
    }


    public LeaderBoardEntity mapToLeaderboardEntry(@NonNull String leagueName, @NonNull LadderTypes leaderboardType, com.poe.ladderservice.domain.pojo.ladder_new.Entry responseEntry, @NonNull String timestamp) {
        return  LeaderBoardEntity.builder()
                .character(responseEntry.getCharacter().getName())
                .account(responseEntry.getAccount().getName())
                .online(responseEntry.getOnline())
                .ascendancy(responseEntry.getCharacter().getCharClass())
                .timeStamp(timestamp)
                .league(leagueName)
                .leaderboard(leaderboardType.getType())
                .rank(responseEntry.getRank())
                .rankDifference("0")
                .experience(0)
                .characterId(responseEntry.getCharacter().getCharacterId())
                .build();

//        mapLeagueSpecificFields(leaderboardType, responseEntry, leaderboardEntity);
//        return leaderboardEntity;
    }

//    public void mapLeagueSpecificFields(@NonNull LeaderboardType leaderboardType, @NonNull Entry responseEntry, @NonNull LeaderBoardEntity leaderboardEntity) {
//        if (leaderboardType == LeaderboardType.DELVE) {
//            leaderboardEntity.setDepth(responseEntry.getCharacter().getDepth().getSolo().toString());
//            leaderboardEntity.setDead(responseEntry.getDead().toString());
//        } else if (leaderboardType == LeaderboardType.UBERLAB) {
//            leaderboardEntity.setTime(responseEntry.getTime());
//            leaderboardEntity.setTimeFormatted(TimestampUtils.formatSecondsToMinutes(responseEntry.getTime()));
//        } else if(leaderboardType == LeaderboardType.RACETO100) {
//            leaderboardEntity.setDead(responseEntry.getDead().toString());
//            leaderboardEntity.setLevel(responseEntry.getCharacter().getLevel().toString());
//            String experience = responseEntry.getCharacter().getExperience().toString();
//            String level = leaderboardEntity.getLevel();
//            String levelProgress = progressBarService.getProgressPercentage(level, experience);
//            leaderboardEntity.setProgress(levelProgress);
//            String formattedXp = formatExperience(experience);
//            leaderboardEntity.setExperience(formattedXp);
//        }
//    }

//    private String formatExperience(@NonNull String xp) {
//        return FormattingUtils.formatStringToDouble(xp);
//    }


}

