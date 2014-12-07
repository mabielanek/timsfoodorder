package com.timsmeet.services.mapper;

import org.springframework.stereotype.Service;

import com.timsmeet.dto.WorkingHour;
import com.timsmeet.persistance.model.WorkingHourEntity;

@Service
public class WorkingHourMapper implements Mapper<WorkingHour, WorkingHourEntity> {

	@Override
	public void map(WorkingHour source, WorkingHourEntity target) {
		if(source.getLastModificationId() != null) {
			target.setLastModificationId(source.getLastModificationId());
		}
		target.setEndTime(source.getEndTime());
		target.setStartTime(source.getStartTime());;
		target.setWeekDay(source.getWeekDay());
	}

	@Override
	public void inverseMap(WorkingHourEntity source, WorkingHour target) {
		target.setId(source.getId());
		target.setLastModificationId(source.getLastModificationId());
		target.setEndTime(source.getEndTime());
		target.setStartTime(source.getStartTime());;
		target.setWeekDay(source.getWeekDay());
	}

}
