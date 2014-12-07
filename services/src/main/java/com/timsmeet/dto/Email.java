package com.timsmeet.dto;

import com.timsmeet.persistance.enums.ActivityStatus;

public class Email {
	private Long id;
	private Long lastModificationId;
	private ActivityStatus status;
	private String comment;
	private int displayIndex;
	private String emailAddress;

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getDisplayIndex() {
		return displayIndex;
	}

	public void setDisplayIndex(int displayIndex) {
		this.displayIndex = displayIndex;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public static final class Builder {
		private final Email email = new Email();

		public Builder(ActivityStatus status) {
			email.setStatus(status);
		}

		public Email build() {
			return email;
		}

		public Builder id(Long id) {
			email.setId(id);
			return this;
		}

		public Builder lastModificationId(Long lastModificationId) {
			email.setLastModificationId(lastModificationId);
			return this;
		}

		public Builder status(ActivityStatus status) {
			email.setStatus(status);
			return this;
		}

		public Builder comment(String comment) {
			email.setComment(comment);
			return this;
		}

		public Builder displayIndex(int displayIndex) {
			email.setDisplayIndex(displayIndex);
			return this;
		}

		public Builder emailAddress(String emailAddress) {
			email.setEmailAddress(emailAddress);
			return this;
		}

	}
}
