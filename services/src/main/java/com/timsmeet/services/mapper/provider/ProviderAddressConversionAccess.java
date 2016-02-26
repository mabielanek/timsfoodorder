package com.timsmeet.services.mapper.provider;

import java.lang.reflect.Type;
import org.modelmapper.TypeToken;
import com.timsmeet.dto.Address;
import com.timsmeet.dto.Provider;
import com.timsmeet.persistance.model.AddressEntity;
import com.timsmeet.persistance.model.ProviderEntity;
import com.timsmeet.services.mapper.OneToOneConversionAccess;

public class ProviderAddressConversionAccess implements OneToOneConversionAccess<Provider, ProviderEntity, Address, AddressEntity> {

    @Override
    public Address getSourceChild(Provider source) {
        return source.getAddress();
    }

    @Override
    public AddressEntity getDestinationChild(ProviderEntity destination) {
        return destination.getAddress();
    }

    @Override
    public void setDestincationChild(ProviderEntity destination, AddressEntity child) {
        destination.setAddress(child);
    }

    @Override
    public Type getDestinationChildType() {
        return new TypeToken<AddressEntity>(){}.getType();
    }
    
    
}
