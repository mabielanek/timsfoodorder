package com.timsmeet.persistance.enums;

public enum YesNo implements StringValuedEnum {
  
  YES("Y"),
  NO("N");

  private final String yesNoCode;

  private final static EnumForCodeHelper<YesNo> forCodeHelper = new EnumForCodeHelper<YesNo>(YesNo.values());

  private YesNo(String yesNoCode) {
    this.yesNoCode = yesNoCode;
  }

  @Override
  public String getCode() {
    return yesNoCode;
  }

  public static YesNo forCode(String code) {
    return forCodeHelper.forCode(code);
  }

}
