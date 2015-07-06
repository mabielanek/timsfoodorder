package com.timsmeet.persistance.enums;

import com.timsmeet.persistance.constants.FieldValue;

public enum FoodOrderStatus implements StringValuedEnum {
    ACTIVE(FieldValue.FoodOrderStatus.ACTIVE),
    CLOSED(FieldValue.FoodOrderStatus.CLOSED),
    DELIVERED(FieldValue.FoodOrderStatus.DELIVERED),
    CANCELLED(FieldValue.FoodOrderStatus.CANCELLED);

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
