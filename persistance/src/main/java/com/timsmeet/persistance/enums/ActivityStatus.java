package com.timsmeet.persistance.enums;

import com.timsmeet.persistance.constants.FieldValue;

public enum ActivityStatus implements StringValuedEnum {

  ACTIVE(FieldValue.ActivityStatus.Active),
  INACTIVE(FieldValue.ActivityStatus.Inactive),
  DELETED(FieldValue.ActivityStatus.Deleted);

  private final String statusCode;
  
  private final static EnumForCodeHelper<ActivityStatus> forCodeHelper = new EnumForCodeHelper<ActivityStatus>(ActivityStatus.values());

  private ActivityStatus(String statusCode) {
    this.statusCode = statusCode;
  }

  @Override
  public String getCode() {
    return statusCode;
  }

  public static ActivityStatus forCode(String code) {
    return forCodeHelper.forCode(code);
  }

}
