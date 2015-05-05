package com.timsmeet.services.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.timsmeet.dto.Contact;
import com.timsmeet.dto.Email;
import com.timsmeet.dto.Phone;
import com.timsmeet.dto.WebUrl;
import com.timsmeet.persistance.model.ContactEntity;
import com.timsmeet.persistance.model.EmailEntity;
import com.timsmeet.persistance.model.PhoneEntity;
import com.timsmeet.persistance.model.WebUrlEntity;
import com.timsmeet.services.find.FindEntityWithIdAccessor;

@Service
public class ContactMapper implements Mapper<Contact, ContactEntity> {

	@Autowired
	private EmailMapper emailMapper;
	
	@Autowired
	private PhoneMapper phoneMapper;
	
	@Autowired
	private WebUrlMapper webUrlMapper;
	
	@Autowired
	private FindEntityWithIdAccessor<EmailEntity> emailFind;
	
	@Autowired
	private FindEntityWithIdAccessor<PhoneEntity> phoneFind;
	
	@Autowired
	private FindEntityWithIdAccessor<WebUrlEntity> webUrlFind;
	
	@Override
	public void map(Contact source, ContactEntity target) {
		if(source.getLastModificationId() != null) {
			target.setLastModificationId(source.getLastModificationId());
		}
		target.setStatus(source.getStatus());

		if(source.getEmails() != null) {
			for(Email email : source.getEmails()) {
				if(DtoStateHelper.isDeleted(email)) {
					EmailEntity deletedEmail = emailFind.findById(target.getEmails(), email.getId());
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
					PhoneEntity deletedPhone = phoneFind.findById(target.getPhones(), phone.getId());
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
					WebUrlEntity deletedWebUrl = webUrlFind.findById(target.getWebUrls(), webUrl.getId());
					if(deletedWebUrl != null) {
						target.removeWebUrl(deletedWebUrl);
					}
				} else {
					WebUrlEntity webUrlEntity = existingOrNewWebUrlEntity(target, webUrl);
					webUrlMapper.map(webUrl, webUrlEntity);
				}
			}
		}
	}

	private EmailEntity existingOrNewEmailEntity(ContactEntity contactEntity, Email email) {
		if(DtoStateHelper.isNew(email)) {
			EmailEntity emailEntity = new EmailEntity();
			contactEntity.addEmail(emailEntity);
			return emailEntity;
		}
		return emailFind.findById(contactEntity.getEmails(), email.getId());
	}

	private PhoneEntity existingOrNewPhoneEntity(ContactEntity contactEntity, Phone phone) {
		if(DtoStateHelper.isNew(phone)) {
			PhoneEntity phoneEntity = new PhoneEntity();
			contactEntity.addPhone(phoneEntity);
			return phoneEntity;
		}
		return phoneFind.findById(contactEntity.getPhones(), phone.getId());
	}

	private WebUrlEntity existingOrNewWebUrlEntity(ContactEntity contactEntity, WebUrl webUrl) {
		if(DtoStateHelper.isNew(webUrl)) {
			WebUrlEntity webUrlEntity = new WebUrlEntity();
			contactEntity.addWebUrl(webUrlEntity);
			return webUrlEntity;
		}
		return webUrlFind.findById(contactEntity.getWebUrls(), webUrl.getId());
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
