package com.timsmeet.services.find.entity;

import com.timsmeet.persistance.model.DishComponentEntity;
import com.timsmeet.persistance.model.DishElementEntity;
import com.timsmeet.persistance.model.DishEntity;
import com.timsmeet.persistance.model.DishGenereEntity;
import com.timsmeet.persistance.model.EmailEntity;
import com.timsmeet.persistance.model.GenereEntity;
import com.timsmeet.persistance.model.PhoneEntity;
import com.timsmeet.persistance.model.VacationEntity;
import com.timsmeet.persistance.model.WebUrlEntity;
import com.timsmeet.persistance.model.WorkingHourEntity;

public class IdAccessors {

    private IdAccessors() {
    }

    public static class DishElementById implements IdAccessor<DishElementEntity, Long> {
        @Override
        public Long getIdValue(DishElementEntity entity) {
            return entity.getId();
        }
    }

    public static class DishGenereByGenereId implements IdAccessor<DishGenereEntity, Long> {
        @Override
        public Long getIdValue(DishGenereEntity entity) {
            return entity.getGenere().getId();
        }
    }

    public static class DishComponentById implements IdAccessor<DishComponentEntity, Long> {
        @Override
        public Long getIdValue(DishComponentEntity entity) {
            return entity.getId();
        }
    }

    public static class EmailById implements IdAccessor<EmailEntity, Long> {
        @Override
        public Long getIdValue(EmailEntity entity) {
            return entity.getId();
        }
    }

    public static class GenereById implements IdAccessor<GenereEntity, Long> {
        @Override
        public Long getIdValue(GenereEntity entity) {
            return entity.getId();
        }
    }

    public static class PhoneById implements IdAccessor<PhoneEntity, Long> {
        @Override
        public Long getIdValue(PhoneEntity entity) {
            return entity.getId();
        }
    }

    public static class VacationById<T extends VacationEntity> implements IdAccessor<T, Long> {
        @Override
        public Long getIdValue(T entity) {
            return entity.getId();
        }
    }

    public static class WebUrlById implements IdAccessor<WebUrlEntity, Long> {
        @Override
        public Long getIdValue(WebUrlEntity entity) {
            return entity.getId();
        }
    }

    public static class WorkingHourById<T extends WorkingHourEntity> implements IdAccessor<T, Long> {
        @Override
        public Long getIdValue(WorkingHourEntity entity) {
            return entity.getId();
        }
    }
    
    public static class DishById implements IdAccessor<DishEntity, Long> {
        @Override
        public Long getIdValue(DishEntity entity) {
            return entity.getId();
        }
    }

}
