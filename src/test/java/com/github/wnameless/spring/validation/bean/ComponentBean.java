package com.github.wnameless.spring.validation.bean;

import com.github.wnameless.spring.validation.SpELScriptAssert;

@SpELScriptAssert(script = "@testComponent.multiply(@testComponent.add(a, b), c) == 9")
public class ComponentBean {

  public int a = 1;

  public int b = 2;

  public int c = 3;

}
