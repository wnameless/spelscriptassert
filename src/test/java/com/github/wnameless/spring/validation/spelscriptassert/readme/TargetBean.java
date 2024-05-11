package com.github.wnameless.spring.validation.spelscriptassert.readme;

import java.math.BigInteger;
import com.github.wnameless.spring.validation.spelscriptassert.SpELScriptAssert;

@SpELScriptAssert( //
    target = "bi1.multiply(bi2)", //
    script = "#target.toString.length < 10", //
    reportOn = "bi1 * bi2", //
    message = "{reportOn} = #{#target} and the digits are more than 10")
public class TargetBean {
  public BigInteger bi1 = new BigInteger("123456789");
  public BigInteger bi2 = new BigInteger("987654321");
}
