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
import com.timsmeet.persistance.enums.AdditionalCostKind;

@Entity
@Table(name = DbTable.AdditionalCost.TABLE,
        indexes = @Index(columnList = DbTable.AdditionalCost.PROVIDER_ID, name = "idx_add_cost_provider_fk"))
@org.hibernate.annotations.Check(constraints = "status IN('ACTIVE','INACTIVE','DELETED') and kind IN('MINVAL','DELIVERY','PACK')")
public class AdditionalCostEntity {

    @Id
    @GeneratedValue(generator = "addCostGenerator")
    @GenericGenerator(name = "addCostGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_fo_add_cost_id")
            })
    private long id;

    @Version
    @Column(name = DbTable.AdditionalCost.LAST_MODIFICATION_ID)
    private long lastModificationId;

    @Column(name = DbTable.AdditionalCost.STATUS, nullable = false, length = 15)
    private String status;

    @Column(name = DbTable.AdditionalCost.KIND, nullable = false, length = 15)
    private String kind;

    @Column(name = DbTable.AdditionalCost.COST)
    private BigDecimal cost;

    @ManyToOne
    @JoinColumn(name = DbTable.AdditionalCost.PROVIDER_ID, foreignKey = @ForeignKey(name = "add_cost_provider_fk"))
    private ProviderEntity provider;

    public ActivityStatus getStatus() {
        return ActivityStatus.forCode(status);
    }

    public void setStatus(ActivityStatus status) {
        this.status = status.getCode();
    }

    public AdditionalCostKind getKind() {
        return AdditionalCostKind.forCode(kind);
    }

    public void setKind(AdditionalCostKind kind) {
        this.kind = kind.getCode();
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public ProviderEntity getProvider() {
        return provider;
    }

    public void setProvider(ProviderEntity provider) {
        this.provider = provider;
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
