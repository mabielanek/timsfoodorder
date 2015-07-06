package com.timsmeet.persistance.enums;

import com.timsmeet.persistance.constants.FieldValue;

public enum WeekDay implements StringValuedEnum {

    MONDAY(FieldValue.WeekDay.MONDAY),
    TUESDAY(FieldValue.WeekDay.TUESDAY),
    WEDNESDAY(FieldValue.WeekDay.WEDNESDAY),
    THURSDAY(FieldValue.WeekDay.THURSDAY),
    FRIDAY(FieldValue.WeekDay.FRIDAY),
    SATRUDAY(FieldValue.WeekDay.SATURDAY),
    SUNDAY(FieldValue.WeekDay.SUNDAY);

    private final String weekDayCode;

    private final static EnumForCodeHelper<WeekDay> forCodeHelper = new EnumForCodeHelper<WeekDay>(WeekDay.values());

    private WeekDay(String weekDayCode) {
        this.weekDayCode = weekDayCode;
    }

    @Override
    public String getCode() {
        return weekDayCode;
    }

    public static WeekDay forCode(String code) {
        return forCodeHelper.forCode(code);
    }

}
