package com.timsmeet.services.mapper;

import java.lang.reflect.Type;

import org.modelmapper.TypeToken;

import com.timsmeet.dto.Contact;
import com.timsmeet.dto.Provider;
import com.timsmeet.persistance.model.ContactEntity;
import com.timsmeet.persistance.model.ProviderEntity;

public class ProviderContactConversionAccess implements OneToOneConversionAccess<Provider, ProviderEntity, Contact, ContactEntity> {

    @Override
    public Contact getSourceChild(Provider source) {
        return source.getContact();
    }

    @Override
    public ContactEntity getDestinationChild(ProviderEntity destination) {
        return destination.getContact();
    }

    @Override
    public void setDestincationChild(ProviderEntity destination, ContactEntity child) {
        destination.setContact(child);
    }

    @Override
    public Type getDestinationChildType() {
        return new TypeToken<ContactEntity>(){}.getType();
    }

}
