package com.timsmeet.services.find.entity;

import com.timsmeet.persistance.model.PhoneEntity;

public class PhoneIdAccessor implements IdAccessor<PhoneEntity, Long> {

	@Override
	public Long getIdValue(PhoneEntity entity) {
		return entity.getId();
	}

}
