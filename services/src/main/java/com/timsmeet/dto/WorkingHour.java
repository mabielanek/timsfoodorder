package com.timsmeet.dto;

import java.sql.Timestamp;

import com.timsmeet.persistance.enums.WeekDay;

public class WorkingHour {
	private Long id;
	private Long lastModificationId;
	private WeekDay weekDay;
	private Timestamp startTime;
	private Timestamp endTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLastModificationId() {
		return lastModificationId;
	}

	public void setLastModificationId(Long lastModificationId) {
		this.lastModificationId = lastModificationId;
	}

	public WeekDay getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(WeekDay weekDay) {
		this.weekDay = weekDay;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public static final class Builder {
		private final WorkingHour workingHour = new WorkingHour();

		public Builder(WeekDay weekDay, Timestamp startTime, Timestamp endTime) {
			workingHour.setWeekDay(weekDay);
			workingHour.setStartTime(startTime);
			workingHour.setEndTime(endTime);
		}

		public WorkingHour build() {
			return workingHour;
		}

		public Builder id(Long id) {
			workingHour.setId(id);
			return this;
		}

		public Builder lastModificationId(Long lastModificationId) {
			workingHour.setLastModificationId(lastModificationId);
			return this;
		}

		public Builder weekDay(WeekDay weekDay) {
			workingHour.setWeekDay(weekDay);
			return this;
		}

		public Builder startTime(Timestamp startTime) {
			workingHour.setStartTime(startTime);
			return this;
		}

		public Builder endTime(Timestamp endTime) {
			workingHour.setEndTime(endTime);
			return this;
		}

	}
}
