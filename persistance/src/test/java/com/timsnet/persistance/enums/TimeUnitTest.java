package com.timsnet.persistance.enums;

import org.junit.Assert;
import org.junit.Test;

import com.timsmeet.persistance.enums.TimeUnit;

public class TimeUnitTest {

  @Test
  public void timeUnitForCodeTest() {
    Assert.assertTrue("Should parse as Day.",
      TimeUnit.forCode(com.timsmeet.persistance.constants.FieldValue.TimeUnit.DAY) == TimeUnit.DAY);
    Assert.assertTrue("Should parse as Hour.",
      TimeUnit.forCode(com.timsmeet.persistance.constants.FieldValue.TimeUnit.HOUR) == TimeUnit.HOUR);
    Assert.assertTrue("Should parse as Minute.",
      TimeUnit.forCode(com.timsmeet.persistance.constants.FieldValue.TimeUnit.MINUTE) == TimeUnit.MINUTE);
    Assert.assertNull("Should parse to null.", TimeUnit.forCode("XX"));

  }

}
