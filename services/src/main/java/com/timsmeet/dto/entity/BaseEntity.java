package com.timsmeet.dto.entity;

public class BaseEntity {

	private EntityAspect entityAspect;
	
	public BaseEntity() {
		this.entityAspect = new EntityAspect(this.getClass().getName());
	}

	public EntityAspect getEntityAspect() {
		return entityAspect;
	}

	public void setEntityAspect(EntityAspect entityAspect) {
		this.entityAspect = entityAspect;
	}
	
}
