package com.github.wnameless.spring.validation.spelscriptassert.bean;

import com.github.wnameless.spring.validation.spelscriptassert.SpELScriptAssert;

@SpELScriptAssert( //
    script = "str.empty", //
    message = "str is NOT empty, str is '#{str}' with length of #{str.length}")
public class CodeBlockBean {

  public String str = "SpELScriptAssert";

}
