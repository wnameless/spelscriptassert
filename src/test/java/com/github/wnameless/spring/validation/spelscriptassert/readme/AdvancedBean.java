package com.github.wnameless.spring.validation.spelscriptassert.readme;

import com.github.wnameless.spring.validation.spelscriptassert.SpELScriptAssert;

@SpELScriptAssert( //
    target = "a * b * c", //
    script = "#target > 10", //
    message = "#{#target} {validation.AdvancedBean.msg} #{10 - T(java.lang.Math).multiplyExact(a, b) * c + 1}")
public class AdvancedBean {

  public Integer a = 1;
  public Integer b = 2;
  public Integer c = 3;

}
