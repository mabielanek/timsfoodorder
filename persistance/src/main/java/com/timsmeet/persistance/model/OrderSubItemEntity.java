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

import com.timsmeet.persistance.constants.DbTable;

@Entity
@Table(name = DbTable.OrderSubItem.TABLE,
        indexes = {
                @Index(columnList = DbTable.OrderSubItem.ORDER_ITEM_ID, name = "idx_ord_sub_item_ord_item_fk"),
                @Index(columnList = DbTable.OrderSubItem.DISH_COMP_ID, name = "idx_ord_subitem_dish_comp_fk"),
                @Index(columnList = DbTable.OrderSubItem.DISH_ELEM_ID, name = "idx_ord_subitem_dish_elem_fk") })
public class OrderSubItemEntity {

    @Id
    @GeneratedValue(generator = "orderSubItemGenerator")
    @GenericGenerator(name = "orderSubItemGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = { @Parameter(name = "sequence_name", value = "seq_fo_sub_ord_item_id") })
    private long id;

    @Version
    @Column(name = DbTable.OrderSubItem.LAST_MODIFICATION_ID)
    private long lastModificationId;

    @ManyToOne
    @JoinColumn(name = DbTable.OrderSubItem.ORDER_ITEM_ID, foreignKey = @ForeignKey(name = "ord_sub_item_ord_item_fk"))
    private OrderItemEntity orderItem;

    @ManyToOne
    @JoinColumn(name = DbTable.OrderSubItem.DISH_COMP_ID, foreignKey = @ForeignKey(name = "ord_sub_item_dish_comp_fk"))
    private DishComponentEntity dishComponent;

    @ManyToOne
    @JoinColumn(name = DbTable.OrderSubItem.DISH_ELEM_ID, foreignKey = @ForeignKey(name = "ord_sub_item_dish_elem_fk"))
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
