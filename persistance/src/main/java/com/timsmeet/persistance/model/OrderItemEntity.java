package com.timsmeet.persistance.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.timsmeet.persistance.constants.DbTable;

@Entity
@Table(name = DbTable.OrderItem.TABLE,
        indexes = {
                @Index(columnList = DbTable.OrderItem.PERSON_ID, name = "idx_order_item_person_fk"),
                @Index(columnList = DbTable.OrderItem.FOOD_ORDER_ID, name = "idx_order_item_food_ord_fk"),
                @Index(columnList = DbTable.OrderItem.DISH_ID, name = "idx_order_item_dish_fk") })
public class OrderItemEntity {

    @Id
    @GeneratedValue(generator = "orderItemGenerator")
    @GenericGenerator(name = "orderItemGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = { @Parameter(name = "sequence_name", value = "seq_fo_ord_item_id") })
    private long id;

    @Version
    @Column(name = DbTable.OrderItem.LAST_MODIFICATION_ID)
    private long lastModificationId;

    @ManyToOne
    @JoinColumn(name = DbTable.OrderItem.PERSON_ID, foreignKey = @ForeignKey(name = "order_item_person_fk"))
    private PersonEntity person;

    @ManyToOne
    @JoinColumn(name = DbTable.OrderItem.FOOD_ORDER_ID, foreignKey = @ForeignKey(name = "order_item_food_ord_fk"))
    private FoodOrderEntity foodOrder;

    @ManyToOne
    @JoinColumn(name = DbTable.OrderItem.DISH_ID, foreignKey = @ForeignKey(name = "order_item_dish_fk"))
    private DishEntity dish;

    @Column(name = DbTable.OrderItem.COUNT, nullable = false)
    private int count;

    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JoinColumn(name = DbTable.OrderSubItem.ORDER_ITEM_ID)
    private List<OrderSubItemEntity> orderSubItems = new ArrayList<OrderSubItemEntity>();

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public FoodOrderEntity getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(FoodOrderEntity foodOrder) {
        this.foodOrder = foodOrder;
    }

    public DishEntity getDish() {
        return dish;
    }

    public void setDish(DishEntity dish) {
        this.dish = dish;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public long getLastModificationId() {
        return lastModificationId;
    }

    public List<OrderSubItemEntity> getOrderSubItems() {
        return orderSubItems;
    }

    public boolean addOrderSubItem(OrderSubItemEntity orderSubItem) {
        if (this.orderSubItems == null) {
            this.orderSubItems = Lists.newArrayList();
        }
        orderSubItem.setOrderItem(this);
        return this.orderSubItems.add(orderSubItem);
    }

    public boolean removeOrderSubItem(OrderSubItemEntity orderSubItem) {
        Preconditions.checkNotNull(orderSubItem);
        orderSubItem.setOrderItem(null);
        if (this.orderSubItems != null) {
            return this.orderSubItems.remove(orderSubItem);
        }
        return false;
    }

}
