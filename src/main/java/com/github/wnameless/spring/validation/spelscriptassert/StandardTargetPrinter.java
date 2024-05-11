package com.github.wnameless.spring.validation.spelscriptassert;

/**
 * A default implementation of {@link TargetPrinter}.
 */
public class StandardTargetPrinter implements TargetPrinter {

  @Override
  public String print(Object target) {
    return target == null ? "null" : target.toString();
  }

}
