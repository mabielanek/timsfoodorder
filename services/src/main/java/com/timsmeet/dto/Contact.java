package com.timsmeet.dto;

import java.util.ArrayList;
import java.util.List;

import com.timsmeet.persistance.enums.ActivityStatus;

public class Contact {
	private Long id;
	private Long lastModificationId;
	private ActivityStatus status;
	private List<Phone> phones = new ArrayList<Phone>();
	private List<WebUrl> webUrls = new ArrayList<WebUrl>();
	private List<Email> emails = new ArrayList<Email>();

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

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public List<WebUrl> getWebUrls() {
		return webUrls;
	}

	public void setWebUrls(List<WebUrl> webUrls) {
		this.webUrls = webUrls;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public static final class Builder {
		private final Contact contact = new Contact();

		public Builder(ActivityStatus status) {
			contact.setStatus(status);
		}

		public Contact build() {
			return contact;
		}

		public Builder id(Long id) {
			contact.setId(id);
			return this;
		}

		public Builder lastModificationId(Long lastModificationId) {
			contact.setLastModificationId(lastModificationId);
			return this;
		}

		public Builder status(ActivityStatus status) {
			contact.setStatus(status);
			return this;
		}

		public Builder phones(List<Phone> phones) {
			contact.setPhones(phones);
			return this;
		}

		public Builder webUrls(List<WebUrl> webUrls) {
			contact.setWebUrls(webUrls);
			return this;
		}

		public Builder emails(List<Email> emails) {
			contact.setEmails(emails);
			return this;
		}
	}

}
