package com.timsnet.persistance.enums;

import org.junit.Assert;
import org.junit.Test;

import com.timsmeet.persistance.enums.WeekDay;

public class WeekDayTest {

  @Test
  public void weekDayForCodeTest() {
    Assert.assertTrue("Should parse as Friday.",
      WeekDay.forCode(com.timsmeet.persistance.constants.FieldValue.WeekDay.FRIDAY) == WeekDay.FRIDAY);
    Assert.assertTrue("Should parse as Monday.",
      WeekDay.forCode(com.timsmeet.persistance.constants.FieldValue.WeekDay.MONDAY) == WeekDay.MONDAY);
    Assert.assertTrue("Should parse as Saturday.",
      WeekDay.forCode(com.timsmeet.persistance.constants.FieldValue.WeekDay.SATURDAY) == WeekDay.SATRUDAY);
    Assert.assertTrue("Should parse as Sunday.",
      WeekDay.forCode(com.timsmeet.persistance.constants.FieldValue.WeekDay.SUNDAY) == WeekDay.SUNDAY);
    Assert.assertTrue("Should parse as Thursday.",
      WeekDay.forCode(com.timsmeet.persistance.constants.FieldValue.WeekDay.THURSDAY) == WeekDay.THURSDAY);
    Assert.assertTrue("Should parse as Tuesday.",
      WeekDay.forCode(com.timsmeet.persistance.constants.FieldValue.WeekDay.TUESDAY) == WeekDay.TUESDAY);
    Assert.assertTrue("Should parse as Wednesday.",
      WeekDay.forCode(com.timsmeet.persistance.constants.FieldValue.WeekDay.WEDNESDAY) == WeekDay.WEDNESDAY);
    Assert.assertNull("Should parse to null.", WeekDay.forCode("XX"));

  }
}
