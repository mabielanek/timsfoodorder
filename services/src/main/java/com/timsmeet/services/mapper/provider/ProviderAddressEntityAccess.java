package com.timsmeet.services.mapper.provider;

import java.lang.reflect.Type;
import org.modelmapper.TypeToken;
import com.timsmeet.persistance.model.AddressEntity;
import com.timsmeet.persistance.model.ProviderEntity;
import com.timsmeet.services.mapper.DestinationChildAccess;

public class ProviderAddressEntityAccess implements DestinationChildAccess<ProviderEntity, AddressEntity> {

    @Override
    public AddressEntity getChild(ProviderEntity source) {
        return source.getAddress();
    }

    @Override
    public void setChild(ProviderEntity destination, AddressEntity child) {
        destination.setAddress(child);
    }

    @Override
    public Type getChildType() {
        return new TypeToken<AddressEntity>(){}.getType();
    }

}
