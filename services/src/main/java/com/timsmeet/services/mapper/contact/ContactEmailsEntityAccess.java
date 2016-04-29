package com.timsmeet.services.mapper.contact;

import java.lang.reflect.Type;
import java.util.Collection;

import org.modelmapper.TypeToken;

import com.timsmeet.persistance.model.ContactEntity;
import com.timsmeet.persistance.model.EmailEntity;
import com.timsmeet.services.mapper.DestinationCollectionAccess;

public class ContactEmailsEntityAccess implements DestinationCollectionAccess<Long, ContactEntity, EmailEntity> {

    @Override
    public Collection<EmailEntity> getChilds(ContactEntity source) {
        return source.getEmails();
    }

    @Override
    public Long getChildId(EmailEntity child) {
        return child.getId();
    }

    @Override
    public void removeChild(ContactEntity parent, EmailEntity child) {
        parent.removeEmail(child);
    }

    @Override
    public void addChild(ContactEntity parent, EmailEntity child) {
        parent.addEmail(child);
    }

    @Override
    public Type getChildType() {
        return new TypeToken<EmailEntity>() {}.getType();
    }

}
