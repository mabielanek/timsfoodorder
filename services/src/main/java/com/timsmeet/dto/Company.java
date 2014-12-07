package com.timsmeet.dto;

import java.util.List;

import com.timsmeet.persistance.enums.ActivityStatus;

public class Company {

	private Long id;
	private Long lastModificationId;
	private ActivityStatus status;
	private String name;
	private Address address;
	private Contact contact;
	private List<WorkingHour> workingHours;
	private List<Vacation> vacations;
	private List<Field> fields;
	private List<ServiceType> serviceTypes;
	private List<ServiceLocation> serviceLocations;

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

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public List<ServiceType> getServiceTypes() {
		return serviceTypes;
	}

	public void setServiceTypes(List<ServiceType> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}

	public List<ServiceLocation> getServiceLocations() {
		return serviceLocations;
	}

	public void setServiceLocations(List<ServiceLocation> serviceLocations) {
		this.serviceLocations = serviceLocations;
	}

	public final static class Builder {
		private final Company company = new Company();

		public Builder(String companyName, ActivityStatus status) {
			company.setName(companyName);
			company.setStatus(status);
		}

		public Company build() {
			return company;
		}

		public Builder id(Long id) {
			company.setId(id);
			return this;
		}

		public Builder lastModificationId(Long lastModificationId) {
			company.setLastModificationId(lastModificationId);
			return this;
		}

		public Builder status(ActivityStatus status) {
			company.setStatus(status);
			return this;
		}

		public Builder name(String name) {
			company.setName(name);
			return this;
		}

		public Builder address(Address address) {
			company.setAddress(address);
			return this;
		}

		public Builder contact(Contact contact) {
			company.setContact(contact);
			return this;
		}

		public Builder workingHours(List<WorkingHour> workingHours) {
			company.setWorkingHours(workingHours);
			return this;
		}

		public Builder vacations(List<Vacation> vacations) {
			company.setVacations(vacations);
			return this;
		}

		public Builder fields(List<Field> fields) {
			company.setFields(fields);
			return this;
		}

		public Builder serviceTypes(List<ServiceType> serviceTypes) {
			company.setServiceTypes(serviceTypes);
			return this;
		}

		public Builder serviceLocations(List<ServiceLocation> serviceLocations) {
			company.setServiceLocations(serviceLocations);
			return this;
		}
	}
}
