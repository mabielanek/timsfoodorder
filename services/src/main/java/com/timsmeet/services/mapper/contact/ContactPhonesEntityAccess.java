package com.timsmeet.services.mapper.contact;

import java.lang.reflect.Type;
import java.util.Collection;

import org.modelmapper.TypeToken;

import com.timsmeet.persistance.model.ContactEntity;
import com.timsmeet.persistance.model.PhoneEntity;
import com.timsmeet.services.mapper.DestinationCollectionAccess;

public class ContactPhonesEntityAccess implements DestinationCollectionAccess<Long, ContactEntity, PhoneEntity> {

    @Override
    public Collection<PhoneEntity> getChilds(ContactEntity source) {
        return source.getPhones();
    }

    @Override
    public Long getChildId(PhoneEntity child) {
        return child.getId();
    }

    @Override
    public void removeChild(ContactEntity parent, PhoneEntity child) {
        parent.removePhone(child);
    }

    @Override
    public void addChild(ContactEntity parent, PhoneEntity child) {
        parent.addPhone(child);
    }

    @Override
    public Type getChildType() {
        return new TypeToken<PhoneEntity>() {}.getType();
    }

}
