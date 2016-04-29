package com.timsmeet.services.mapper.dish;

import java.util.Collection;
import com.timsmeet.dto.Dish;
import com.timsmeet.dto.Genere;
import com.timsmeet.services.mapper.SourceCollectionAccess;

public class DishGeneresAccess implements SourceCollectionAccess<Long, Dish, Genere> {

    @Override
    public Collection<Genere> getChilds(Dish source) {
        return source.getGeneres();
    }

    @Override
    public Long getChildId(Genere child) {
        return child.getId();
    }

}
