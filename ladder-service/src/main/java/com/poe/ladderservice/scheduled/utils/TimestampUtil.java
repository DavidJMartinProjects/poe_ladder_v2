package com.poe.ladderservice.scheduled.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TimestampUtil {

	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getTimestamp() {
		return FORMATTER.format(new Timestamp(new Date().getTime()));
	}
	
	public static String toMinutes(String timeInSeconds) {
		int theTime = Integer.parseInt(timeInSeconds);
		return theTime/60 + "min " + theTime%60 + "sec";
	}

}
		