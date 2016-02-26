package com.timsmeet.services.mapper;

import java.util.Collection;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class OneToManyJoinEntityConverter <S, D, SC, DC, JC> implements Converter<S, D> {

    private OneToManyJoinConversionAccess<S, D, SC, DC, JC> helper = null;

    public OneToManyJoinEntityConverter(OneToManyJoinConversionAccess<S, D, SC, DC, JC> oneToManyJoinConversionAccess) {
        this.helper = oneToManyJoinConversionAccess;
    }

    @Override
    public D convert(MappingContext<S, D> context) {

        Collection<SC> sourceCollection = helper.getSourceChilds(context.getSource());
        Collection<JC> destinationCollection = helper.getJoinChilds(context.getDestination());
        Collection<JC> removeFromJoin = Lists.newLinkedList(destinationCollection);
        if (sourceCollection != null) {
            for (SC sourceElement : sourceCollection) {
                if(helper.getSouceChildId(sourceElement) == null) {
                    throw new IllegalArgumentException("Empty id in element of source collection - mapping with join.");
                }
                JC destinationChild = findExisting(destinationCollection, sourceElement);
                if (destinationChild != null) {
                    removeFromJoin.remove(destinationChild);
                } else {
                    DC realDestinationChild = helper.findDbDestinationChild(helper.getSouceChildId(sourceElement));
                    if(realDestinationChild != null) {
                        helper.createAndAddJoinChild(context.getDestination(), realDestinationChild);
                    } else {
                        throw new IllegalArgumentException("Destination child with id of element from source collection not found - mapping with join.");
                    }
                }
            }
            for(JC toRemove : removeFromJoin) {
                helper.removeJoinChild(context.getDestination(), toRemove);
            }
        }
        return context.getDestination();
    }

    private JC findExisting(Collection<JC> destinationCollection, final SC sourceElement) {
        Collection<JC> matchingEntities = Collections2.filter(destinationCollection, new Predicate<JC>() {

            @Override
            public boolean apply(JC input) {
                return helper.getSouceChildId(sourceElement).equals(helper.getDestinationChildIdFromJoin(input));
            }
        });

        if (matchingEntities.size() > 1) {
            throw new IllegalStateException("More than one entity with key: " + helper.getSouceChildId(sourceElement) + " found in collection - mapping with join.");
        }
        return matchingEntities.size() == 1 ? matchingEntities.iterator().next() : null;
    }
}
