package com.timsmeet.dto.entity;

public class EntityAspect {

	private String entityType;
	private EntityState entityState;
	
	public EntityAspect() {
		this("");
	}
	
	public EntityAspect(String entityType) {
		this(entityType, EntityState.UNCHANGED);
	}
	
	public EntityAspect(String entityType, EntityState entityState) {
		this.entityType = entityType;
		this.entityState = entityState;
	}
	
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public EntityState getEntityState() {
		return entityState;
	}
	public void setEntityState(EntityState entityState) {
		this.entityState = entityState;
	}
	
}
