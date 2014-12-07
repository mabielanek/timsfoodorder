package com.timsmeet.services.mapper;

import org.springframework.stereotype.Service;

import com.timsmeet.dto.WebUrl;
import com.timsmeet.persistance.model.WebUrlEntity;

@Service
public class WebUrlMapper implements Mapper<WebUrl, WebUrlEntity> {

	@Override
	public void map(WebUrl source, WebUrlEntity target) {
		if(source.getLastModificationId() != null) {
			target.setLastModificationId(source.getLastModificationId());
		}
		target.setStatus(source.getStatus());
		target.setComment(source.getComment());
		target.setDisplayIndex(source.getDisplayIndex());
		target.setWebUrlAddress(source.getWebUrlAddress());
	}

	@Override
	public void inverseMap(WebUrlEntity source, WebUrl target) {
		target.setId(source.getId());
		target.setLastModificationId(source.getLastModificationId());
		target.setStatus(source.getStatus());
		target.setComment(source.getComment());
		target.setDisplayIndex(source.getDisplayIndex());
		target.setWebUrlAddress(source.getWebUrlAddress());
	}

}
