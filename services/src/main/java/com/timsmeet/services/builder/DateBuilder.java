package com.timsmeet.services.builder;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

public class DateBuilder {

	private static final Calendar calendarPattern = Calendar.getInstance();

	//private static final SimpleDateFormat timeTextFormat = new SimpleDateFormat("hh:mm");
	//private static final SimpleDateFormat dateTextFormat = new SimpleDateFormat("yyyy-MM-dd");

	static {
		calendarPattern.setTimeZone(TimeZone.getTimeZone("UTC"));
		calendarPattern.set(Calendar.YEAR, 2000);
		calendarPattern.set(Calendar.MONTH, 6);
		calendarPattern.set(Calendar.DAY_OF_MONTH, 1);
		calendarPattern.set(Calendar.HOUR_OF_DAY, 0);
		calendarPattern.set(Calendar.MINUTE, 0);
		calendarPattern.set(Calendar.SECOND, 0);
		calendarPattern.set(Calendar.MILLISECOND, 0);
	}
	
	public static Timestamp utcDateAsTimestamp(int year, int month, int day) {
		Calendar cal = (Calendar)calendarPattern.clone();
		cal.set(year, month, day);
		return new Timestamp(cal.getTimeInMillis());
	}
	
	public static Timestamp utcTimeAsTimestamp(int hour, int minute) {
		Calendar cal = (Calendar)calendarPattern.clone();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		return new Timestamp(cal.getTimeInMillis());
	}
	
//	public Date buildTime(String timeString) {
//		try {
//			Date date = timeTextFormat.parse(timeString);
//			return calendarPattern.set)
//		} catch (ParseException e) {
//			throw new IllegalArgumentException("Wrong date string format", e);
//		}
//	}
}
