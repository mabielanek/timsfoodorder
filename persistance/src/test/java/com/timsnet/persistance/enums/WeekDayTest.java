package com.timsnet.persistance.enums;

import org.junit.Assert;
import org.junit.Test;

import com.timsmeet.persistance.enums.WeekDay;

public class WeekDayTest {

  @Test
  public void weekDayForCodeTest() {
    Assert.assertTrue("Should parse as Friday.",
      WeekDay.forCode(com.timsmeet.persistance.constants.FieldValue.WeekDay.Friday) == WeekDay.FRIDAY);
    Assert.assertTrue("Should parse as Monday.",
      WeekDay.forCode(com.timsmeet.persistance.constants.FieldValue.WeekDay.Monday) == WeekDay.MONDAY);
    Assert.assertTrue("Should parse as Saturday.",
      WeekDay.forCode(com.timsmeet.persistance.constants.FieldValue.WeekDay.Saturday) == WeekDay.SATRUDAY);
    Assert.assertTrue("Should parse as Sunday.",
      WeekDay.forCode(com.timsmeet.persistance.constants.FieldValue.WeekDay.Sunday) == WeekDay.SUNDAY);
    Assert.assertTrue("Should parse as Thursday.",
      WeekDay.forCode(com.timsmeet.persistance.constants.FieldValue.WeekDay.Thursday) == WeekDay.THURSDAY);
    Assert.assertTrue("Should parse as Tuesday.",
      WeekDay.forCode(com.timsmeet.persistance.constants.FieldValue.WeekDay.Tuesday) == WeekDay.TUESDAY);
    Assert.assertTrue("Should parse as Wednesday.",
      WeekDay.forCode(com.timsmeet.persistance.constants.FieldValue.WeekDay.Wednesday) == WeekDay.WEDNESDAY);
    Assert.assertNull("Should parse to null.", WeekDay.forCode("XX"));

  }
}
