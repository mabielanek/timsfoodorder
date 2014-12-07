package com.timsmeet.services.mapper;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.timsmeet.dto.Contact;
import com.timsmeet.dto.Email;
import com.timsmeet.dto.Phone;
import com.timsmeet.dto.WebUrl;
import com.timsmeet.persistance.model.ContactEntity;
import com.timsmeet.persistance.model.EmailEntity;
import com.timsmeet.persistance.model.PhoneEntity;
import com.timsmeet.persistance.model.WebUrlEntity;

@Service
public class ContactMapper implements Mapper<Contact, ContactEntity> {

	@Autowired
	private EmailMapper emailMapper;
	
	@Autowired
	private PhoneMapper phoneMapper;
	
	@Autowired
	private WebUrlMapper webUrlMapper;
	
	@Override
	public void map(Contact source, ContactEntity target) {
		if(source.getLastModificationId() != null) {
			target.setLastModificationId(source.getLastModificationId());
		}
		target.setStatus(source.getStatus());

		if(source.getEmails() != null) {
			for(Email email : source.getEmails()) {
				if(DtoStateHelper.isDeleted(email)) {
					EmailEntity deletedEmail = findEmailById(target, email.getId());
					if(deletedEmail != null) {
						target.removeEmail(deletedEmail);
					}
				} else {
					EmailEntity emailEntity = existingOrNewEmailEntity(target, email);
					emailMapper.map(email, emailEntity);
				}
			}
		}
		
		if(source.getPhones() != null) {
			for(Phone phone : source.getPhones()) {
				if(DtoStateHelper.isDeleted(phone)) {
					PhoneEntity deletedPhone = findPhoneById(target, phone.getId());
					if(deletedPhone != null) {
						target.removePhone(deletedPhone);
					}
				} else {
					PhoneEntity phoneEntity = existingOrNewPhoneEntity(target, phone);
					phoneMapper.map(phone, phoneEntity);
				}
			}
		}
		
		if(source.getWebUrls() != null) {
			for(WebUrl webUrl : source.getWebUrls()) {
				if(DtoStateHelper.isDeleted(webUrl)) {
					WebUrlEntity deletedWebUrl = findWebUrlById(target, webUrl.getId());
					if(deletedWebUrl != null) {
						target.removeWebUrl(deletedWebUrl);
					}
				} else {
					WebUrlEntity webUrlEntity = existingOrNewWebUrlEntity(target, webUrl);
					webUrlMapper.map(webUrl, webUrlEntity);
				}
			}
		}
		
		
		//todo add other mappings
	}

	private EmailEntity existingOrNewEmailEntity(ContactEntity contactEntity, Email email) {
		if(DtoStateHelper.isNew(email)) {
			EmailEntity emailEntity = new EmailEntity();
			contactEntity.addEmail(emailEntity);
			return emailEntity;
		}
		return findEmailById(contactEntity, email.getId());
	}

	private EmailEntity findEmailById(ContactEntity contactEntity, final long emailId) {
		Collection<EmailEntity> matchingEmails = Collections2.filter(contactEntity.getEmails(), new Predicate<EmailEntity>() {
			@Override
			public boolean apply(EmailEntity input) {
				return input.getId() == emailId;
			}
		});
		if(matchingEmails.size() == 0) {
			throw new IllegalArgumentException("Existing email entity with key: " + emailId + " not found for contact.");
		}
		return matchingEmails.iterator().next();
	}

	
	private PhoneEntity existingOrNewPhoneEntity(ContactEntity contactEntity, Phone phone) {
		if(DtoStateHelper.isNew(phone)) {
			PhoneEntity phoneEntity = new PhoneEntity();
			contactEntity.addPhone(phoneEntity);
			return phoneEntity;
		}
		return findPhoneById(contactEntity, phone.getId());
	}

	private PhoneEntity findPhoneById(ContactEntity contactEntity, final long phoneId) {
		Collection<PhoneEntity> matchingPhones = Collections2.filter(contactEntity.getPhones(), new Predicate<PhoneEntity>() {
			@Override
			public boolean apply(PhoneEntity input) {
				return input.getId() == phoneId;
			}
		});
		if(matchingPhones.size() == 0) {
			throw new IllegalArgumentException("Existing phone entity with key: " + phoneId + " not found for contact.");
		}
		return matchingPhones.iterator().next();
	}

	private WebUrlEntity existingOrNewWebUrlEntity(ContactEntity contactEntity, WebUrl webUrl) {
		if(DtoStateHelper.isNew(webUrl)) {
			WebUrlEntity webUrlEntity = new WebUrlEntity();
			contactEntity.addWebUrl(webUrlEntity);
			return webUrlEntity;
		}
		return findWebUrlById(contactEntity, webUrl.getId());
	}

	private WebUrlEntity findWebUrlById(ContactEntity contactEntity, final long webUrlId) {
		Collection<WebUrlEntity> matchingWebUrls = Collections2.filter(contactEntity.getWebUrls(), new Predicate<WebUrlEntity>() {
			@Override
			public boolean apply(WebUrlEntity input) {
				return input.getId() == webUrlId;
			}
		});
		if(matchingWebUrls.size() == 0) {
			throw new IllegalArgumentException("Existing webUrl entity with key: " + webUrlId + " not found for contact.");
		}
		return matchingWebUrls.iterator().next();
	}

	@Override
	public void inverseMap(ContactEntity source, Contact target) {
		target.setId(source.getId());
		target.setLastModificationId(source.getLastModificationId());
		target.setStatus(source.getStatus());
		if(HibernateMapperHelper.isCollectionInitialized(source.getEmails())) {
			List<Email> emails = Lists.newArrayListWithCapacity(source.getEmails().size()); 
			for(EmailEntity dbEmail : source.getEmails()) {
				Email email = new Email();
				emailMapper.inverseMap(dbEmail, email);
				emails.add(email);
			}
			target.setEmails(emails);
		}
		if(HibernateMapperHelper.isCollectionInitialized(source.getPhones())) {
			List<Phone> phones = Lists.newArrayListWithCapacity(source.getPhones().size());
			for(PhoneEntity dbPhone : source.getPhones()) {
				Phone phone = new Phone();
				phoneMapper.inverseMap(dbPhone, phone);
				phones.add(phone);
			}
			target.setPhones(phones);
		}
		if(HibernateMapperHelper.isCollectionInitialized(source.getWebUrls())) {
			List<WebUrl> webUrls = Lists.newArrayListWithCapacity(source.getWebUrls().size());
			for(WebUrlEntity dbWebUrl : source.getWebUrls()) {
				WebUrl webUrl = new WebUrl();
				webUrlMapper.inverseMap(dbWebUrl, webUrl);
				webUrls.add(webUrl);
			}
			target.setWebUrls(webUrls);
		}
	}

}
