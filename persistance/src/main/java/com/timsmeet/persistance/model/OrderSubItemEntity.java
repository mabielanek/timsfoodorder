package com.timsmeet.persistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "fo_order_sub_item",
        indexes = {
                @Index(columnList = "order_item_id", name = "idx_ord_sub_item_ord_item_fk"),
                @Index(columnList = "dish_comp_id", name = "idx_ord_subitem_dish_comp_fk"),
                @Index(columnList = "dish_elem_id", name = "idx_ord_subitem_dish_elem_fk") })
public class OrderSubItemEntity {

    @Id
    @GeneratedValue(generator = "orderSubItemGenerator")
    @GenericGenerator(name = "orderSubItemGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = { @Parameter(name = "sequence_name", value = "seq_fo_sub_ord_item_id") })
    private long id;

    @Version
    @Column(name = "last_modification_id")
    private long lastModificationId;

    @ManyToOne
    @JoinColumn(name = "order_item_id", foreignKey = @ForeignKey(name = "ord_sub_item_ord_item_fk"))
    private OrderItemEntity orderItem;

    @ManyToOne
    @JoinColumn(name = "dish_comp_id", foreignKey = @ForeignKey(name = "ord_sub_item_dish_comp_fk"))
    private DishComponentEntity dishComponent;

    @ManyToOne
    @JoinColumn(name = "dish_elem_id", foreignKey = @ForeignKey(name = "ord_sub_item_dish_elem_fk"))
    private DishElementEntity dishElement;

    public OrderItemEntity getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItemEntity orderItem) {
        this.orderItem = orderItem;
    }

    public DishComponentEntity getDishComponent() {
        return dishComponent;
    }

    public void setDishComponent(DishComponentEntity dishComponent) {
        this.dishComponent = dishComponent;
    }

    public DishElementEntity getDishElement() {
        return dishElement;
    }

    public void setDishElement(DishElementEntity dishElement) {
        this.dishElement = dishElement;
    }

    public long getId() {
        return id;
    }

    public long getLastModificationId() {
        return lastModificationId;
    }

}
