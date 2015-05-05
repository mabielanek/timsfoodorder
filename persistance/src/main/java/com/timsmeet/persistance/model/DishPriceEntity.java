package com.timsmeet.persistance.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
@Table(name = "fo_dish_price",
        indexes = { @Index(columnList = "dish_id", name = "idx_dish_price_dish_fk"),
                @Index(columnList = "dish_comp_id", name = "idx_dish_price_dish_comp_fk"),
                @Index(columnList = "dish_elem_id", name = "idx_dish_price_dish_elem_fk")
        })
public class DishPriceEntity {

    @Id
    @GeneratedValue(generator = "dishPriceGenerator")
    @GenericGenerator(name = "dishPriceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_fo_dish_price_id")
            })
    private long id;

    @Version
    @Column(name = "last_modification_id")
    private long lastModificationId;

    @Column(name = "last_upd_time", nullable = false)
    private Timestamp lastUpdateTime;

    @Column(name = "cost")
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = "dish_id", foreignKey = @ForeignKey(name = "dish_price_dish_fk"))
    private DishEntity dish;

    @ManyToOne
    @JoinColumn(name = "dish_comp_id", foreignKey = @ForeignKey(name = "dish_price_dish_comp_fk"))
    private DishComponentEntity dishComponent;

    @ManyToOne
    @JoinColumn(name = "dish_elem_id", foreignKey = @ForeignKey(name = "dish_price_dish_elem_fk"))
    private DishElementEntity dishElement;

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public DishEntity getDish() {
        return dish;
    }

    public void setDish(DishEntity dish) {
        this.dish = dish;
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
