package com.timsmeet.services.mapper;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.timsmeet.dto.entity.BaseEntity;

public class OneToOneEntityConverter<S, D, SC extends BaseEntity, DC> implements Converter<S, D> {

    private OneToOneConversionAccess<S, D, SC, DC> helper = null;

    public OneToOneEntityConverter(OneToOneConversionAccess<S, D, SC, DC> oneToOneConversionAccess) {
        this.helper = oneToOneConversionAccess;
    }

    @Override
    public D convert(MappingContext<S, D> context) {
        SC sourceChildEntity = helper.getSourceChild(context.getSource());
        DC destinationChildEntity = helper.getDestinationChild(context.getDestination());
        
        if(destinationChildEntity != null) {
            MappingContext<SC, DC> elementContext = context.create(sourceChildEntity, destinationChildEntity);
            context.getMappingEngine().map(elementContext);
        } else {
            MappingContext<SC, DC> elementContext = context.create(sourceChildEntity, helper.getDestinationChildType());
            destinationChildEntity = context.getMappingEngine().map(elementContext);
            helper.setDestincationChild(context.getDestination(), destinationChildEntity);
        }
        return context.getDestination();
    }

}
