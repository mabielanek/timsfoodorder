package com.timsmeet.services.mapper;

import org.springframework.stereotype.Service;

import com.timsmeet.dto.Vacation;
import com.timsmeet.persistance.model.VacationEntity;

@Service
public class VacationMapper implements Mapper<Vacation, VacationEntity> {

	@Override
	public void map(Vacation source, VacationEntity target) {
		if(source.getLastModificationId() != null) {
			target.setLastModificationId(source.getLastModificationId());
		}
		target.setStartDay(source.getStartDay());
		target.setEndDay(source.getEndDay());
	}

	@Override
	public void inverseMap(VacationEntity source, Vacation target) {
		target.setId(source.getId());
		target.setLastModificationId(source.getLastModificationId());
		target.setStartDay(source.getStartDay());
		target.setEndDay(source.getEndDay());
	}

}
