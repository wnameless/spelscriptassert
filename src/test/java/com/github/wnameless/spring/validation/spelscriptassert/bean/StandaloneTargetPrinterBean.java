package com.github.wnameless.spring.validation.spelscriptassert.bean;

import com.github.wnameless.spring.validation.spelscriptassert.SpELScriptAssert;

@SpELScriptAssert( //
    target = "a * b * c", //
    script = "#target < 100", //
    message = "{#target}", //
    targetPrinter = StandaloneTargetPrinter.class //
)
public class StandaloneTargetPrinterBean {

  public int a = 10;
  public int b = 10;
  public int c = 10;

}
