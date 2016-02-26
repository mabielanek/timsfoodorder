package com.timsmeet.services.mapper.dish;

import java.util.Collection;
import com.timsmeet.dto.Dish;
import com.timsmeet.dto.Genere;
import com.timsmeet.persistance.model.DishEntity;
import com.timsmeet.persistance.model.DishGenereEntity;
import com.timsmeet.persistance.model.GenereEntity;
import com.timsmeet.persistance.repositories.GenereRepository;
import com.timsmeet.services.mapper.OneToManyJoinConversionAccess;

public class DishGeneresConversionAccess implements OneToManyJoinConversionAccess<Dish, DishEntity, Genere, GenereEntity, DishGenereEntity>
{
    private GenereRepository genereRepository;

    public DishGeneresConversionAccess(GenereRepository genereRepository) {
        this.genereRepository = genereRepository;
    }

    @Override
    public Collection<Genere> getSourceChilds(Dish source) {
        return source.getGeneres();
    }

    @Override
    public Collection<DishGenereEntity> getJoinChilds(DishEntity destination) {
        return destination.getDishGeneres();
    }

    @Override
    public Long getSouceChildId(Genere child) {
        return child.getId();
    }

    @Override
    public Long getDestinationChildIdFromJoin(DishGenereEntity join) {
        return join.getGenere().getId();
    }

    @Override
    public void removeJoinChild(DishEntity parent, DishGenereEntity joinChild) {
        parent.removeDishGenere(joinChild);
    }

    @Override
    public void createAndAddJoinChild(DishEntity parent, GenereEntity destinationChild) {
        DishGenereEntity added = new DishGenereEntity();
        added.setDish(parent);
        added.setGenere(destinationChild);
        parent.addDishGenere(added);
    }

    @Override
    public GenereEntity findDbDestinationChild(Long childId) {
        return genereRepository.findOne(childId);
    }

}
