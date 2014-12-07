package com.timsmeet.persistance.enums;

import com.timsmeet.persistance.constants.FieldValue;

public enum WeekDay implements StringValuedEnum {
  
  MONDAY(FieldValue.WeekDay.Monday),
  TUESDAY(FieldValue.WeekDay.Tuesday),
  WEDNESDAY(FieldValue.WeekDay.Wednesday),
  THURSDAY(FieldValue.WeekDay.Thursday),
  FRIDAY(FieldValue.WeekDay.Friday),
  SATRUDAY(FieldValue.WeekDay.Saturday),
  SUNDAY(FieldValue.WeekDay.Sunday);

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
