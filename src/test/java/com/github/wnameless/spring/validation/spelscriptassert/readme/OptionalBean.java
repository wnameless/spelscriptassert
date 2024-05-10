package com.github.wnameless.spring.validation.spelscriptassert.readme;

import java.util.Optional;
import com.github.wnameless.spring.validation.spelscriptassert.SpELScriptAssert;

@SpELScriptAssert(script = "a", reportOn = "a", message = "{reportOn} is false")
@SpELScriptAssert(script = "b", reportOn = "b", message = "{reportOn} is false")
@SpELScriptAssert(script = "c", reportOn = "c", message = "{reportOn} is false")
public class OptionalBean {
  public Optional<String> a = Optional.empty();
  public Optional<String> b = Optional.of("I");
  public Optional<Boolean> c = Optional.of(false);
}
