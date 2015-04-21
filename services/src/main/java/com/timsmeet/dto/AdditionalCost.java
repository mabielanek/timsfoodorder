package com.timsmeet.dto;

import java.math.BigDecimal;

import com.timsmeet.dto.entity.BaseEntity;
import com.timsmeet.dto.entity.EntityState;
import com.timsmeet.persistance.enums.ActivityStatus;
import com.timsmeet.persistance.enums.AdditionalCostKind;

public class AdditionalCost extends BaseEntity {

	private Long id;
	private Long lastModificationId;
	private ActivityStatus status;
	private AdditionalCostKind kind;
	private BigDecimal cost;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLastModificationId() {
		return lastModificationId;
	}
	public void setLastModificationId(Long lastModificationId) {
		this.lastModificationId = lastModificationId;
	}
	public ActivityStatus getStatus() {
		return status;
	}
	public void setStatus(ActivityStatus status) {
		this.status = status;
	}
	public AdditionalCostKind getKind() {
		return kind;
	}
	public void setKind(AdditionalCostKind kind) {
		this.kind = kind;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	public final static class Builder {
		private final AdditionalCost additionalCost = new AdditionalCost();

		public Builder(EntityState entityState, ActivityStatus status) {
			additionalCost.getEntityAspect().setEntityState(entityState);
			additionalCost.setStatus(status);
		}

		public AdditionalCost build() {
			return additionalCost;
		}

		public Builder id(Long id) {
			additionalCost.setId(id);
			return this;
		}

		public Builder lastModificationId(Long lastModificationId) {
			additionalCost.setLastModificationId(lastModificationId);
			return this;
		}

		public Builder status(ActivityStatus status) {
			additionalCost.setStatus(status);
			return this;
		}

		public Builder kind(AdditionalCostKind kind) {
			additionalCost.setKind(kind);
			return this;
		}
		public Builder cost(BigDecimal cost) {
			additionalCost.setCost(cost);
			return this;
		}
	}
}
