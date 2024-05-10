package com.github.wnameless.spring.validation.bean;

import org.springframework.stereotype.Component;

@Component
public class TestComponent {

  public int add(int a, int b) {
    return a + b;
  }

  public int multiply(int a, int b) {
    return a * b;
  }

}
