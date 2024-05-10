package com.github.wnameless.spring.validation.bean;

import com.github.wnameless.spring.validation.SpELScriptAssert;

@SpELScriptAssert(script = "test",
    message = "{com.github.wnameless.spring.validation.SpELAssert.custom}", reportOn = "test")
public class CustomBean {

  public String test = "";

}
