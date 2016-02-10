package com.timsmeet.dto;

import java.sql.Timestamp;
import java.util.List;
import com.timsmeet.persistance.enums.FoodOrderStatus;

public class FoodOrder {

    private Long id;
    private Long lastModificationId;
    private Person person;
    private Provider provider;
    private FoodOrderStatus orderStatus;
    private Timestamp orderAdded;
    private Timestamp orderTime;
    private List<OrderItem> orderItems;

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public FoodOrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(FoodOrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getOrderAdded() {
        return orderAdded;
    }

    public void setOrderAdded(Timestamp orderAdded) {
        this.orderAdded = orderAdded;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public final static class Builder {
        private final FoodOrder foodOrder = new FoodOrder();

        public Builder(Person person, Provider provider) {
            foodOrder.setPerson(person);
            foodOrder.setProvider(provider);
        }

        public FoodOrder build() {
            return foodOrder;
        }

        public Builder id(Long id) {
            foodOrder.setId(id);
            return this;
        }

        public Builder lastModificationId(Long lastModificationId) {
            foodOrder.setLastModificationId(lastModificationId);
            return this;
        }

        public Builder orderStatus(FoodOrderStatus orderStatus) {
            foodOrder.setOrderStatus(orderStatus);
            return this;
        }

        public Builder orderAdded(Timestamp orderAdded) {
            foodOrder.setOrderAdded(orderAdded);
            return this;
        }

        public Builder orderTime(Timestamp orderTime) {
            foodOrder.setOrderTime(orderTime);
            return this;
        }

        public Builder person(Person person) {
            foodOrder.setPerson(person);
            return this;
        }

        public Builder provider(Provider provider) {
            foodOrder.setProvider(provider);
            return this;
        }

        public Builder orderItems(List<OrderItem> orderItems) {
            foodOrder.setOrderItems(orderItems);
            return this;
        }
    }
}
