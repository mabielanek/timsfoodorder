package com.timsmeet.persistance.model;

import java.sql.Timestamp;
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
import com.timsmeet.persistance.enums.FoodOrderStatus;

@Entity
@Table(name = DbTable.FoodOrder.TABLE,
        indexes = {
                @Index(columnList = DbTable.FoodOrder.PERSON_ID, name = "idx_food_order_person_fk"),
                @Index(columnList = DbTable.FoodOrder.PROVIDER_ID, name = "idx_food_order_provider_fk"),
                @Index(columnList = DbTable.FoodOrder.ORDER_TIME, name = "idx_order_time") })
@org.hibernate.annotations.Check(constraints = "orderStatus IN('A','C','D','L')")
public class FoodOrderEntity {

    @Id
    @GeneratedValue(generator = "foodOrderGenerator")
    @GenericGenerator(name = "foodOrderGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_fo_food_ord_id")
            })
    private long id;

    @Version
    @Column(name = DbTable.FoodOrder.LAST_MODIFICATION_ID)
    private long lastModificationId;

    @ManyToOne
    @JoinColumn(name = DbTable.FoodOrder.PERSON_ID, foreignKey = @ForeignKey(name = "food_order_person_fk"))
    private PersonEntity person;

    @ManyToOne
    @JoinColumn(name = DbTable.FoodOrder.PROVIDER_ID, foreignKey = @ForeignKey(name = "food_order_provider_fk"))
    private ProviderEntity provider;

    @Column(name = DbTable.FoodOrder.ORDER_STATUS, nullable = false, length = 1)
    private String orderStatus;

    @Column(name = DbTable.FoodOrder.ADD_TIME, nullable = false)
    private Timestamp orderAdded;

    @Column(name = DbTable.FoodOrder.ORDER_TIME, nullable = false)
    private Timestamp orderTime;

    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JoinColumn(name = DbTable.OrderItem.FOOD_ORDER_ID)
    private List<OrderItemEntity> orderItems = new ArrayList<OrderItemEntity>();

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public ProviderEntity getProvider() {
        return provider;
    }

    public void setProvider(ProviderEntity provider) {
        this.provider = provider;
    }

    public FoodOrderStatus getOrderStatus() {
        return FoodOrderStatus.forCode(orderStatus);
    }

    public void setOrderStatus(FoodOrderStatus orderStatus) {
        this.orderStatus = orderStatus.getCode();
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

    public long getId() {
        return id;
    }

    public long getLastModificationId() {
        return lastModificationId;
    }

    public List<OrderItemEntity> getOrderItems() {
        return orderItems;
    }

    public boolean addOrderItem(OrderItemEntity orderItem) {
        if (this.orderItems == null) {
            this.orderItems = Lists.newArrayList();
        }
        orderItem.setFoodOrder(this);
        return this.orderItems.add(orderItem);
    }

    public boolean removeOrderItem(OrderItemEntity orderItem) {
        Preconditions.checkNotNull(orderItem);
        orderItem.setFoodOrder(null);
        if (this.orderItems != null) {
            return this.orderItems.remove(orderItem);
        }
        return false;
    }
}
