package com.timsmeet.dto;

import com.timsmeet.persistance.enums.ActivityStatus;

public class Address {

	private Long id;
	private Long lastModificationId;
	private ActivityStatus status;
	private String address1;
	private String address2;
	private String city;
	private String zipCode;
	private String state;
	private String country;
	private String comment;
	private Integer displayIndex;

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

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getDisplayIndex() {
		return displayIndex;
	}

	public void setDisplayIndex(Integer displayIndex) {
		this.displayIndex = displayIndex;
	}

	public static final class Builder {
		private final Address address = new Address();

		public Builder(ActivityStatus status) {
			address.setStatus(status);
		}

		public Address build() {
			return address;
		}

		public Builder id(Long id) {
			address.setId(id);
			return this;
		}

		public Builder lastModificationId(Long lastModificationId) {
			address.setLastModificationId(lastModificationId);
			return this;
		}

		public Builder status(ActivityStatus status) {
			address.setStatus(status);
			return this;
		}

		public Builder address1(String address1) {
			address.setAddress1(address1);
			return this;
		}

		public Builder address2(String address2) {
			address.setAddress2(address2);
			return this;
		}

		public Builder city(String city) {
			address.setCity(city);
			return this;
		}

		public Builder zipCode(String zipCode) {
			address.setZipCode(zipCode);
			return this;
		}

		public Builder state(String state) {
			address.setState(state);
			return this;
		}

		public Builder country(String country) {
			address.setCountry(country);
			return this;
		}

		public Builder comment(String comment) {
			address.setComment(comment);
			return this;
		}

		public Builder displayIndex(Integer displayIndex) {
			address.setDisplayIndex(displayIndex);
			return this;
		}

	}
}
