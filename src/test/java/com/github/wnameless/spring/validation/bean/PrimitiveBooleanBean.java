package com.github.wnameless.spring.validation.bean;

import com.github.wnameless.spring.validation.SpELScriptAssert;

@SpELScriptAssert(script = "boolFalse")
@SpELScriptAssert(script = "boolTrue == false")
public class PrimitiveBooleanBean {

  public boolean boolTrue = true;
  public boolean boolFalse = false;

}
