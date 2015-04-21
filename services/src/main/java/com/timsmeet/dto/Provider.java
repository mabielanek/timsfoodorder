package com.timsmeet.dto;

import java.util.ArrayList;
import java.util.List;

import com.timsmeet.dto.entity.BaseEntity;
import com.timsmeet.dto.entity.EntityState;
import com.timsmeet.persistance.enums.ActivityStatus;

public class Provider extends BaseEntity {

	private Long id;
	private Long lastModificationId;
	private ActivityStatus status;
	private String name;
	private String comment;
	private Address address;
	private Contact contact;
	private List<WorkingHour> workingHours = new ArrayList<WorkingHour>();
	private List<Vacation> vacations = new ArrayList<Vacation>();
	private List<AdditionalCost> additionalCosts = new ArrayList<AdditionalCost>();
	private List<Dish> dishes = new ArrayList<Dish>();
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public List<WorkingHour> getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(List<WorkingHour> workingHours) {
		this.workingHours = workingHours;
	}
	public List<Vacation> getVacations() {
		return vacations;
	}
	public void setVacations(List<Vacation> vacations) {
		this.vacations = vacations;
	}
	public List<AdditionalCost> getAdditionalCosts() {
		return additionalCosts;
	}
	public void setAdditionalCosts(List<AdditionalCost> additionalCosts) {
		this.additionalCosts = additionalCosts;
	}
	public List<Dish> getDishes() {
		return dishes;
	}
	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}
	
	

	public final static class Builder {
		private final Provider provider = new Provider();

		public Builder(EntityState entityState, String providerName, ActivityStatus status) {
			provider.getEntityAspect().setEntityState(entityState);
			provider.setName(providerName);
			provider.setStatus(status);
		}

		public Provider build() {
			return provider;
		}

		public Builder id(Long id) {
			provider.setId(id);
			return this;
		}

		public Builder lastModificationId(Long lastModificationId) {
			provider.setLastModificationId(lastModificationId);
			return this;
		}

		public Builder status(ActivityStatus status) {
			provider.setStatus(status);
			return this;
		}

		public Builder name(String name) {
			provider.setName(name);
			return this;
		}
		
		public Builder comment(String comment) {
			provider.setComment(comment);
			return this;
		}

		public Builder address(Address address) {
			provider.setAddress(address);
			return this;
		}

		public Builder contact(Contact contact) {
			provider.setContact(contact);
			return this;
		}

		public Builder workingHours(List<WorkingHour> workingHours) {
			provider.setWorkingHours(workingHours);
			return this;
		}

		public Builder additionalCosts(List<AdditionalCost> additionalCosts) {
			provider.setAdditionalCosts(additionalCosts);
			return this;
		}

		public Builder vacations(List<Vacation> vacations) {
			provider.setVacations(vacations);
			return this;
		}		
		
		public Builder dishes(List<Dish> dishes) {
			provider.setDishes(dishes);
			return this;
		}
	}
}
