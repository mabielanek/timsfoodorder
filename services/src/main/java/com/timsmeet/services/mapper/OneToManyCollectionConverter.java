package com.timsmeet.services.mapper;

import java.lang.reflect.Type;
import java.util.Collection;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import com.google.common.base.Predicate;
import com.google.common.base.Verify;
import com.google.common.collect.Collections2;
import com.timsmeet.dto.entity.BaseEntity;

public class OneToManyCollectionConverter<S, D, SC extends BaseEntity, DC> implements Converter<S, D> {

    private OneToManyConversionAccess<S, D, SC, DC> helper = null;

    public OneToManyCollectionConverter(OneToManyConversionAccess<S, D, SC, DC> oneToManyConversionAccess) {
        this.helper = oneToManyConversionAccess;
    }

    @Override
    public D convert(MappingContext<S, D> context) {

        Collection<SC> sourceCollection = helper.getSourceChilds(context.getSource());

        if (sourceCollection != null) {
            Type destinationType = helper.getDestinationChildType();
            for (SC sourceElement : sourceCollection) {
                if (DtoStateHelper.isDeleted(sourceElement)) {
                    DC deletedChild = findById(context, sourceElement);
                    if (deletedChild != null) {
                        helper.removeDestinationChild(context.getDestination(), deletedChild);
                    }
                } else {
                    DC destinationChild = null;
                    if (DtoStateHelper.isNew(sourceElement)) {
                        MappingContext<SC, DC> elementContext = context.create(sourceElement, destinationType);
                        destinationChild = context.getMappingEngine().map(elementContext);
                        helper.addDestinationChild(context.getDestination(), destinationChild);
                    } else {
                        destinationChild = findById(context, sourceElement);
                        if (destinationChild != null) {
                            MappingContext<SC, DC> elementContext = context.create(sourceElement, destinationChild);
                            destinationChild = context.getMappingEngine().map(elementContext);
                        }
                    }
                }
            }
        }
        return context.getDestination();
    }

    public DC findById(MappingContext<S, D> context, final SC sourceChild) {
        Verify.verifyNotNull(helper, "helper can't be null when findById");

        final Long entityId = helper.getSouceChildId(sourceChild);
        Collection<DC> matchingEntities = Collections2.filter(helper.getDestinationChilds(context.getDestination()), new Predicate<DC>() {
            @Override
            public boolean apply(DC input) {
                return helper.getDestinationChildId(input).equals(entityId);
            }
        });

        if (matchingEntities.size() == 0) {
            throw new IllegalArgumentException("Existing entity with key: " + entityId + " not found.");
        } else if (matchingEntities.size() > 1) {
            throw new IllegalStateException("More than one entity with key: " + entityId + " found in collection.");
        }
        return matchingEntities.iterator().next();
    }
}
