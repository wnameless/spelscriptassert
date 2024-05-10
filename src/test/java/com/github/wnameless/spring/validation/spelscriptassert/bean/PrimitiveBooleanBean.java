package com.github.wnameless.spring.validation.spelscriptassert.bean;

import com.github.wnameless.spring.validation.spelscriptassert.SpELScriptAssert;

@SpELScriptAssert(script = "boolFalse")
@SpELScriptAssert(script = "boolTrue == false")
public class PrimitiveBooleanBean {

  public boolean boolTrue = true;
  public boolean boolFalse = false;

}
