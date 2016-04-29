package com.timsmeet.services.mapper;

import java.lang.reflect.Type;
import java.util.Collection;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.google.common.collect.Lists;
import com.timsmeet.services.find.FindEntityWithIdAccessor;
import com.timsmeet.services.find.entity.IdAccessor;

public class OneToManyConverter <ID, S, D, SC, DC> implements Converter<S, D> {

    private SourceCollectionAccess<ID, S, SC> sourceHelper = null;
    private DestinationCollectionAccess<ID, D, DC> destinationHelper = null;

    public OneToManyConverter(SourceCollectionAccess<ID, S, SC> sourceCollectionAccess, DestinationCollectionAccess<ID, D, DC> destinationCollectionAccess) {
        this.sourceHelper = sourceCollectionAccess;
        this.destinationHelper = destinationCollectionAccess;
    }

    @Override
    public D convert(MappingContext<S, D> context) {

        Collection<SC> sourceCollection = sourceHelper.getChilds(context.getSource());
        if (sourceCollection != null) {
            
            FindEntityWithIdAccessor<DC, ID> find = buildFindEntityById();

            Collection<DC> destinationCollection = destinationHelper.getChilds(context.getDestination());
            Collection<DC> removeFromDestination = Lists.newLinkedList(destinationCollection);
            
            Type destinationType = destinationHelper.getChildType();
            for (SC sourceElement : sourceCollection) {
                if(sourceHelper.getChildId(sourceElement) == null) {
                    MappingContext<SC, DC> elementContext = context.create(sourceElement, destinationType);
                    DC destinationChild = context.getMappingEngine().map(elementContext);
                    destinationHelper.addChild(context.getDestination(), destinationChild);
                } else {
                    DC destinationChild = find.findById(destinationCollection, sourceHelper.getChildId(sourceElement));
                    if (destinationChild != null) {
                        removeFromDestination.remove(destinationChild);
                        MappingContext<SC, DC> elementContext = context.create(sourceElement, destinationChild);
                        destinationChild = context.getMappingEngine().map(elementContext);
                    }
                }

            }
            for(DC toRemove : removeFromDestination) {
                destinationHelper.removeChild(context.getDestination(), toRemove);
            }
        }
        return context.getDestination();
    }

    private FindEntityWithIdAccessor<DC, ID> buildFindEntityById() {
        FindEntityWithIdAccessor<DC, ID> findById = new FindEntityWithIdAccessor<DC, ID>();
        findById.setIdAccessor(new IdAccessor<DC, ID>() {

            @Override
            public ID getIdValue(DC entity) {
                return destinationHelper.getChildId(entity);
            }
        });
        return findById;
    }

}
