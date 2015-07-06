package com.timsmeet.persistance.enums;

import com.timsmeet.persistance.constants.FieldValue;

public enum PhoneNumberType implements StringValuedEnum {

    MOBILE(FieldValue.PhoneNumberType.MOBILE),
    FAX(FieldValue.PhoneNumberType.FAX),
    LANDLINE(FieldValue.PhoneNumberType.LANDLINE);

    private final String phoneNumberTypeCode;

    private final static EnumForCodeHelper<PhoneNumberType> forCodeHelper = new EnumForCodeHelper<PhoneNumberType>(PhoneNumberType.values());

    private PhoneNumberType(String phoneNumberTypeCode) {
        this.phoneNumberTypeCode = phoneNumberTypeCode;
    }

    @Override
    public String getCode() {
        return phoneNumberTypeCode;
    }

    public static PhoneNumberType forCode(String code) {
        return forCodeHelper.forCode(code);
    }

}
