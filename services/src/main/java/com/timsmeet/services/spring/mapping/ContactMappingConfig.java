package com.timsmeet.services.spring.mapping;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import com.timsmeet.dto.Contact;
import com.timsmeet.persistance.model.ContactEntity;
import com.timsmeet.services.mapper.ChildEntityConverterBuilder;
import com.timsmeet.services.mapper.contact.ContactEmailsAccess;
import com.timsmeet.services.mapper.contact.ContactEmailsEntityAccess;
import com.timsmeet.services.mapper.contact.ContactPhonesAccess;
import com.timsmeet.services.mapper.contact.ContactPhonesEntityAccess;
import com.timsmeet.services.mapper.contact.ContactWebUrlAccess;
import com.timsmeet.services.mapper.contact.ContactWebUrlEntityAccess;

@Service
public class ContactMappingConfig {

    public void addMappings(ModelMapper modelMapper) {

        modelMapper.addMappings(new PropertyMap<Contact, ContactEntity>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map().setLastModificationId(source.getLastModificationId());
            }
        }).setPostConverter(new ChildEntityConverterBuilder<Contact, ContactEntity>()
                .addCollectionConverterFrom(new ContactEmailsAccess(), new ContactEmailsEntityAccess())
                .addCollectionConverterFrom(new ContactWebUrlAccess(), new ContactWebUrlEntityAccess())
                .addCollectionConverterFrom(new ContactPhonesAccess(), new ContactPhonesEntityAccess()).build());

    }
}
