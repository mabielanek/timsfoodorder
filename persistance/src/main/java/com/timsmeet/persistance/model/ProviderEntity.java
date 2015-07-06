/*
 *
 */
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.timsmeet.persistance.constants.DbTable;
import com.timsmeet.persistance.enums.ActivityStatus;

/**
 * Stores information about provider.
 */
@Entity
@Table(name = DbTable.Provider.TABLE,
        indexes = {
                @Index(columnList = DbTable.Provider.NAME, name = "idx_provider_name"),
                @Index(columnList = DbTable.Provider.ADDRESS_ID, name = "idx_provider_address_fk"),
                @Index(columnList = DbTable.Provider.CONTACT_ID, name = "idx_provider_contact_fk") })
public class ProviderEntity {

    @Id
    @GeneratedValue(generator = "providerGenerator")
    @GenericGenerator(name = "providerGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_fo_provider_id"),
                    @Parameter(name = "initial_value", value = "100")
            })
    private long id;

    @Version
    @Column(name = DbTable.Provider.LAST_MODIFICATION_ID)
    private long lastModificationId;

    @Column(name = DbTable.Provider.STATUS, nullable = false, length = 1)
    @org.hibernate.annotations.Check(constraints = "status IN('A','I','D')")
    private String status;

    @Column(name = DbTable.Provider.NAME, nullable = false, length = 255)
    private String name;

    @Column(name = DbTable.Provider.COMMENT, nullable = false, length = 255)
    private String comment;

    @OneToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = DbTable.Provider.ADDRESS_ID, foreignKey = @ForeignKey(name = "provider_address_fk"))
    private AddressEntity address;

    @OneToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = DbTable.Provider.CONTACT_ID, foreignKey = @ForeignKey(name = "provider_contact_fk"))
    private ContactEntity contact;

    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JoinColumn(name = DbTable.WorkingHour.PROVIDER_ID)
    private List<ProviderWorkingHourEntity> workingHours = new ArrayList<ProviderWorkingHourEntity>();

    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JoinColumn(name = DbTable.Vacation.PROVIDER_ID)
    private List<ProviderVacationEntity> vacations = new ArrayList<ProviderVacationEntity>();

    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JoinColumn(name = DbTable.AdditionalCost.PROVIDER_ID)
    private List<AdditionalCostEntity> additionalCosts = new ArrayList<AdditionalCostEntity>();

    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    @JoinColumn(name = DbTable.Dish.PROVIDER_ID)
    private List<DishEntity> dishes = new ArrayList<DishEntity>();

    /**
     * Gets the record status.
     *
     * @return the status
     */
    public ActivityStatus getStatus() {
        return ActivityStatus.forCode(status);
    }

    /**
     * Sets the record status.
     *
     * @param status
     *            the new status
     */
    public void setStatus(ActivityStatus status) {
        this.status = status.getCode();
    }

    /**
     * Gets the name of the company/
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the company.
     *
     * @param name
     *            the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the address of the company.
     *
     * @return the address
     */
    public AddressEntity getAddress() {
        return address;
    }

    /**
     * Sets the address of the company.
     *
     * @param address
     *            the new address
     */
    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    /**
     * Gets the contact for the company.
     *
     * @return the contact
     */
    public ContactEntity getContact() {
        return contact;
    }

    /**
     * Sets the contact for the company.
     *
     * @param contact
     *            the new contact
     */
    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }

    /**
     * Gets the working hours.
     *
     * @return the working hours
     */
    public List<ProviderWorkingHourEntity> getWorkingHours() {
        return workingHours;
    }

    /**
     * Adds the working hour.
     *
     * @param workingHour
     *            the working hour
     * @return true, if collection changed as a result of the call
     */
    public boolean addWorkingHour(ProviderWorkingHourEntity workingHour) {
        if (this.workingHours == null) {
            this.workingHours = Lists.newArrayList();
        }
        workingHour.setProvider(this);
        return this.workingHours.add(workingHour);
    }

    /**
     * Removes the working hour.
     *
     * @param workingHour
     *            the working hour
     * @return true, if list contained the specified element
     */
    public boolean removeWorkingHour(ProviderWorkingHourEntity workingHour) {
        Preconditions.checkNotNull(workingHour);
        workingHour.setProvider(null);
        if (this.workingHours != null) {
            return this.workingHours.remove(workingHour);
        }
        return false;
    }

    /**
     * Gets the vacations.
     *
     * @return the vacations
     */
    public List<ProviderVacationEntity> getVacations() {
        return vacations;
    }

    /**
     * Adds the vacation.
     *
     * @param vacation
     *            the company vacation
     * @return true, if collection changed as a result of the call
     */
    public boolean addVacation(ProviderVacationEntity vacation) {
        if (this.vacations == null) {
            this.vacations = Lists.newArrayList();
        }
        vacation.setProvider(this);
        return this.vacations.add(vacation);
    }

    /**
     * Removes the vacation.
     *
     * @param vacation
     *            the vacation
     * @return true, if list contained the specified element
     */
    public boolean removeVacation(ProviderVacationEntity vacation) {
        Preconditions.checkNotNull(vacation);
        vacation.setProvider(null);
        if (this.vacations != null) {
            return this.vacations.remove(vacation);
        }
        return false;
    }

    /**
     * Gets the Company record identifier.
     *
     * @return the record identifier
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the last modification identifier - for optimistic concurrency
     * locking.
     *
     * @return the last modification id
     */
    public long getLastModificationId() {
        return lastModificationId;
    }

    /**
     * Sets the last modification identifier - for optimistic concurrency
     * locking.
     *
     * @param comment
     *            the last modification id
     */
    public void setLastModificationId(long lastModificationId) {
        this.lastModificationId = lastModificationId;
    }

    /**
     * Gets the comment.
     *
     * @return the comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment.
     *
     * @param comment
     *            to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets the additional costs.
     *
     * @return the additional costs
     */
    public List<AdditionalCostEntity> getAdditionalCosts() {
        return additionalCosts;
    }

    /**
     * Adds the additional cost.
     *
     * @param additionalCost
     *            the additional cost
     * @return true, if collection changed as a result of the call
     */
    public boolean addAdditionalCost(AdditionalCostEntity additionalCost) {
        if (this.additionalCosts == null) {
            this.additionalCosts = Lists.newArrayList();
        }
        return this.additionalCosts.add(additionalCost);
    }

    /**
     * Removes the additional cost.
     *
     * @param additionalCost
     *            the additional cost
     * @return true, if list contained the specified element
     */
    public boolean removeAdditionalCost(AdditionalCostEntity additionalCost) {
        Preconditions.checkNotNull(additionalCost);
        if (this.additionalCosts != null) {
            return this.additionalCosts.remove(additionalCost);
        }
        return false;
    }

    /**
     * Gets the dishes.
     *
     * @return the dishes
     */
    public List<DishEntity> getDishes() {
        return dishes;
    }

    /**
     * Adds the dish.
     *
     * @param dish
     *            the dish
     * @return true, if collection changed as a result of the call
     */
    public boolean addDish(DishEntity dish) {
        if (this.dishes == null) {
            this.dishes = Lists.newArrayList();
        }
        return this.dishes.add(dish);
    }

    /**
     * Removes the dish.
     *
     * @param dish
     *            the dish
     * @return true, if list contained the specified element
     */
    public boolean removeDish(DishEntity dish) {
        Preconditions.checkNotNull(dish);
        if (this.dishes != null) {
            return this.dishes.remove(dish);
        }
        return false;
    }

    public final static class Builder {
        private final ProviderEntity entity = new ProviderEntity();

        public Builder(ActivityStatus status, String name) {
            entity.setStatus(status);
            entity.setName(name);
        }

        public ProviderEntity build() {
            return entity;
        }

        public Builder status(ActivityStatus status) {
            entity.setStatus(status);
            return this;
        }

        public Builder name(String name) {
            entity.setName(name);
            return this;
        }

        public Builder address(AddressEntity address) {
            entity.setAddress(address);
            return this;
        }

        public Builder contact(ContactEntity contact) {
            entity.setContact(contact);
            return this;
        }

        public Builder addWorkingHour(ProviderWorkingHourEntity... workingHours) {
            for (ProviderWorkingHourEntity workingHour : workingHours) {
                entity.addWorkingHour(workingHour);
            }
            return this;
        }

        public Builder addVacation(ProviderVacationEntity... vacations) {
            for (ProviderVacationEntity vacation : vacations) {
                entity.addVacation(vacation);
            }
            return this;
        }

    }
}
