package com.timsmeet.services.mapper;

import java.util.Collection;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class OneToManyJoinConverter<ID, S, D, SC, DC, JC> implements Converter<S, D> {

    private SourceCollectionAccess<ID, S, SC> sourceHelper = null;
    private DestinationJoinCollectionAccess<ID, D, DC, JC> destinationHelper = null;

    public OneToManyJoinConverter(SourceCollectionAccess<ID, S, SC> sourceCollectionAccess, DestinationJoinCollectionAccess<ID, D, DC, JC> desinationAccess) {
        this.sourceHelper = sourceCollectionAccess;
        this.destinationHelper = desinationAccess;
    }

    @Override
    public D convert(MappingContext<S, D> context) {

        Collection<SC> sourceCollection = sourceHelper.getChilds(context.getSource());
        Collection<JC> destinationCollection = destinationHelper.getJoinChilds(context.getDestination());
        Collection<JC> removeFromJoin = Lists.newLinkedList(destinationCollection);
        if (sourceCollection != null) {
            for (SC sourceElement : sourceCollection) {
                if(sourceHelper.getChildId(sourceElement) == null) {
                    throw new IllegalArgumentException("Empty id in element of source collection - mapping with join.");
                }
                JC destinationChild = findExisting(destinationCollection, sourceElement);
                if (destinationChild != null) {
                    removeFromJoin.remove(destinationChild);
                } else {
                    DC realDestinationChild = destinationHelper.findDbChild(sourceHelper.getChildId(sourceElement));
                    if(realDestinationChild != null) {
                        destinationHelper.createAndAddJoin(context.getDestination(), realDestinationChild);
                    } else {
                        throw new IllegalArgumentException("Destination child with id of element from source collection not found - mapping with join.");
                    }
                }
            }
            for(JC toRemove : removeFromJoin) {
                destinationHelper.removeJoin(context.getDestination(), toRemove);
            }
        }
        return context.getDestination();
    }

    private JC findExisting(Collection<JC> destinationCollection, final SC sourceElement) {
        Collection<JC> matchingEntities = Collections2.filter(destinationCollection, new Predicate<JC>() {

            @Override
            public boolean apply(JC input) {
                return sourceHelper.getChildId(sourceElement).equals(destinationHelper.getChildIdFromJoin(input));
            }
        });

        if (matchingEntities.size() > 1) {
            throw new IllegalStateException("More than one entity with key: " + sourceHelper.getChildId(sourceElement) + " found in collection - mapping with join.");
        }
        return matchingEntities.size() == 1 ? matchingEntities.iterator().next() : null;
    }
}
