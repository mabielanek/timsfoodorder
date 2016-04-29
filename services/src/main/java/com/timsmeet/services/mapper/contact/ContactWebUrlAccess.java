package com.timsmeet.services.mapper.contact;

import java.util.Collection;

import com.timsmeet.dto.Contact;
import com.timsmeet.dto.WebUrl;
import com.timsmeet.services.mapper.SourceCollectionAccess;

public class ContactWebUrlAccess implements SourceCollectionAccess<Long, Contact, WebUrl> {

    @Override
    public Collection<WebUrl> getChilds(Contact source) {
        return source.getWebUrls();
    }

    @Override
    public Long getChildId(WebUrl child) {
        return child.getId();
    }

}
