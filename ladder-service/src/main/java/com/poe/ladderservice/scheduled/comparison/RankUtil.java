package com.poe.ladderservice.scheduled.comparison;

import org.springframework.stereotype.Component;

import com.poe.ladderservice.domain.entity.RankEntity;
import com.poe.ladderservice.scheduled.utils.FormattingUtils;

@Component
public class RankUtil {
	
	public String calcTimeDifference(RankEntity oldLeagueDataEntry, RankEntity newLeagueDataEntry) {
		return calculateDifference(oldLeagueDataEntry.getTime(), newLeagueDataEntry.getTime());
	}
	
	public String calcDepthDifference(RankEntity oldLeagueDataEntry, RankEntity newLeagueDataEntry) {
		return calculateDifference(oldLeagueDataEntry.getRank(), newLeagueDataEntry.getRank());		
	}
	
	public String calcRankDifference(RankEntity oldEntry, RankEntity newEntry) {
		return FormattingUtils.formatRank(calculateDifference(newEntry.getRank(), oldEntry.getRank()));
	}	
	
	public String calcXpDifference(RankEntity oldXp, RankEntity newXp) {
		String oldXpAsString="0";		
		if(oldXp != null) {
			oldXpAsString = FormattingUtils.removeCommasFromXpValue(oldXp.getExperience());
		} 		
		String newXpAsString="0";
		if(newXp != null) {
			newXpAsString = FormattingUtils.removeCommasFromXpValue(newXp.getExperience());
		}		
		return FormattingUtils.formatXpDifference(calculateDifference(oldXpAsString, newXpAsString));		
	}
	
	private String calculateDifference(int oldValue, int newValue) {
		return String.valueOf((long) newValue - (long) oldValue);
	}

	private String calculateDifference(String oldValue, String newValue) {
		return String.valueOf(Long.parseLong(newValue) - Long.parseLong(oldValue));
	}

}
