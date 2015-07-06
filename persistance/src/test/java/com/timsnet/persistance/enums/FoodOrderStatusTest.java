package com.timsnet.persistance.enums;

import org.junit.Assert;
import org.junit.Test;

import com.timsmeet.persistance.enums.FoodOrderStatus;

public class FoodOrderStatusTest {

	@Test
	public void foodOrderStatusTestForCode() {
		Assert.assertTrue("Should decode as Active",
				FoodOrderStatus.forCode(com.timsmeet.persistance.constants.FieldValue.FoodOrderStatus.ACTIVE) == FoodOrderStatus.ACTIVE);
		Assert.assertTrue("Should decode as Closed",
				FoodOrderStatus.forCode(com.timsmeet.persistance.constants.FieldValue.FoodOrderStatus.CLOSED) == FoodOrderStatus.CLOSED);
		Assert.assertTrue("Should decode as Deliverd",
				FoodOrderStatus.forCode(com.timsmeet.persistance.constants.FieldValue.FoodOrderStatus.DELIVERED) == FoodOrderStatus.DELIVERED);
		Assert.assertTrue("Should decode as Cancelled",
				FoodOrderStatus.forCode(com.timsmeet.persistance.constants.FieldValue.FoodOrderStatus.CANCELLED) == FoodOrderStatus.CANCELLED);
		Assert.assertNull("Should decode as null", FoodOrderStatus.forCode("ALA"));
	}
}
