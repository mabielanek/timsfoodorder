package com.timsmeet.persistance.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.timsmeet.persistance.constants.DbTable;

/**
 * Stores information about company vacation.
 */
@Entity
@DiscriminatorValue("PROVIDER")
public class ProviderVacationEntity extends VacationEntity {

    @ManyToOne
    @JoinColumn(name = DbTable.Vacation.PROVIDER_ID, foreignKey = @ForeignKey(name = "vacation_provider_fk"))
    private ProviderEntity provider;

    /**
     * Gets the company.
     *
     * @return the company
     */
    public ProviderEntity getProvider() {
        return provider;
    }

    /**
     * Sets the company.
     *
     * @param company
     *            the new company
     */
    void setProvider(ProviderEntity provider) {
        this.provider = provider;
    }

    public final static class Builder extends VacationEntity.Builder<ProviderVacationEntity> {
        public Builder() {
            super(new ProviderVacationEntity());
        }

    }

}
