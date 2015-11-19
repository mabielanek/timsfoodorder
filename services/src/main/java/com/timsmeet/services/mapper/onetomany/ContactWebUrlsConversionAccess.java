package com.timsmeet.services.mapper.onetomany;

import java.lang.reflect.Type;
import java.util.Collection;
import org.modelmapper.TypeToken;
import com.timsmeet.dto.Contact;
import com.timsmeet.dto.WebUrl;
import com.timsmeet.persistance.model.ContactEntity;
import com.timsmeet.persistance.model.WebUrlEntity;
import com.timsmeet.services.mapper.OneToManyConversionAccess;

public class ContactWebUrlsConversionAccess implements OneToManyConversionAccess<Contact, ContactEntity, WebUrl, WebUrlEntity> {
    @Override
    public void removeDestinationChild(ContactEntity parent, WebUrlEntity child) {
        parent.removeWebUrl(child);

    }

    @Override
    public void addDestinationChild(ContactEntity parent, WebUrlEntity child) {
        parent.addWebUrl(child);

    }

    @Override
    public Collection<WebUrl> getSourceChilds(Contact source) {
        return source.getWebUrls();
    }

    @Override
    public Collection<WebUrlEntity> getDestinationChilds(ContactEntity destination) {
        return destination.getWebUrls();
    }

    @Override
    public Long getDestinationChildId(WebUrlEntity child) {
        return child.getId();
    }

    @Override
    public Long getSouceChildId(WebUrl child) {
        return child.getId();
    }

    @Override
    public Type getDestinationChildType() {
        return new TypeToken<WebUrlEntity>() {
        }.getType();
    }
}
