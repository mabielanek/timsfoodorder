package com.timsmeet.services.mapper.contact;

import java.util.Collection;

import com.timsmeet.dto.Contact;
import com.timsmeet.dto.Phone;
import com.timsmeet.services.mapper.SourceCollectionAccess;

public class ContactPhonesAccess implements SourceCollectionAccess<Long, Contact, Phone> {

    @Override
    public Collection<Phone> getChilds(Contact source) {
        return source.getPhones();
    }

    @Override
    public Long getChildId(Phone child) {
        return child.getId();
    }

}
