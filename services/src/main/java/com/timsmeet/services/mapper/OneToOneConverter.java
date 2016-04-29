package com.timsmeet.services.mapper;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class OneToOneConverter<S, D, SC, DC> implements Converter<S, D> {

    private SourceChildAccess<S, SC> sourceHelper = null;
    private DestinationChildAccess<D, DC> destinationHelper = null;

    public OneToOneConverter(SourceChildAccess<S, SC> sourceChildAccess, DestinationChildAccess<D, DC> destinationChildAccess) {
        this.sourceHelper = sourceChildAccess;
        this.destinationHelper = destinationChildAccess;
    }

    @Override
    public D convert(MappingContext<S, D> context) {
        SC sourceChildEntity = sourceHelper.getChild(context.getSource());
        DC destinationChildEntity = destinationHelper.getChild(context.getDestination());

        if(destinationChildEntity != null) {
            MappingContext<SC, DC> elementContext = context.create(sourceChildEntity, destinationChildEntity);
            context.getMappingEngine().map(elementContext);
        } else {
            MappingContext<SC, DC> elementContext = context.create(sourceChildEntity, destinationHelper.getChildType());
            destinationChildEntity = context.getMappingEngine().map(elementContext);
            destinationHelper.setChild(context.getDestination(), destinationChildEntity);
        }
        return context.getDestination();
    }

}
