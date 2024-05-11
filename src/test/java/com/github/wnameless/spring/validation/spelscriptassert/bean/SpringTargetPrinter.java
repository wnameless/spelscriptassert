package com.github.wnameless.spring.validation.spelscriptassert.bean;

import org.springframework.stereotype.Component;
import com.github.wnameless.spring.validation.spelscriptassert.TargetPrinter;

@Component
public class SpringTargetPrinter implements TargetPrinter {

  @Override
  public String print(Object target) {
    if (target instanceof Number number) {
      return String.format("%06d", number);
    }
    return target == null ? "null" : target.toString();
  }

}
