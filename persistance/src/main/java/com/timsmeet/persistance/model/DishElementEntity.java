package com.timsmeet.persistance.model;

import java.math.BigDecimal;
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
import com.timsmeet.persistance.enums.ActivityStatus;

@Entity
@Table(name = DbTable.DishElement.TABLE,
        indexes = @Index(columnList = DbTable.DishElement.DISH_COMP_ID, name = "idx_dish_elem_dish_comp_fk"))
@org.hibernate.annotations.Check(constraints = "status IN('ACTIVE','INACTIVE','DELETED')")
public class DishElementEntity {

    @Id
    @GeneratedValue(generator = "dishElementGenerator")
    @GenericGenerator(name = "dishElementGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_fo_dish_elem_id")
            })
    private long id;

    @Version
    @Column(name = DbTable.DishElement.LAST_MODIFICATION_ID)
    private long lastModificationId;

    @Column(name = DbTable.DishElement.STATUS, nullable = false, length = 15)
    private String status;

    @ManyToOne
    @JoinColumn(name = DbTable.DishElement.DISH_COMP_ID, foreignKey = @ForeignKey(name = "dish_elem_dish_comp_fk"))
    private DishComponentEntity dishComponent;

    @Column(name = DbTable.DishElement.NAME, length = 255)
    private String name;

    @Column(name = DbTable.DishElement.DESCRIPTION, length = 255)
    private String description;

    @Column(name = DbTable.DishElement.PRICE)
    private BigDecimal price;

    public ActivityStatus getStatus() {
        return ActivityStatus.forCode(status);
    }

    public void setStatus(ActivityStatus status) {
        this.status = status.getCode();
    }

    public DishComponentEntity getDishComponent() {
        return dishComponent;
    }

    public void setDishComponent(DishComponentEntity dishComponent) {
        this.dishComponent = dishComponent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public long getLastModificationId() {
        return lastModificationId;
    }

    public void setLastModificationId(long lastModificationId) {
        this.lastModificationId = lastModificationId;
    }

}
