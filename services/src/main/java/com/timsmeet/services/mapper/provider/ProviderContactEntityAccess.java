package com.timsmeet.services.mapper.provider;

import java.lang.reflect.Type;
import org.modelmapper.TypeToken;
import com.timsmeet.persistance.model.ContactEntity;
import com.timsmeet.persistance.model.ProviderEntity;
import com.timsmeet.services.mapper.DestinationChildAccess;

public class ProviderContactEntityAccess implements DestinationChildAccess<ProviderEntity, ContactEntity> {

    @Override
    public ContactEntity getChild(ProviderEntity source) {
        return source.getContact();
    }

    @Override
    public void setChild(ProviderEntity destination, ContactEntity child) {
        destination.setContact(child);
    }

    @Override
    public Type getChildType() {
        return new TypeToken<ContactEntity>(){}.getType();
    }

}
