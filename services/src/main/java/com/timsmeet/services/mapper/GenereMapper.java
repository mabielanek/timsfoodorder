package com.timsmeet.services.mapper;

import org.springframework.stereotype.Service;

import com.timsmeet.dto.Genere;
import com.timsmeet.persistance.model.GenereEntity;

@Service
public class GenereMapper implements Mapper<Genere, GenereEntity> {

    @Override
    public void map(Genere source, GenereEntity target) {
        // TODO Auto-generated method stub

    }

    @Override
    public void inverseMap(GenereEntity source, Genere target) {
        // TODO Auto-generated method stub

    }

}
