package com.timsmeet.persistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.timsmeet.persistance.constants.DbTable;

@Entity
@Table(name = DbTable.FoodOrderForGroup.TABLE)
public class FoodOrderForGroupEntity {
    @Id
    @GeneratedValue(generator = "foodOrderForGroupGenerator")
    @GenericGenerator(name = "foodOrderForGroupGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_fo_food_ord_grp_id")
            })
    private long id;

    @Version
    @Column(name = DbTable.FoodOrderForGroup.LAST_MODIFICATION_ID)
    private long lastModificationId;

    @ManyToOne
    @JoinColumn(name = DbTable.FoodOrderForGroup.FOOD_ORDER, foreignKey = @ForeignKey(name = "foodordgrp_foodord_fk"))
    private FoodOrderEntity foodOrder;

    @ManyToOne
    @JoinColumn(name = DbTable.FoodOrderForGroup.GROUP, foreignKey = @ForeignKey(name = "foodordgrp_group_fk"))
    private GroupEntity group;

    public long getLastModificationId() {
        return lastModificationId;
    }

    public void setLastModificationId(long lastModificationId) {
        this.lastModificationId = lastModificationId;
    }

    public FoodOrderEntity getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(FoodOrderEntity foodOrder) {
        this.foodOrder = foodOrder;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public long getId() {
        return id;
    }


}
