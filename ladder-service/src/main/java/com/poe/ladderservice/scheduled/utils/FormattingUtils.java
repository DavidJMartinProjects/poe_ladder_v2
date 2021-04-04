package com.poe.ladderservice.scheduled.utils;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

import org.apache.commons.lang3.StringUtils;

@Component
public class FormattingUtils {

    private static final DecimalFormat FORMATTER = new DecimalFormat("#,###");

    public static String formatStringToDouble(String number) {
        return FORMATTER.format(Double.parseDouble(number.replace(",", "")));
    }
    
    public static String removeCommasFromXpValue(long xpValue) {
		return String.valueOf(xpValue).replace(",", "");
	}
	
    public static String formatXpDifference(String xpDifference) {
    	if(isBlank(xpDifference)) {
    		return String.valueOf("");
		}
		return String.format("%.2fM", Double.parseDouble(xpDifference)/ 1000000.0);
	}
    
    public static String formatRank(String rankDifference) {
		long difference = Long.parseLong(rankDifference);
		if(difference > 0) {
			return "+" + difference;
		}
		return rankDifference;
	}
    
}	
	