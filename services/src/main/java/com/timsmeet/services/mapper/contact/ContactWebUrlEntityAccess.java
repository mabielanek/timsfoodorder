package com.timsmeet.services.mapper.contact;

import java.lang.reflect.Type;
import java.util.Collection;

import org.modelmapper.TypeToken;

import com.timsmeet.persistance.model.ContactEntity;
import com.timsmeet.persistance.model.WebUrlEntity;
import com.timsmeet.services.mapper.DestinationCollectionAccess;

public class ContactWebUrlEntityAccess implements DestinationCollectionAccess<Long, ContactEntity, WebUrlEntity> {

    @Override
    public Collection<WebUrlEntity> getChilds(ContactEntity source) {
        return source.getWebUrls();
    }

    @Override
    public Long getChildId(WebUrlEntity child) {
        return child.getId();
    }

    @Override
    public void removeChild(ContactEntity parent, WebUrlEntity child) {
        parent.removeWebUrl(child);
    }

    @Override
    public void addChild(ContactEntity parent, WebUrlEntity child) {
        parent.addWebUrl(child);
    }

    @Override
    public Type getChildType() {
        return new TypeToken<WebUrlEntity>() {}.getType();
    }

}
