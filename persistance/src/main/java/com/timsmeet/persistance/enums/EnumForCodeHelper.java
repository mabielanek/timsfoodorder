package com.timsmeet.persistance.enums;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class EnumForCodeHelper<T extends Enum<?> & StringValuedEnum> {

  private Map<String, T> byCode;

  public EnumForCodeHelper(T[] values) {
    createForCodeMap(values);
  }
  
  public T forCode(String code) {
    return byCode.get(code);
  }

  private void createForCodeMap(T[] values) {
    final ImmutableMap.Builder<String, T> builder = ImmutableMap.builder();
    for (final T value : values) {
      builder.put(value.getCode(), value);
    }
    byCode = builder.build();
  }

}
