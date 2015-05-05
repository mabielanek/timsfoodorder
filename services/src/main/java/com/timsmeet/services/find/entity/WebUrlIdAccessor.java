package com.timsmeet.services.find.entity;

import com.timsmeet.persistance.model.WebUrlEntity;

public class WebUrlIdAccessor implements IdAccessor<WebUrlEntity, Long> {

	@Override
	public Long getIdValue(WebUrlEntity entity) {
		return entity.getId();
	}

}
