package com.timsmeet.persistance.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * Stores information about company working hour.
 */
@Entity
@DiscriminatorValue("P")
public class ProviderWorkingHourEntity extends WorkingHourEntity {
  
  @ManyToOne
  @JoinColumn(name = "provider_id", foreignKey=@ForeignKey(name="wrk_hour_provider_fk"))
  private ProviderEntity provider;

  /**
   * Gets the provider.
   *
   * @return the provider
   */
  public ProviderEntity getProvider() {
    return provider;
  }

  /**
   * Sets the provider.
   *
   * @param company the new provider
   */
  void setProvider(ProviderEntity provider) {
    this.provider = provider;
  }
  
	public final static class Builder extends WorkingHourEntity.Builder<ProviderWorkingHourEntity> {
		public Builder() {
			super(new ProviderWorkingHourEntity());
		}

	}
  

}
