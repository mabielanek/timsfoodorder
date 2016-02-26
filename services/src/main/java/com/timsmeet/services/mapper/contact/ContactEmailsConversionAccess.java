package com.timsmeet.services.mapper.contact;

import java.lang.reflect.Type;
import java.util.Collection;
import org.modelmapper.TypeToken;
import com.timsmeet.dto.Contact;
import com.timsmeet.dto.Email;
import com.timsmeet.persistance.model.ContactEntity;
import com.timsmeet.persistance.model.EmailEntity;
import com.timsmeet.services.mapper.OneToManyConversionAccess;

public class ContactEmailsConversionAccess implements OneToManyConversionAccess<Contact, ContactEntity, Email, EmailEntity> {
    @Override
    public void removeDestinationChild(ContactEntity parent, EmailEntity child) {
        parent.removeEmail(child);
    }

    @Override
    public void addDestinationChild(ContactEntity parent, EmailEntity child) {
        parent.addEmail(child);
    }

    @Override
    public Collection<Email> getSourceChilds(Contact source) {
        return source.getEmails();
    }

    @Override
    public Collection<EmailEntity> getDestinationChilds(ContactEntity destination) {
        return destination.getEmails();
    }

    @Override
    public Long getDestinationChildId(EmailEntity child) {
        return child.getId();
    }

    @Override
    public Long getSouceChildId(Email child) {
        return child.getId();
    }

    @Override
    public Type getDestinationChildType() {
        return new TypeToken<EmailEntity>() {
        }.getType();
    }
}