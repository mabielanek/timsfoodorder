package com.timsmeet.services.mapper.dish;

import java.util.Collection;
import com.timsmeet.persistance.model.DishEntity;
import com.timsmeet.persistance.model.DishGenereEntity;
import com.timsmeet.persistance.model.GenereEntity;
import com.timsmeet.persistance.repositories.GenereRepository;
import com.timsmeet.services.mapper.DestinationJoinCollectionAccess;

public class DishGeneresJoinEntityAccess implements DestinationJoinCollectionAccess<Long, DishEntity, GenereEntity, DishGenereEntity> {

    private GenereRepository genereRepository;

    public DishGeneresJoinEntityAccess(GenereRepository genereRepository) {
        this.genereRepository = genereRepository;
    }

    @Override
    public Collection<DishGenereEntity> getJoinChilds(DishEntity destination) {
        return destination.getDishGeneres();
    }

    @Override
    public Long getChildIdFromJoin(DishGenereEntity join) {
        return join.getGenere().getId();
    }

    @Override
    public void removeJoin(DishEntity parent, DishGenereEntity joinChild) {
        parent.removeDishGenere(joinChild);
    }

    @Override
    public void createAndAddJoin(DishEntity parent, GenereEntity destinationChild) {
        DishGenereEntity added = new DishGenereEntity();
        added.setDish(parent);
        added.setGenere(destinationChild);
        parent.addDishGenere(added);
    }

    @Override
    public GenereEntity findDbChild(Long childId) {
        return genereRepository.findOne(childId);
    }
}
