package com.timsmeet.services.mapper;

import org.springframework.stereotype.Service;
import com.timsmeet.dto.AdditionalCost;
import com.timsmeet.persistance.model.AdditionalCostEntity;

@Service
public class AdditionalCostMapper implements Mapper<AdditionalCost, AdditionalCostEntity> {

    @Override
    public void map(AdditionalCost source, AdditionalCostEntity target) {
        if (source.getLastModificationId() != null) {
            target.setLastModificationId(source.getLastModificationId());
        }
        target.setCost(source.getCost());
        target.setKind(source.getKind());
        target.setStatus(source.getStatus());
    }

    @Override
    public void inverseMap(AdditionalCostEntity source, AdditionalCost target) {
        target.setId(source.getId());
        target.setLastModificationId(source.getLastModificationId());
        target.setCost(source.getCost());
        target.setKind(source.getKind());
        target.setStatus(source.getStatus());
    }

}
