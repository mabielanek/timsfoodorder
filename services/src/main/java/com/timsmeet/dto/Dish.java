package com.timsmeet.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.timsmeet.dto.entity.BaseEntity;
import com.timsmeet.dto.entity.EntityState;
import com.timsmeet.persistance.enums.ActivityStatus;

public class Dish extends BaseEntity {

	private Long id;
	private Long lastModificationId;
	private ActivityStatus status;
	private String name;
	private String description;
	private Timestamp avaiabilityStartDay;
	private Timestamp avaiabilityEndDay;
	private List<DishComponent> dishComponents = new ArrayList<DishComponent>();
	private List<DishGenere> dishGeneres = new ArrayList<DishGenere>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLastModificationId() {
		return lastModificationId;
	}
	public void setLastModificationId(Long lastModificationId) {
		this.lastModificationId = lastModificationId;
	}
	public ActivityStatus getStatus() {
		return status;
	}
	public void setStatus(ActivityStatus status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getAvaiabilityStartDay() {
		return avaiabilityStartDay;
	}
	public void setAvaiabilityStartDay(Timestamp avaiabilityStartDay) {
		this.avaiabilityStartDay = avaiabilityStartDay;
	}
	public Timestamp getAvaiabilityEndDay() {
		return avaiabilityEndDay;
	}
	public void setAvaiabilityEndDay(Timestamp avaiabilityEndDay) {
		this.avaiabilityEndDay = avaiabilityEndDay;
	}
	public List<DishComponent> getDishComponents() {
		return dishComponents;
	}
	public void setDishComponents(List<DishComponent> dishComponents) {
		this.dishComponents = dishComponents;
	}
	public List<DishGenere> getDishGeneres() {
		return dishGeneres;
	}
	public void setDishGeneres(List<DishGenere> dishGeneres) {
		this.dishGeneres = dishGeneres;
	}
	
	public final static class Builder {
		private final Dish dish = new Dish();

		public Builder(EntityState entityState, String providerName, ActivityStatus status) {
			dish.getEntityAspect().setEntityState(entityState);
			dish.setName(providerName);
			dish.setStatus(status);
		}

		public Dish build() {
			return dish;
		}

		public Builder id(Long id) {
			dish.setId(id);
			return this;
		}

		public Builder lastModificationId(Long lastModificationId) {
			dish.setLastModificationId(lastModificationId);
			return this;
		}

		public Builder status(ActivityStatus status) {
			dish.setStatus(status);
			return this;
		}

		public Builder name(String name) {
			dish.setName(name);
			return this;
		}
		
		public Builder description(String description) {
			dish.setDescription(description);
			return this;
		}
		
		public Builder avaiabilityStartDay(Timestamp avaiabilityStartDay) {
			dish.setAvaiabilityStartDay(avaiabilityStartDay);
			return this;
		}
		public Builder avaiabilityEndDay(Timestamp avaiabilityEndDay) {
			dish.setAvaiabilityEndDay(avaiabilityEndDay);
			return this;
		}
		public Builder dishComponents(List<DishComponent> dishComponents) {
			dish.setDishComponents(dishComponents);
			return this;
		}
		public Builder dishGeneres(List<DishGenere> dishGeneres) {
			dish.setDishGeneres(dishGeneres);
			return this;
		}

	}
}
