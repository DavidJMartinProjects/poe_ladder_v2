package com.poe.ladder.backend.leaderboard.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TimestampUtils {
	
	public static String getCurrentTimestamp() {
		Date date = new Date();
		Timestamp ts=new Timestamp(date.getTime());  
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		return formatter.format(ts);
	}
	
	public static String formatSecondsToMinutes(String timeInSeconds) {
		Integer theTime = new Integer(timeInSeconds);
		int minutes = theTime/60;
		int seconds = theTime%60;
		return ""+minutes+"min "+seconds+"sec";
	}

}
		