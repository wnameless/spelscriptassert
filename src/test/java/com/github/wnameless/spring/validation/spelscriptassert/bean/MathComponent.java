package com.github.wnameless.spring.validation.spelscriptassert.bean;

import org.springframework.stereotype.Component;

@Component
public class MathComponent {

  public int multiply(int a, int b) {
    return a * b;
  }

}
