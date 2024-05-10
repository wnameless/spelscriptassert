package com.github.wnameless.spring.validation.bean;

import com.github.wnameless.spring.validation.SpELScriptAssert;

@SpELScriptAssert(script = "#multiply(#add(a, b), c) == 9",
    helpers = {MathHelper.class, MathHelper2.class})
public class HelperBean {

  public int a = 1;

  public int b = 2;

  public int c = 3;

}
