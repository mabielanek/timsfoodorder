package com.timsmeet.services.mapper;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.google.common.collect.Lists;
import com.timsmeet.dto.entity.BaseEntity;

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
    
//  public ChildCollectionConverterBuilder<S, D> addChildCollectionConverter(Converter<S,D> childCollectionConverter) {
//      chainedConverter.addConverter(childCollectionConverter);
//      return this;
//  }
    
    public <SC extends BaseEntity, DC> ChildEntityConverterBuilder<S, D> addChildCollectionConverterFrom(OneToManyConversionAccess<S, D, SC, DC> oneToManyConversionAccess) {
        chainedConverter.addConverter(new OneToManyCollectionConverter<S, D, SC, DC>(oneToManyConversionAccess));
        return this;
    }
    
    public <SC extends BaseEntity, DC> ChildEntityConverterBuilder<S, D> addChildEntityConverterFrom(OneToOneConversionAccess<S, D, SC, DC> oneToOneConversionAccess) {
        chainedConverter.addConverter(new OneToOneEntityConverter<S, D, SC, DC>(oneToOneConversionAccess));
        return this;
    }
    
}