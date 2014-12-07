package com.timsnet.persistance.enums;

import org.junit.Assert;
import org.junit.Test;

import com.timsmeet.persistance.enums.PhoneNumberType;

public class PhoneNumberTypeTest {
	
	@Test
	public void phoneNumberTypeForCodeTest() {
		Assert.assertTrue("Should parse as Fax.", 
				PhoneNumberType.forCode(com.timsmeet.persistance.constants.FieldValue.PhoneNumberType.Fax) == PhoneNumberType.FAX);
		Assert.assertTrue("Should parse as Landline.", 
				PhoneNumberType.forCode(com.timsmeet.persistance.constants.FieldValue.PhoneNumberType.Landline) == PhoneNumberType.LANDLINE);
		Assert.assertTrue("Should parse as Mobile.", 
				PhoneNumberType.forCode(com.timsmeet.persistance.constants.FieldValue.PhoneNumberType.Mobile) == PhoneNumberType.MOBILE);
		Assert.assertNull("Should parse to null.", PhoneNumberType.forCode("XX"));

	}

}
