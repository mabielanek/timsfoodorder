package com.timsmeet.services.find.entity;

import com.timsmeet.persistance.model.WorkingHourEntity;

public class WorkingHourIdAccessor<T extends WorkingHourEntity> implements IdAccessor<T, Long> {

	@Override
	public Long getIdValue(WorkingHourEntity entity) {
		return entity.getId();
	}

}
