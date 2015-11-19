package com.timsmeet.services.find.entity;

import com.timsmeet.dto.Dish;
import com.timsmeet.dto.DishComponent;
import com.timsmeet.dto.DishElement;
import com.timsmeet.dto.Email;
import com.timsmeet.dto.Genere;
import com.timsmeet.dto.Phone;
import com.timsmeet.dto.Vacation;
import com.timsmeet.dto.WebUrl;
import com.timsmeet.dto.WorkingHour;
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

    public static class DishElementEntityById implements IdAccessor<DishElementEntity, Long> {
        @Override
        public Long getIdValue(DishElementEntity entity) {
            return entity.getId();
        }
    }
    
    public static class DishElementById implements IdAccessor<DishElement, Long> {
        @Override
        public Long getIdValue(DishElement dishElement) {
            return dishElement.getId();
        }
    }
    
    public static class DishGenereEntityByGenereId implements IdAccessor<DishGenereEntity, Long> {
        @Override
        public Long getIdValue(DishGenereEntity entity) {
            return entity.getGenere().getId();
        }
    }

    public static class DishComponentEntityById implements IdAccessor<DishComponentEntity, Long> {
        @Override
        public Long getIdValue(DishComponentEntity entity) {
            return entity.getId();
        }
    }
    
    public static class DishComponentById implements IdAccessor<DishComponent, Long> {
        @Override
        public Long getIdValue(DishComponent dishComponent) {
            return dishComponent.getId();
        }
    }

    public static class EmailEntityById implements IdAccessor<EmailEntity, Long> {
        @Override
        public Long getIdValue(EmailEntity entity) {
            return entity.getId();
        }
    }
    
    public static class EmailById implements IdAccessor<Email, Long> {
        @Override
        public Long getIdValue(Email email) {
            return email.getId();
        }
    }

    public static class GenereEntityById implements IdAccessor<GenereEntity, Long> {
        @Override
        public Long getIdValue(GenereEntity entity) {
            return entity.getId();
        }
    }
    
    public static class GenereById implements IdAccessor<Genere, Long> {
        @Override
        public Long getIdValue(Genere genere) {
            return genere.getId();
        }
    }

    public static class PhoneEntityById implements IdAccessor<PhoneEntity, Long> {
        @Override
        public Long getIdValue(PhoneEntity entity) {
            return entity.getId();
        }
    }
    
    public static class PhoneById implements IdAccessor<Phone, Long> {
        @Override
        public Long getIdValue(Phone phone) {
            return phone.getId();
        }
    }

    public static class VacationEntityById<T extends VacationEntity> implements IdAccessor<T, Long> {
        @Override
        public Long getIdValue(T entity) {
            return entity.getId();
        }
    }
    
    public static class VacationById implements IdAccessor<Vacation, Long> {
        @Override
        public Long getIdValue(Vacation vacation) {
            return vacation.getId();
        }
    }

    public static class WebUrlEntityById implements IdAccessor<WebUrlEntity, Long> {
        @Override
        public Long getIdValue(WebUrlEntity entity) {
            return entity.getId();
        }
    }
    
    public static class WebUrlById implements IdAccessor<WebUrl, Long> {
        @Override
        public Long getIdValue(WebUrl webUrl) {
            return webUrl.getId();
        }
    }

    public static class WorkingHourEntityById<T extends WorkingHourEntity> implements IdAccessor<T, Long> {
        @Override
        public Long getIdValue(WorkingHourEntity entity) {
            return entity.getId();
        }
    }
    
    public static class WorkingHourById implements IdAccessor<WorkingHour, Long> {
        @Override
        public Long getIdValue(WorkingHour workingHour) {
            return workingHour.getId();
        }
    }
    
    public static class DishEntityById implements IdAccessor<DishEntity, Long> {
        @Override
        public Long getIdValue(DishEntity entity) {
            return entity.getId();
        }
    }
    
    public static class DishById implements IdAccessor<Dish, Long> {
        @Override
        public Long getIdValue(Dish dish) {
            return dish.getId();
        }
    }

}
