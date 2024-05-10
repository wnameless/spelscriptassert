package com.github.wnameless.spring.validation.spelscriptassert.bean;

import com.github.wnameless.spring.validation.spelscriptassert.SpELScriptAssert;

@SpELScriptAssert( //
    script = "@mathComponent.multiply(#add(a, b), c) == result", //
    performIf = "a != null && b != null && c != null", //
    helpers = {MathHelper.class}, //
    reportOn = "result", //
    message = "{com.github.wnameless.spring.validation.spelscriptassert.bean.MixBean}")
public class MixBean {

  public Integer result = 10;
  public Integer a = 1;
  public Integer b = 2;
  public Integer c = 3;

}
