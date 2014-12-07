package com.timsmeet.dto;

import java.util.List;

import com.timsmeet.persistance.enums.ActivityStatus;
import com.timsmeet.persistance.enums.EmailPreferences;

public class Employee {

	private Long id;
	private Long lastModificationId;
	private ActivityStatus status;
	private String firstName;
	private String lastName;
	private String title;
	private Contact contact;
	private EmailPreferences emailPreferences;
	private List<WorkingHour> workingHours;
	private List<ServiceType> serviceTypes;
	private List<Vacation> vacations;
	private List<Field> fields;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public EmailPreferences getEmailPreferences() {
		return emailPreferences;
	}

	public void setEmailPreferences(EmailPreferences emailPreferences) {
		this.emailPreferences = emailPreferences;
	}

	public List<WorkingHour> getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(List<WorkingHour> workingHours) {
		this.workingHours = workingHours;
	}

	public List<ServiceType> getServiceTypes() {
		return serviceTypes;
	}

	public void setServiceTypes(List<ServiceType> serviceTypes) {
		this.serviceTypes = serviceTypes;
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

	public static final class Builder {
		private final Employee employee = new Employee();

		public Builder(ActivityStatus status) {
			employee.setStatus(status);
		}

		public Employee build() {
			return employee;
		}

		public Builder id(Long id) {
			employee.setId(id);
			return this;
		}

		public Builder lastModificationId(Long lastModificationId) {
			employee.setLastModificationId(lastModificationId);
			return this;
		}

		public Builder status(ActivityStatus status) {
			employee.setStatus(status);
			return this;
		}

		public Builder firstName(String firstName) {
			employee.setFirstName(firstName);
			return this;
		}

		public Builder lastName(String lastName) {
			employee.setLastName(lastName);
			return this;
		}

		public Builder title(String title) {
			employee.setTitle(title);
			return this;
		}

		public Builder contact(Contact contact) {
			employee.setContact(contact);
			return this;
		}

		public Builder emailPreferences(EmailPreferences emailPreferences) {
			employee.setEmailPreferences(emailPreferences);
			return this;
		}

		public Builder workingHours(List<WorkingHour> workingHours) {
			employee.setWorkingHours(workingHours);
			return this;
		}

		public Builder serviceTypes(List<ServiceType> serviceTypes) {
			employee.setServiceTypes(serviceTypes);
			return this;
		}

		public Builder vacations(List<Vacation> vacations) {
			employee.setVacations(vacations);
			return this;
		}

		public Builder fields(List<Field> fields) {
			employee.setFields(fields);
			return this;
		}
		
	}

}
