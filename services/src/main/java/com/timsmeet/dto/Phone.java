package com.timsmeet.dto;

import com.timsmeet.persistance.enums.ActivityStatus;
import com.timsmeet.persistance.enums.PhoneNumberType;

public class Phone {
	private Long id;
	private Long lastModificationId;
	private ActivityStatus status;
	private String phone;
	private String phoneExt;
	private PhoneNumberType numberType;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneExt() {
		return phoneExt;
	}

	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
	}

	public PhoneNumberType getNumberType() {
		return numberType;
	}

	public void setNumberType(PhoneNumberType numberType) {
		this.numberType = numberType;
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
		private final Phone phone = new Phone();

		public Builder(ActivityStatus status, PhoneNumberType numberType) {
			phone.setStatus(status);
			phone.setNumberType(numberType);
		}

		public Phone build() {
			return phone;
		}

		public void id(Long id) {
			phone.setId(id);
		}

		public Builder lastModificationId(Long lastModificationId) {
			phone.setLastModificationId(lastModificationId);
			return this;
		}

		public Builder status(ActivityStatus status) {
			phone.setStatus(status);
			return this;
		}

		public Builder phone(String phoneNum) {
			phone.setPhone(phoneNum);
			return this;
		}

		public Builder phoneExt(String phoneExt) {
			phone.setPhoneExt(phoneExt);
			return this;
		}

		public Builder numberType(PhoneNumberType numberType) {
			phone.setNumberType(numberType);
			return this;
		}

		public Builder comment(String comment) {
			phone.setComment(comment);
			return this;
		}

		public Builder displayIndex(Integer displayIndex) {
			phone.setDisplayIndex(displayIndex);
			return this;
		}
	}

}
