package com.timsmeet.services.mapper.contact;

import java.util.Collection;

import com.timsmeet.dto.Contact;
import com.timsmeet.dto.Email;
import com.timsmeet.services.mapper.SourceCollectionAccess;

public class ContactEmailsAccess implements SourceCollectionAccess<Long, Contact, Email> {

    @Override
    public Collection<Email> getChilds(Contact source) {
        return source.getEmails();
    }

    @Override
    public Long getChildId(Email child) {
        return child.getId();
    }

}
