package com.timsnet.persistance.enums;

import org.junit.Assert;
import org.junit.Test;

import com.timsmeet.persistance.enums.AdditionalCostKind;

public class AdditionalCostTest {
	@Test
	  public void additionalCostTestForCode() {
	    Assert.assertTrue("Should decode as Delivery", 
	      AdditionalCostKind.forCode(com.timsmeet.persistance.constants.FieldValue.AdditionalCostKind.DELIVERY) == AdditionalCostKind.DELIVERY);
	    Assert.assertTrue("Should decode as MinimalValue", 
	    		AdditionalCostKind.forCode(com.timsmeet.persistance.constants.FieldValue.AdditionalCostKind.MINIMAL_VALUE) == AdditionalCostKind.MINIMAL_VALUE);
	    Assert.assertTrue("Should decode as Packaging", 
	    		AdditionalCostKind.forCode(com.timsmeet.persistance.constants.FieldValue.AdditionalCostKind.PACKAGING) == AdditionalCostKind.PACKAGING);
	    Assert.assertNull("Should decode as null", AdditionalCostKind.forCode("ALA"));
	  }
}
