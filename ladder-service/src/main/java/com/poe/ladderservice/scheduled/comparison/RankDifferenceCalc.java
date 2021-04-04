package com.poe.ladderservice.scheduled.comparison;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poe.ladderservice.domain.entity.RankEntity;
import com.poe.ladderservice.domain.enums.LadderTypes;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RankDifferenceCalc {
	
	@Autowired
	LeagueComparisonUtil leagueComparisonUtil;
	
	public List<RankEntity> calcRankings(List<RankEntity> oldRanks, List<RankEntity> newRanks) {
		log.info("comparing league data to determine latest rankings.");
		List<RankEntity> ladder = new ArrayList<>();
		for (RankEntity newRank : newRanks) { // compare new ranks to old ranks
			for (RankEntity oldRank : oldRanks) {
				ladder = new ArrayList<>(newRanks);
				if(isMatchingRank(newRank, oldRank)) { // if ranks match
					ladder.add(calcRankDifference(oldRank, newRank)); // update the ranking difference
				} else { // if no match found, return the new rank
					ladder.add(newRank);
				}
			}				
		}			    		
		return ladder;
	}

	private boolean isMatchingRank(RankEntity newRank, RankEntity oldRank) {
		return newRank.getCharacterId().equals(oldRank.getCharacterId())
			&& newRank.getLeague().equals(oldRank.getLeague())
			&& newRank.getLeaderboard().equals(oldRank.getLeaderboard());
	}

	private RankEntity calcRankDifference(RankEntity oldRank, RankEntity newRank) {
		RankEntity updatedRank = newRank;
		updatedRank.setRankDifference(leagueComparisonUtil.compareRankDifference(oldRank, newRank));
		if(updatedRank.getLeaderboard().equals(LadderTypes.RACETO100.toString())) {
			updatedRank.setExperienceDifference(leagueComparisonUtil.calcXpDifference(oldRank, newRank));
		} else if(updatedRank.getLeaderboard().equals(LadderTypes.DELVE.toString())) {
			updatedRank.setDepthDifference(leagueComparisonUtil.compareDepthDifference(oldRank, newRank));
		} else if(updatedRank.getLeaderboard().equals(LadderTypes.UBERLAB.toString())) {
			updatedRank.setTimeDifference(leagueComparisonUtil.compareTimeDifference(oldRank, newRank));
		}
		return updatedRank;
	}
	
}
	