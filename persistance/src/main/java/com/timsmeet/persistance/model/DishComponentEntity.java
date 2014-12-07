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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.timsmeet.persistance.enums.ActivityStatus;
import com.timsmeet.persistance.enums.YesNo;

@Entity
@Table(name = "fo_dish_comp",
	indexes = @Index(columnList="dish_id", name="idx_dish_comp_dish_fk")
)
public class DishComponentEntity {

	@Id
	@GeneratedValue(generator = "dishComponentGenerator")
	@GenericGenerator(name = "dishComponentGenerator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", 
	parameters = { 
	  @Parameter(name = "sequence_name", value="seq_fo_dish_comp_id")
	})
	private long id;
	  
	@Version
	@Column(name = "last_modification_id")
	private long lastModificationId;

	@Column(name = "status", nullable = false, length = 1)
	@org.hibernate.annotations.Check(constraints = "status IN('A','I','D')")
	private String status;

	@ManyToOne
	@JoinColumn(name = "dish_id", foreignKey = @ForeignKey(name = "dish_comp_dish_fk"))
	private DishEntity dish;

	@Column(name = "description", length = 255)
	private String description;

	@Column(name = "use_as_dish_price", nullable = false, length = 1)
	@org.hibernate.annotations.Check(constraints = "use_as_dish_price IN('Y','N')")
	private String useComponentPriceAsDishPrice = YesNo.NO.getCode();

	@Column(name = "elements_required", nullable = false)
	private Integer numberOfRequiredElements;

	@Column(name = "max_elements", nullable = false)
	private Integer maximumNumberOfElements;

	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "dish_comp_id")
	private List<DishElementEntity> dishElements = new ArrayList<DishElementEntity>();

	public ActivityStatus getStatus() {
		return ActivityStatus.forCode(status);
	}

	public void setStatus(ActivityStatus status) {
		this.status = status.getCode();
	}

	public DishEntity getDish() {
		return dish;
	}

	public void setDish(DishEntity dish) {
		this.dish = dish;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public YesNo getUseComponentPriceAsDishPrice() {
		return YesNo.forCode(useComponentPriceAsDishPrice);
	}

	public void setUseComponentPriceAsDishPrice(YesNo useComponentPriceAsDishPrice) {
		this.useComponentPriceAsDishPrice = useComponentPriceAsDishPrice.getCode();
	}

	public Integer getNumberOfRequiredElements() {
		return numberOfRequiredElements;
	}

	public void setNumberOfRequiredElements(Integer numberOfRequiredElements) {
		this.numberOfRequiredElements = numberOfRequiredElements;
	}

	public Integer getMaximumNumberOfElements() {
		return maximumNumberOfElements;
	}

	public void setMaximumNumberOfElements(Integer maximumNumberOfElements) {
		this.maximumNumberOfElements = maximumNumberOfElements;
	}

	public long getId() {
		return id;
	}

	public long getLastModificationId() {
		return lastModificationId;
	}

	public List<DishElementEntity> getDishElements() {
		return dishElements;
	}

	public boolean addDishElement(DishElementEntity dishElement) {
		if (this.dishElements == null) {
			this.dishElements = Lists.newArrayList();
		}
		dishElement.setDishComponent(this);
		return this.dishElements.add(dishElement);
	}

	public boolean removeDishElement(DishElementEntity dishElement) {
		Preconditions.checkNotNull(dishElement);
		dishElement.setDishComponent(null);
		if (this.dishElements != null) {
			return this.dishElements.remove(dishElement);
		}
		return false;
	}	
	
}
