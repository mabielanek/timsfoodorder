package com.timsmeet.services.mapper;

import java.util.List;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import com.google.common.collect.Lists;

public class ChildEntityConverterBuilder<S, D> {

    private static class ChildEntityConverter<S, D> implements Converter<S, D> {

        private List<Converter<S,D>> converters = Lists.newArrayList();

        public void addConverter(Converter<S,D> childConverter) {
            converters.add(childConverter);
        }

        @Override
        public D convert(MappingContext<S, D> context) {
            for(Converter<S, D> converter : converters) {
                converter.convert(context);
            }
            return context.getDestination();
        }
    }

    private ChildEntityConverter<S, D> chainedConverter = new ChildEntityConverter<S, D>();

    public Converter<S, D> build() {
        return chainedConverter;
    }

//    public <SC, DC> ChildEntityConverterBuilder<S, D> addCollectionConverterFrom(OneToManyConversionAccess<S, D, SC, DC> oneToManyConversionAccess) {
//        chainedConverter.addConverter(new OneToManyCollectionConverter<S, D, SC, DC>(oneToManyConversionAccess));
//        return this;
//    }

    public <ID, SC, DC> ChildEntityConverterBuilder<S, D> addCollectionConverterFrom(SourceCollectionAccess<ID, S, SC> sourceCollectionAccess, DestinationCollectionAccess<ID, D, DC> destinationCollectionAccess) {
        chainedConverter.addConverter(new OneToManyConverter<ID, S, D, SC, DC>(sourceCollectionAccess, destinationCollectionAccess));
        return this;
    }

    public <ID, SC, DC, JC> ChildEntityConverterBuilder<S, D> addCollectionConverterFrom(SourceCollectionAccess<ID, S, SC> sourceCollectionAccess, DestinationJoinCollectionAccess<ID, D, DC, JC> destinationCollectionAccess) {
        chainedConverter.addConverter(new OneToManyJoinConverter<ID, S, D, SC, DC, JC>(sourceCollectionAccess, destinationCollectionAccess));
        return this;
    }


//    public <SC, DC, JC> ChildEntityConverterBuilder<S, D> addJoinCollectionConverterFrom(OneToManyJoinConversionAccess<S, D, SC, DC, JC> oneToManyJoinConversionAccess) {
//        chainedConverter.addConverter(new OneToManyJoinEntityConverter<S, D, SC, DC, JC>(oneToManyJoinConversionAccess));
//        return this;
//    }

    public <SC, DC> ChildEntityConverterBuilder<S, D> addChildConverterFrom(SourceChildAccess<S, SC> sourceChildAccess, DestinationChildAccess<D, DC> destinationChildAccess) {
        chainedConverter.addConverter(new OneToOneConverter<S, D, SC, DC>(sourceChildAccess, destinationChildAccess));
        return this;
    }

}