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
import com.timsmeet.persistance.enums.ActivityStatus;

@Entity
@Table(name = "fo_dish",
        indexes = @Index(columnList = "provider_id", name = "idx_dish_provider_fk"))
public class DishEntity {

    @Id
    @GeneratedValue(generator = "dishGenerator")
    @GenericGenerator(name = "dishGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_fo_dish_id")
            })
    private long id;

    @Version
    @Column(name = "last_modification_id")
    private long lastModificationId;

    @Column(name = "status", nullable = false, length = 1)
    @org.hibernate.annotations.Check(constraints = "status IN('A','I','D')")
    private String status;

    @ManyToOne
    @JoinColumn(name = "provider_id", foreignKey = @ForeignKey(name = "dish_provider_fk"))
    private ProviderEntity provider;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "start_day", nullable = false)
    private Timestamp avaiabilityStartDay;

    @Column(name = "end_day", nullable = false)
    private Timestamp avaiabilityEndDay;

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "dish_id")
    private List<DishComponentEntity> dishComponents = new ArrayList<DishComponentEntity>();

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "dish_id")
    private List<DishGenereEntity> dishGeneres = new ArrayList<DishGenereEntity>();

    public ActivityStatus getStatus() {
        return ActivityStatus.forCode(status);
    }

    public void setStatus(ActivityStatus status) {
        this.status = status.getCode();
    }

    public ProviderEntity getProvider() {
        return provider;
    }

    public void setProvider(ProviderEntity provider) {
        this.provider = provider;
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

    public Timestamp getAvaiabilityStartDay() {
        return avaiabilityStartDay;
    }

    public void setAvaiabilityStartDay(Timestamp avaiabilityStartDay) {
        this.avaiabilityStartDay = avaiabilityStartDay;
    }

    public Timestamp getAvaiabilityEndDay() {
        return avaiabilityEndDay;
    }

    public void setAvaiabilityEndDay(Timestamp avaiabilityEndDay) {
        this.avaiabilityEndDay = avaiabilityEndDay;
    }

    public long getId() {
        return id;
    }

    public long getLastModificationId() {
        return lastModificationId;
    }

    public List<DishComponentEntity> getDishComponents() {
        return dishComponents;
    }

    public boolean addDishComponent(DishComponentEntity dishComponent) {
        if (this.dishComponents == null) {
            this.dishComponents = Lists.newArrayList();
        }
        dishComponent.setDish(this);
        return this.dishComponents.add(dishComponent);
    }

    public boolean removeDishComponent(DishComponentEntity dishComponent) {
        Preconditions.checkNotNull(dishComponent);
        dishComponent.setDish(null);
        if (this.dishComponents != null) {
            return this.dishComponents.remove(dishComponent);
        }
        return false;
    }

    public List<DishGenereEntity> getDishGneres() {
        return dishGeneres;
    }

    public boolean addDishGenere(DishGenereEntity dishGenere) {
        if (this.dishGeneres == null) {
            this.dishGeneres = Lists.newArrayList();
        }
        dishGenere.setDish(this);
        return this.dishGeneres.add(dishGenere);
    }

    public boolean removeDishComponent(DishGenereEntity dishGenere) {
        Preconditions.checkNotNull(dishGenere);
        dishGenere.setDish(null);
        if (this.dishGeneres != null) {
            return this.dishGeneres.remove(dishGenere);
        }
        return false;
    }

}
