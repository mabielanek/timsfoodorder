package com.timsmeet.services.mapper;

import com.timsmeet.dto.entity.BaseEntity;
import com.timsmeet.dto.entity.EntityState;


public class DtoStateHelper {

	public static boolean isDeleted(BaseEntity dtoObject) {
		return EntityState.DELETED.equals(dtoObject.getEntityAspect().getEntityState());
	}

	public static boolean isNew(BaseEntity dtoObject) {
		return EntityState.ADDED.equals(dtoObject.getEntityAspect().getEntityState());
	}

}
