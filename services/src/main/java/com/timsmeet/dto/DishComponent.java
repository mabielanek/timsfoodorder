package com.timsmeet.dto;

import java.math.BigDecimal;
import java.util.List;
import com.timsmeet.persistance.enums.ActivityStatus;
import com.timsmeet.persistance.enums.YesNo;

public class DishComponent {

    private Long id;
    private Long lastModificationId;
    private ActivityStatus status;
    private String description;
    private YesNo useComponentPriceAsDishPrice;
    private Integer numberOfRequiredElements;
    private Integer maximumNumberOfElements;
    private BigDecimal price;
    private List<DishElement> dishElements;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLastModificationId() {
        return lastModificationId;
    }

    public void setLastModificationId(Long lastModificationId) {
        this.lastModificationId = lastModificationId;
    }

    public ActivityStatus getStatus() {
        return status;
    }

    public void setStatus(ActivityStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public YesNo getUseComponentPriceAsDishPrice() {
        return useComponentPriceAsDishPrice;
    }

    public void setUseComponentPriceAsDishPrice(YesNo useComponentPriceAsDishPrice) {
        this.useComponentPriceAsDishPrice = useComponentPriceAsDishPrice;
    }

    public Integer getNumberOfRequiredElements() {
        return numberOfRequiredElements;
    }

    public void setNumberOfRequiredElements(Integer numberOfRequiredElements) {
        this.numberOfRequiredElements = numberOfRequiredElements;
    }

    public Integer getMaximumNumberOfElements() {
        return maximumNumberOfElements;
    }

    public void setMaximumNumberOfElements(Integer maximumNumberOfElements) {
        this.maximumNumberOfElements = maximumNumberOfElements;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<DishElement> getDishElements() {
        return dishElements;
    }

    public void setDishElements(List<DishElement> dishElements) {
        this.dishElements = dishElements;
    }

    public final static class Builder {
        private final DishComponent dishComponent = new DishComponent();

        public Builder(String description, ActivityStatus status) {
            dishComponent.setDescription(description);
            dishComponent.setStatus(status);
        }

        public DishComponent build() {
            return dishComponent;
        }

        public Builder id(Long id) {
            dishComponent.setId(id);
            return this;
        }

        public Builder lastModificationId(Long lastModificationId) {
            dishComponent.setLastModificationId(lastModificationId);
            return this;
        }

        public Builder status(ActivityStatus status) {
            dishComponent.setStatus(status);
            return this;
        }

        public Builder description(String description) {
            dishComponent.setDescription(description);
            return this;
        }

        public Builder useComponentPriceAsDishPrice(YesNo useComponentPriceAsDishPrice) {
            dishComponent.setUseComponentPriceAsDishPrice(useComponentPriceAsDishPrice);
            return this;
        }

        public Builder numberOfRequiredElements(Integer numberOfRequiredElements) {
            dishComponent.setNumberOfRequiredElements(numberOfRequiredElements);
            return this;
        }

        public Builder maximumNumberOfElements(Integer maximumNumberOfElements) {
            dishComponent.setMaximumNumberOfElements(maximumNumberOfElements);
            return this;
        }

        public Builder price(BigDecimal price) {
            dishComponent.setPrice(price);;
            return this;
        }

        public Builder dishElements(List<DishElement> dishElements) {
            dishComponent.setDishElements(dishElements);
            return this;
        }

    }
}
