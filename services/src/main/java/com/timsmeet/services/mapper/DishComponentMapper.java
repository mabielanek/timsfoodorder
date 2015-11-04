package com.timsmeet.services.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.timsmeet.dto.DishComponent;
import com.timsmeet.dto.DishElement;
import com.timsmeet.persistance.model.DishComponentEntity;
import com.timsmeet.persistance.model.DishElementEntity;
import com.timsmeet.services.find.FindEntityWithIdAccessor;

@Service
public class DishComponentMapper implements Mapper<DishComponent, DishComponentEntity> {

    @Autowired
    private DishElementMapper dishElementMapper;
    
    @Autowired
    private FindEntityWithIdAccessor<DishElementEntity> dishElementFind;
    
    @Override
    public void map(DishComponent source, DishComponentEntity target) {
        if (source.getLastModificationId() != null) {
            target.setLastModificationId(source.getLastModificationId());
        }
        target.setDescription(source.getDescription());
        target.setStatus(source.getStatus());
        target.setMaximumNumberOfElements(source.getMaximumNumberOfElements());
        target.setNumberOfRequiredElements(source.getNumberOfRequiredElements());
        target.setUseComponentPriceAsDishPrice(source.getUseComponentPriceAsDishPrice());
        
        if(source.getDishElements() != null) {
            for(DishElement dishElement : source.getDishElements()) {
                if(DtoStateHelper.isDeleted(dishElement)) {
                    DishElementEntity deletedDishElement = dishElementFind.findById(target.getDishElements(), dishElement.getId());
                    if(deletedDishElement != null) {
                        target.removeDishElement(deletedDishElement);
                    }
                } else {
                    DishElementEntity dishElementEntity = existingOrNewDishElementEntity(target, dishElement);
                    dishElementMapper.map(dishElement, dishElementEntity);
                }
            }
        }
    }

    private DishElementEntity existingOrNewDishElementEntity(DishComponentEntity dishComponentEntity, DishElement dishElement) {
        if (DtoStateHelper.isNew(dishElement)) {
            DishElementEntity dishElementEntity = new DishElementEntity();
            dishComponentEntity.addDishElement(dishElementEntity);
            return dishElementEntity;
        }
        return dishElementFind.findById(dishComponentEntity.getDishElements(), dishElement.getId());
        
    }

    @Override
    public void inverseMap(DishComponentEntity source, DishComponent target) {
        target.setId(source.getId());
        target.setLastModificationId(source.getLastModificationId());
        target.setStatus(source.getStatus());
        target.setDescription(source.getDescription());
        target.setNumberOfRequiredElements(source.getNumberOfRequiredElements());
        target.setMaximumNumberOfElements(source.getMaximumNumberOfElements());
        target.setUseComponentPriceAsDishPrice(source.getUseComponentPriceAsDishPrice());
        
        if (HibernateMapperHelper.isCollectionInitialized(source.getDishElements())) {
            List<DishElement> dishElements = Lists.newArrayListWithCapacity(source.getDishElements().size());
            for (DishElementEntity dbDishElement : source.getDishElements()) {
                DishElement dishElement = new DishElement();
                dishElementMapper.inverseMap(dbDishElement, dishElement);
                dishElements.add(dishElement);
            }
            target.setDishElements(dishElements);
        }
    }

}
