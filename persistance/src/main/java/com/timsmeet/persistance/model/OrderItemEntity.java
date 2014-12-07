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

@Entity
@Table(name = "fo_order_item", 
	indexes = { 
		@Index(columnList = "person_id", name = "idx_order_item_person_fk"),
		@Index(columnList = "food_order_id", name = "idx_order_item_food_ord_fk"),
		@Index(columnList = "dish_id", name = "idx_order_item_dish_fk") })
public class OrderItemEntity {

	@Id
	@GeneratedValue(generator = "orderItemGenerator")
	@GenericGenerator(name = "orderItemGenerator", 
		strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", 
		parameters = {@Parameter(name = "sequence_name", value = "seq_fo_ord_item_id")})
	private long id;

	@Version
	@Column(name = "last_modification_id")
	private long lastModificationId;
	
	@ManyToOne
	@JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "order_item_person_fk"))
	private PersonEntity person;
	
	@ManyToOne
	@JoinColumn(name = "food_order_id", foreignKey = @ForeignKey(name = "order_item_food_ord_fk"))
	private FoodOrderEntity foodOrder;

	@ManyToOne
	@JoinColumn(name = "dish_id", foreignKey = @ForeignKey(name = "order_item_dish_fk"))
	private DishEntity dish;

	@Column(name = "count", nullable = false)
	private int count;
	
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "order_item_id")
	private List<OrderSubItemEntity> orderSubItems = new ArrayList<OrderSubItemEntity>();

	public PersonEntity getPerson() {
		return person;
	}

	public void setPerson(PersonEntity person) {
		this.person = person;
	}

	public FoodOrderEntity getFoodOrder() {
		return foodOrder;
	}

	public void setFoodOrder(FoodOrderEntity foodOrder) {
		this.foodOrder = foodOrder;
	}

	public DishEntity getDish() {
		return dish;
	}

	public void setDish(DishEntity dish) {
		this.dish = dish;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getId() {
		return id;
	}

	public long getLastModificationId() {
		return lastModificationId;
	}	
	
	public List<OrderSubItemEntity> getOrderSubItems() {
		return orderSubItems;
	}
	
	public boolean addOrderSubItem(OrderSubItemEntity orderSubItem) {
		if (this.orderSubItems == null) {
			this.orderSubItems = Lists.newArrayList();
		}
		orderSubItem.setOrderItem(this);
		return this.orderSubItems.add(orderSubItem);
	}

	public boolean removeOrderSubItem(OrderSubItemEntity orderSubItem) {
		Preconditions.checkNotNull(orderSubItem);
		orderSubItem.setOrderItem(null);
		if (this.orderSubItems != null) {
			return this.orderSubItems.remove(orderSubItem);
		}
		return false;
	}	

}
