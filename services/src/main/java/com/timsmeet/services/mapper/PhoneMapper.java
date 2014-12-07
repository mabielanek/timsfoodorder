package com.timsmeet.services.mapper;

import org.springframework.stereotype.Service;

import com.timsmeet.dto.Phone;
import com.timsmeet.persistance.model.PhoneEntity;

@Service
public class PhoneMapper implements Mapper<Phone, PhoneEntity> {

	@Override
	public void map(Phone source, PhoneEntity target) {
		if(source.getLastModificationId() != null) {
			target.setLastModificationId(source.getLastModificationId());
		}
		target.setStatus(source.getStatus());
		target.setComment(source.getComment());
		target.setDisplayIndex(source.getDisplayIndex());
		target.setNumberType(source.getNumberType());
		target.setPhone(source.getPhone());
		target.setPhoneExt(source.getPhoneExt());
	}

	@Override
	public void inverseMap(PhoneEntity source, Phone target) {
		target.setId(source.getId());
		target.setLastModificationId(source.getLastModificationId());
		target.setStatus(source.getStatus());
		target.setComment(source.getComment());
		target.setDisplayIndex(source.getDisplayIndex());
		target.setNumberType(source.getNumberType());
		target.setPhone(source.getPhone());
		target.setPhoneExt(source.getPhoneExt());
	}

}
