package com.timsmeet.services.mapper;

import org.springframework.stereotype.Service;

import com.timsmeet.dto.Address;
import com.timsmeet.persistance.model.AddressEntity;

@Service
public class AddressMapper implements Mapper<Address, AddressEntity> {

	@Override
	public void map(Address source, AddressEntity target) {
		if(source.getLastModificationId() != null) {
			target.setLastModificationId(source.getLastModificationId());
		}
		target.setAddress1(source.getAddress1());
		target.setAddress2(source.getAddress2());
		target.setCity(source.getCity());
		target.setComment(source.getComment());
		target.setCountry(source.getCountry());
		target.setDisplayIndex(source.getDisplayIndex());
		target.setState(source.getState());
		target.setStatus(source.getStatus());
		target.setZipCode(source.getZipCode());
	}

	@Override
	public void inverseMap(AddressEntity source, Address target) {
		target.setId(source.getId());
		target.setLastModificationId(source.getLastModificationId());
		target.setAddress1(source.getAddress1());
		target.setAddress2(source.getAddress2());
		target.setCity(source.getCity());
		target.setComment(source.getComment());
		target.setCountry(source.getCountry());
		target.setDisplayIndex(source.getDisplayIndex());
		target.setId(source.getId());
		target.setState(source.getState());
		target.setStatus(source.getStatus());
		target.setZipCode(source.getZipCode());
	}
}
