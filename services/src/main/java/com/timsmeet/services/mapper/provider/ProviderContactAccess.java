package com.timsmeet.services.mapper.provider;

import com.timsmeet.dto.Contact;
import com.timsmeet.dto.Provider;
import com.timsmeet.services.mapper.SourceChildAccess;

public class ProviderContactAccess implements SourceChildAccess<Provider, Contact> {

    @Override
    public Contact getChild(Provider source) {
        return source.getContact();
    }

}
