package com.timsnet.persistance.enums;

import org.junit.Assert;
import org.junit.Test;

import com.timsmeet.persistance.enums.ActivityStatus;

public class ActivityStatusTest {
  
	@Test
	public void activityStatusTestForCode() {
		Assert.assertTrue("Should decode as Active",
				ActivityStatus.forCode(com.timsmeet.persistance.constants.FieldValue.ActivityStatus.Active) == ActivityStatus.ACTIVE);
		Assert.assertTrue("Should decode as Inactive",
				ActivityStatus.forCode(com.timsmeet.persistance.constants.FieldValue.ActivityStatus.Inactive) == ActivityStatus.INACTIVE);
		Assert.assertTrue("Should decode as Deleted",
				ActivityStatus.forCode(com.timsmeet.persistance.constants.FieldValue.ActivityStatus.Deleted) == ActivityStatus.DELETED);
		Assert.assertNull("Should decode as null", ActivityStatus.forCode("ALA"));
	}

}
