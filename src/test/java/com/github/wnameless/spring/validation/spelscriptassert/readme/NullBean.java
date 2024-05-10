package com.github.wnameless.spring.validation.spelscriptassert.readme;

import java.math.BigDecimal;
import java.math.BigInteger;
import com.github.wnameless.spring.validation.spelscriptassert.SpELScriptAssert;

@SpELScriptAssert(script = "a", reportOn = "a", message = "{reportOn} is false")
@SpELScriptAssert(script = "b", reportOn = "b", message = "{reportOn} is false")
public class NullBean {
  public BigInteger a = null;
  public BigDecimal b = BigDecimal.valueOf(123L);
}
