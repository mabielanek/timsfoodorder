package com.timsmeet.services.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.timsmeet.dto.Dish;
import com.timsmeet.dto.DishComponent;
import com.timsmeet.dto.Genere;
import com.timsmeet.persistance.model.DishComponentEntity;
import com.timsmeet.persistance.model.DishEntity;
import com.timsmeet.persistance.model.DishGenereEntity;
import com.timsmeet.persistance.model.GenereEntity;
import com.timsmeet.persistance.repositories.GenereRepository;
import com.timsmeet.services.find.FindEntityWithIdAccessor;

@Service
public class DishMapper implements Mapper<Dish, DishEntity> {

    @Autowired
    private DishComponentMapper dishComponentMapper;

    @Autowired
    private FindEntityWithIdAccessor<DishComponentEntity> dishComponentFind;
    
    @Autowired
    private FindEntityWithIdAccessor<DishGenereEntity> dishGenereFind;
    
    @Autowired
    private GenereRepository genereRepository;

    @Override
	public void map(Dish source, DishEntity target) {
        if (source.getLastModificationId() != null) {
            target.setLastModificationId(source.getLastModificationId());
        }
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setStatus(source.getStatus());
        target.setAvaiabilityEndDay(source.getAvaiabilityEndDay());
        target.setAvaiabilityStartDay(source.getAvaiabilityStartDay());
        
        if(source.getDishComponents() != null) {
            for(DishComponent dishComponent : source.getDishComponents()) {
                if(DtoStateHelper.isDeleted(dishComponent)) {
                    DishComponentEntity deletedDishComponent = dishComponentFind.findById(target.getDishComponents(), dishComponent.getId());
                    if(deletedDishComponent != null) {
                        target.removeDishComponent(deletedDishComponent);
                    }
                } else {
                    DishComponentEntity dishComponentEntity = existingOrNewDishComponentEntity(target, dishComponent);
                    dishComponentMapper.map(dishComponent, dishComponentEntity);
                }
            }
        }

		if(source.getGeneres() != null) {
		    for(Genere genere : source.getGeneres()) {
		        if(DtoStateHelper.isDeleted(genere)) {
		            DishGenereEntity deletedDishGenere = dishGenereFind.findById(target.getDishGneres(), genere.getId());
		            if(deletedDishGenere != null) {
		                target.removeDishGenere(deletedDishGenere);
		            }
		        } if(DtoStateHelper.isNew(genere)) {
		            DishGenereEntity added = new DishGenereEntity();
		            added.setDish(target);
		            GenereEntity dbGenere = genereRepository.findOne(genere.getId());
		            added.setGenere(dbGenere);
		            target.addDishGenere(added);
		        }
		    }
		}
	}

	private DishComponentEntity existingOrNewDishComponentEntity(DishEntity dishEntity, DishComponent dishComponent) {
        if (DtoStateHelper.isNew(dishComponent)) {
            DishComponentEntity dishComponentEntity = new DishComponentEntity();
            dishEntity.addDishComponent(dishComponentEntity);
            return dishComponentEntity;
        }
        return dishComponentFind.findById(dishEntity.getDishComponents(), dishComponent.getId());
    }

    @Override
	public void inverseMap(DishEntity source, Dish target) {
        target.setId(source.getId());
        target.setLastModificationId(source.getLastModificationId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setStatus(source.getStatus());
        target.setAvaiabilityEndDay(source.getAvaiabilityEndDay());
        target.setAvaiabilityStartDay(source.getAvaiabilityStartDay());
        
        if (HibernateMapperHelper.isCollectionInitialized(source.getDishComponents())) {
            List<DishComponent> dishComponents = Lists.newArrayListWithCapacity(source.getDishComponents().size());
            for (DishComponentEntity dbDishComponent : source.getDishComponents()) {
                DishComponent dishComponent = new DishComponent();
                dishComponentMapper.inverseMap(dbDishComponent, dishComponent);
                dishComponents.add(dishComponent);
            }
            target.setDishComponents(dishComponents);
        }
        
        if(HibernateMapperHelper.isCollectionInitialized(source.getDishGneres())) {
            List<Genere> dishGeneres = Lists.newArrayListWithCapacity(source.getDishGneres().size());
            for(DishGenereEntity dbDishGenere : source.getDishGneres()) {
                Genere dishGenere = new Genere();
                dishGenere.setId(dbDishGenere.getGenere().getId());
                dishGenere.setLastModificationId(dbDishGenere.getGenere().getLastModificationId());
                dishGenere.setName(dbDishGenere.getGenere().getName());
                dishGeneres.add(dishGenere);
            }
            target.setGeneres(dishGeneres);
        }
	}

}
