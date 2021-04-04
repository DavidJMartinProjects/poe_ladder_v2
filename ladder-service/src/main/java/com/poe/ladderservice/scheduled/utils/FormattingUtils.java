package com.poe.ladderservice.scheduled.utils;

import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

import org.apache.commons.lang3.StringUtils;

@Component
public class FormattingUtils {

    private static DecimalFormat formatter = new DecimalFormat("#,###");

    public static String formatStringToDouble(String number) {
        String numberAsString = number.replaceAll(",", "");
        Double numberAsDouble = Double.parseDouble(numberAsString);
        return formatter.format(numberAsDouble);
    }
    
    public static String removeCommasFromXpValue(long xpValue) {
		return String.valueOf(xpValue).replaceAll(",", "");
	}
	
    public static String formatXpDifference(String xpDifference) {
    	if(StringUtils.isBlank(xpDifference)) {
    		return String.valueOf("");
		}
		return String.format("%.2fM", Double.parseDouble(xpDifference)/ 1000000.0);
	}
    
    public static String formatRank(String rankDifference) {
		Long difference = Long.parseLong(rankDifference);
		if(difference > 0) {
			return "+" + difference;
		}
		return rankDifference;
	}
    
}	
	