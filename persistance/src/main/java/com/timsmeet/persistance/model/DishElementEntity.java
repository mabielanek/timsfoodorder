package com.timsmeet.persistance.model;

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

import com.timsmeet.persistance.enums.ActivityStatus;

@Entity
@Table(name = "fo_dish_elem",
	indexes = @Index(columnList="dish_comp_id", name="idx_dish_elem_dish_comp_fk")
)
public class DishElementEntity {

	@Id
	@GeneratedValue(generator = "dishElementGenerator")
	@GenericGenerator(name = "dishElementGenerator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", 
	parameters = { 
	  @Parameter(name = "sequence_name", value="seq_fo_dish_elem_id")
	})
	private long id;

	@Version
	@Column(name = "last_modification_id")
	private long lastModificationId;

	@Column(name = "status", nullable = false, length = 1)
	@org.hibernate.annotations.Check(constraints = "status IN('A','I','D')")
	private String status;

	@ManyToOne
	@JoinColumn(name = "dish_comp_id", foreignKey = @ForeignKey(name = "dish_elem_dish_comp_fk"))
	private DishComponentEntity dishComponent;

	@Column(name = "name", length = 255)
	private String name;

	@Column(name = "description", length = 255)
	private String description;

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

	public long getId() {
		return id;
	}

	public long getLastModificationId() {
		return lastModificationId;
	}
	
}
