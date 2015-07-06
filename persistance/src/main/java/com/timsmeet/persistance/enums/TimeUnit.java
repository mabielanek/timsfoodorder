package com.timsmeet.persistance.enums;

import com.timsmeet.persistance.constants.FieldValue;

public enum TimeUnit implements StringValuedEnum {

    MINUTE(FieldValue.TimeUnit.MINUTE),
    HOUR(FieldValue.TimeUnit.HOUR),
    DAY(FieldValue.TimeUnit.DAY);

    private final String tiemUnitCode;

    private final static EnumForCodeHelper<TimeUnit> forCodeHelper = new EnumForCodeHelper<TimeUnit>(TimeUnit.values());

    private TimeUnit(String tiemUnitCode) {
        this.tiemUnitCode = tiemUnitCode;
    }

    @Override
    public String getCode() {
        return tiemUnitCode;
    }

    public static TimeUnit forCode(String code) {
        return forCodeHelper.forCode(code);
    }

}
