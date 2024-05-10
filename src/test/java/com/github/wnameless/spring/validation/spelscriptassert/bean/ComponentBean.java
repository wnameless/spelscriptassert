package com.github.wnameless.spring.validation.spelscriptassert.bean;

import com.github.wnameless.spring.validation.spelscriptassert.SpELScriptAssert;

@SpELScriptAssert(script = "@mathComponent.multiply(a + b, c) == 9")
public class ComponentBean {

  public int a = 1;

  public int b = 2;

  public int c = 3;

}
