package com.timsnet.persistance.enums;

import org.junit.Assert;
import org.junit.Test;

import com.timsmeet.persistance.enums.EnumForCodeHelper;
import com.timsmeet.persistance.enums.StringValuedEnum;

public class EnumForCodeHelperTest {
	
	public enum TestEnum implements StringValuedEnum {
		TestEnumA("A"),
		TestEnumB("B");
		
		private final String code;
		
		private TestEnum(String code) {
			this.code = code;
		}

		@Override
		public String getCode() {
			return code;
		}
	}
	
	@Test
	public void testForCodeHelper() {
		EnumForCodeHelper<TestEnum> forCodeHelper = new EnumForCodeHelper<EnumForCodeHelperTest.TestEnum>(TestEnum.values());
		
		Assert.assertTrue("TestEnumA for code 'A' expected.",
				forCodeHelper.forCode("A") == TestEnum.TestEnumA);
		Assert.assertTrue("TestEnumB for code 'B' expected.", 
				forCodeHelper.forCode("B") == TestEnum.TestEnumB);
		Assert.assertNull("Expected null for unknown code.", forCodeHelper.forCode("X"));
		
	}

}
