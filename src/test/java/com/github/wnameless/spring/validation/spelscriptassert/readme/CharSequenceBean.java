package com.github.wnameless.spring.validation.spelscriptassert.readme;

import com.github.wnameless.spring.validation.spelscriptassert.SpELScriptAssert;

@SpELScriptAssert(script = "a", reportOn = "a", message = "{reportOn} is false")
@SpELScriptAssert(script = "b", reportOn = "b", message = "{reportOn} is false")
public class CharSequenceBean {
  public String a = "   ";
  public CharSequence b = "I";
}
