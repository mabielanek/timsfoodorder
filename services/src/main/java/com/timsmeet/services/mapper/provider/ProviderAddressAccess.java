package com.timsmeet.services.mapper.provider;

import com.timsmeet.dto.Address;
import com.timsmeet.dto.Provider;
import com.timsmeet.services.mapper.SourceChildAccess;

public class ProviderAddressAccess implements SourceChildAccess<Provider, Address> {

    @Override
    public Address getChild(Provider source) {
        return source.getAddress();
    }

}
