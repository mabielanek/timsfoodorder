package com.timsmeet.dto;

import java.util.List;

public class OrderItem {
    private Long id;
    private Long lastModificationId;
    private Person person;
    private Dish dish;
    private Integer count;
    private List<OrderSubItem> orderSubItems;

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

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<OrderSubItem> getOrderSubItems() {
        return orderSubItems;
    }

    public void setOrderSubItems(List<OrderSubItem> orderSubItems) {
        this.orderSubItems = orderSubItems;
    }

    public final static class Builder {
        private final OrderItem orderItem = new OrderItem();

        public Builder(Person person, Dish dish) {
            orderItem.setPerson(person);
            orderItem.setDish(dish);
        }

        public OrderItem build() {
            return orderItem;
        }

        public Builder id(Long id) {
            orderItem.setId(id);
            return this;
        }

        public Builder lastModificationId(Long lastModificationId) {
            orderItem.setLastModificationId(lastModificationId);
            return this;
        }

        public Builder dish(Dish dish) {
            orderItem.setDish(dish);
            return this;
        }

        public Builder count(Integer count) {
            orderItem.setCount(count);
            return this;
        }

        public Builder person(Person person) {
            orderItem.setPerson(person);
            return this;
        }

        public Builder orderSubItems(List<OrderSubItem> orderSubItems) {
            orderItem.setOrderSubItems(orderSubItems);
            return this;
        }
    }
}
