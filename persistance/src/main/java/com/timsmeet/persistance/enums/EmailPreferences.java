package com.timsmeet.persistance.enums;

import com.timsmeet.persistance.constants.FieldValue;

public enum EmailPreferences implements StringValuedEnum {

  PLAIN_TEXT(FieldValue.EmailPreferences.PlainText),
  RICH_TEXT(FieldValue.EmailPreferences.RichText);

  private final String emailPreferencesCode;

  private final static EnumForCodeHelper<EmailPreferences> forCodeHelper = new EnumForCodeHelper<EmailPreferences>(
    EmailPreferences.values());

  private EmailPreferences(String emailPreferencesCode) {
    this.emailPreferencesCode = emailPreferencesCode;
  }

  @Override
  public String getCode() {
    return emailPreferencesCode;
  }

  public static EmailPreferences forCode(String code) {
    return forCodeHelper.forCode(code);
  }

}
