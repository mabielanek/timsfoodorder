package com.timsnet.persistance.enums;

import org.junit.Assert;
import org.junit.Test;

import com.timsmeet.persistance.enums.EmailPreferences;

public class EmailPreferencesTest {

  @Test
  public void emailPreferencesTestForCode() {
    Assert.assertTrue("Should decode as text",
      EmailPreferences.forCode(com.timsmeet.persistance.constants.FieldValue.EmailPreferences.PlainText) == EmailPreferences.PLAIN_TEXT);
    Assert.assertTrue("Should decode as rich text",
      EmailPreferences.forCode(com.timsmeet.persistance.constants.FieldValue.EmailPreferences.RichText) == EmailPreferences.RICH_TEXT);
    Assert.assertNull("Should decode as null", EmailPreferences.forCode("ALA"));
    
  }

}
