package com.timsmeet.services.find.entity;

import com.timsmeet.persistance.model.VacationEntity;

public class VacationIdAccessor<T extends VacationEntity> implements IdAccessor<T, Long> {

	@Override
	public Long getIdValue(T entity) {
		return entity.getId();
	}

}
