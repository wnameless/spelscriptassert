package com.github.wnameless.spring.validation.bean;

import com.github.wnameless.spring.validation.SpELScriptAssert;

@SpELScriptAssert(script = "@mathComponent.multiply(#add(a, b), c) == result", reportOn = "result",
    performIf = "a != null && b != null && c != null", helpers = {MathHelper.class})
public class MixBean {

  public Integer result = 9;

  public Integer a = 1;

  public Integer b = 2;

  public Integer c = 3;

}
