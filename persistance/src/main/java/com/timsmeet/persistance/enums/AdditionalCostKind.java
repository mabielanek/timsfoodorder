package com.timsmeet.persistance.enums;

import com.timsmeet.persistance.constants.FieldValue;

public enum AdditionalCostKind implements StringValuedEnum {

    MINIMAL_VALUE(FieldValue.AdditionalCostKind.MINIMAL_VALUE),
    DELIVERY(FieldValue.AdditionalCostKind.DELIVERY),
    PACKAGING(FieldValue.AdditionalCostKind.PACKAGING);

    private final String additionalCostKindCode;

    private final static EnumForCodeHelper<AdditionalCostKind> forCodeHelper = new EnumForCodeHelper<AdditionalCostKind>(
            AdditionalCostKind.values());

    private AdditionalCostKind(String additionalCostKindCode) {
        this.additionalCostKindCode = additionalCostKindCode;
    }

    @Override
    public String getCode() {
        return additionalCostKindCode;
    }

    public static AdditionalCostKind forCode(String code) {
        return forCodeHelper.forCode(code);
    }

}
