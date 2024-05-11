package com.github.wnameless.spring.validation.spelscriptassert.bean;

import com.github.wnameless.spring.validation.spelscriptassert.TargetPrinter;

public class NoTargetPrinter implements TargetPrinter {

  private NoTargetPrinter() {}

  @Override
  public String print(Object target) {
    if (target instanceof Number number) {
      return String.format("%07d", number);
    }
    return target == null ? "null" : target.toString();
  }

}
