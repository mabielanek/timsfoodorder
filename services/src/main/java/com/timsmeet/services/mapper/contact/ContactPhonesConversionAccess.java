package com.timsmeet.services.mapper.contact;

import java.lang.reflect.Type;
import java.util.Collection;
import org.modelmapper.TypeToken;
import com.timsmeet.dto.Contact;
import com.timsmeet.dto.Phone;
import com.timsmeet.persistance.model.ContactEntity;
import com.timsmeet.persistance.model.PhoneEntity;
import com.timsmeet.services.mapper.OneToManyConversionAccess;

public class ContactPhonesConversionAccess implements OneToManyConversionAccess<Contact, ContactEntity, Phone, PhoneEntity> {
    @Override
    public void removeDestinationChild(ContactEntity parent, PhoneEntity child) {
        parent.removePhone(child);
    }

    @Override
    public void addDestinationChild(ContactEntity parent, PhoneEntity child) {
        parent.addPhone(child);
    }

    @Override
    public Collection<Phone> getSourceChilds(Contact source) {
        return source.getPhones();
    }

    @Override
    public Collection<PhoneEntity> getDestinationChilds(ContactEntity destination) {
        return destination.getPhones();
    }

    @Override
    public Long getDestinationChildId(PhoneEntity child) {
        return child.getId();
    }

    @Override
    public Long getSouceChildId(Phone child) {
        return child.getId();
    }

    @Override
    public Type getDestinationChildType() {
        return new TypeToken<PhoneEntity>() {
        }.getType();
    }
}