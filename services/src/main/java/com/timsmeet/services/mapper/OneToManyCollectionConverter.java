package com.timsmeet.services.mapper;

import java.lang.reflect.Type;
import java.util.Collection;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import com.google.common.collect.Lists;
import com.timsmeet.services.find.FindEntityWithIdAccessor;
import com.timsmeet.services.find.entity.IdAccessor;

public class OneToManyCollectionConverter<S, D, SC, DC> implements Converter<S, D> {

    private OneToManyConversionAccess<S, D, SC, DC> helper = null;

    public OneToManyCollectionConverter(OneToManyConversionAccess<S, D, SC, DC> oneToManyConversionAccess) {
        this.helper = oneToManyConversionAccess;
    }

    @Override
    public D convert(MappingContext<S, D> context) {

        Collection<SC> sourceCollection = helper.getSourceChilds(context.getSource());
        Collection<DC> destinationCollection = helper.getDestinationChilds(context.getDestination());
        FindEntityWithIdAccessor<DC> find = new FindEntityWithIdAccessor<DC>();
        find.setIdAccessor(new IdAccessor<DC, Long>() {

            @Override
            public Long getIdValue(DC entity) {
                return helper.getDestinationChildId(entity);
            }
        });
        Collection<DC> removeFromDestination = Lists.newLinkedList(destinationCollection);
        if (sourceCollection != null) {
            Type destinationType = helper.getDestinationChildType();
            for (SC sourceElement : sourceCollection) {
                if(helper.getSouceChildId(sourceElement) == null) {
                    MappingContext<SC, DC> elementContext = context.create(sourceElement, destinationType);
                    DC destinationChild = context.getMappingEngine().map(elementContext);
                    helper.addDestinationChild(context.getDestination(), destinationChild);
                } else {
                    DC destinationChild = find.findById(destinationCollection, helper.getSouceChildId(sourceElement));
                    if (destinationChild != null) {
                        removeFromDestination.remove(destinationChild);
                        MappingContext<SC, DC> elementContext = context.create(sourceElement, destinationChild);
                        destinationChild = context.getMappingEngine().map(elementContext);
                    }
                }

//                if (DtoStateHelper.isDeleted(sourceElement)) {
//                    DC deletedChild = find.findById(destinationCollection, helper.getSouceChildId(sourceElement));
//                    if (deletedChild != null) {
//                        helper.removeDestinationChild(context.getDestination(), deletedChild);
//                    }
//                } else {
//                    DC destinationChild = null;
//                    if (DtoStateHelper.isNew(sourceElement)) {
//                        MappingContext<SC, DC> elementContext = context.create(sourceElement, destinationType);
//                        destinationChild = context.getMappingEngine().map(elementContext);
//                        helper.addDestinationChild(context.getDestination(), destinationChild);
//                    } else {
//                        destinationChild = find.findById(destinationCollection, helper.getSouceChildId(sourceElement));
//                        if (destinationChild != null) {
//                            MappingContext<SC, DC> elementContext = context.create(sourceElement, destinationChild);
//                            destinationChild = context.getMappingEngine().map(elementContext);
//                        }
//                    }
//                }
            }
            for(DC toRemove : removeFromDestination) {
                helper.removeDestinationChild(context.getDestination(), toRemove);
            }
        }
        return context.getDestination();
    }
}
