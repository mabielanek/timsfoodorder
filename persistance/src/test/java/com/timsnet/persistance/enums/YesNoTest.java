package com.timsnet.persistance.enums;

import org.junit.Assert;
import org.junit.Test;

import com.timsmeet.persistance.enums.YesNo;

public class YesNoTest {

  @Test
  public void yesNoForCodeTest() {
    Assert.assertTrue("Should parse as No.",
      YesNo.forCode(com.timsmeet.persistance.constants.FieldValue.YesNo.No) == YesNo.NO);
    Assert.assertTrue("Should parse as Yes.",
      YesNo.forCode(com.timsmeet.persistance.constants.FieldValue.YesNo.Yes) == YesNo.YES);
    Assert.assertNull("Should parse to null.", YesNo.forCode("XX"));

  }
}
