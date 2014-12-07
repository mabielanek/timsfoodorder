package com.timsmeet.services.mapper;

import org.springframework.stereotype.Service;

import com.timsmeet.dto.Email;
import com.timsmeet.persistance.model.EmailEntity;

@Service
public class EmailMapper implements Mapper<Email, EmailEntity> {

	@Override
	public void map(Email source, EmailEntity target) {
		if(source.getLastModificationId() != null) {
			target.setLastModificationId(source.getLastModificationId());
		}
		target.setEmailAddress(source.getEmailAddress());
		target.setStatus(source.getStatus());
		target.setDisplayIndex(source.getDisplayIndex());
		target.setComment(source.getComment());
	}

	@Override
	public void inverseMap(EmailEntity source, Email target) {
		target.setId(source.getId());
		target.setLastModificationId(source.getLastModificationId());
		target.setEmailAddress(source.getEmailAddress());
		target.setStatus(source.getStatus());
		target.setDisplayIndex(source.getDisplayIndex());
		target.setComment(source.getComment());
	}

}
