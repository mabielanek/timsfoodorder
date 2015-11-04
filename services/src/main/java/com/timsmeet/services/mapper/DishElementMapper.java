package com.timsmeet.services.mapper;

import org.springframework.stereotype.Service;

import com.timsmeet.dto.DishElement;
import com.timsmeet.persistance.model.DishElementEntity;

@Service
public class DishElementMapper implements Mapper<DishElement, DishElementEntity> {

    @Override
    public void map(DishElement source, DishElementEntity target) {
        if (source.getLastModificationId() != null) {
            target.setLastModificationId(source.getLastModificationId());
        }
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setStatus(source.getStatus());
    }

    @Override
    public void inverseMap(DishElementEntity source, DishElement target) {
        target.setId(source.getId());
        target.setLastModificationId(source.getLastModificationId());
        target.setStatus(source.getStatus());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
    }

}
