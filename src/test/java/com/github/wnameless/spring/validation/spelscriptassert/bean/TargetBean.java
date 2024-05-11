package com.github.wnameless.spring.validation.spelscriptassert.bean;

import com.github.wnameless.spring.validation.spelscriptassert.SpELScriptAssert;

@SpELScriptAssert( //
    target = "a * b * c", //
    script = "#target < 100", //
    performIf = "a != null && b != null && c != null", //
    message = "{com.github.wnameless.spring.validation.spelscriptassert.bean.TargetBean.multiplication}: {#target} > 100")
public class TargetBean {

  public int a = 10;
  public int b = 10;
  public int c = 10;

}
