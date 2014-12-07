package com.timsmeet.persistance.enums;

import com.timsmeet.persistance.constants.FieldValue;

public enum FoodOrderStatus implements StringValuedEnum {
	ACTIVE(FieldValue.FoodOrderStatus.Active),
	CLOSED(FieldValue.FoodOrderStatus.Closed),
	DELIVERED(FieldValue.FoodOrderStatus.Delivered),
	CANCELLED(FieldValue.FoodOrderStatus.Cancelled);
	  
	private final String foodOrderStatusCode;

	private final static EnumForCodeHelper<FoodOrderStatus> forCodeHelper = new EnumForCodeHelper<FoodOrderStatus>(
			FoodOrderStatus.values());

	private FoodOrderStatus(String foodOrderStatusCode) {
		this.foodOrderStatusCode = foodOrderStatusCode;
	}

	@Override
	public String getCode() {
		return foodOrderStatusCode;
	}

	public static FoodOrderStatus forCode(String code) {
		return forCodeHelper.forCode(code);
	}
}
