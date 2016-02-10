package com.timsmeet.dto;


public class OrderSubItem {
    private Long id;
    private Long lastModificationId;
    private OrderItem orderItem;
    private DishComponent dishComponent;
    private DishElement dishElement;

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

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public DishComponent getDishComponent() {
        return dishComponent;
    }

    public void setDishComponent(DishComponent dishComponent) {
        this.dishComponent = dishComponent;
    }

    public DishElement getDishElement() {
        return dishElement;
    }

    public void setDishElement(DishElement dishElement) {
        this.dishElement = dishElement;
    }

    public final static class Builder {
        private final OrderSubItem orderSubItem = new OrderSubItem();

        public Builder() { }

        public OrderSubItem build() {
            return orderSubItem;
        }

        public Builder id(Long id) {
            orderSubItem.setId(id);
            return this;
        }

        public Builder lastModificationId(Long lastModificationId) {
            orderSubItem.setLastModificationId(lastModificationId);
            return this;
        }

        public Builder dishComponent(DishComponent dishComponent) {
            orderSubItem.setDishComponent(dishComponent);
            return this;
        }

        public Builder dishElement(DishElement dishElement) {
            orderSubItem.setDishElement(dishElement);
            return this;
        }
    }
}
