package com.timsmeet.services.find.entity;

import com.timsmeet.persistance.model.EmailEntity;

public class EmailIdAccessor implements IdAccessor<EmailEntity, Long> {

	@Override
	public Long getIdValue(EmailEntity entity) {
		return entity.getId();
	}

}
