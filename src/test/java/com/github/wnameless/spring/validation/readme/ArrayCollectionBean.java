package com.github.wnameless.spring.validation.readme;

import java.util.List;
import java.util.Map;
import com.github.wnameless.spring.validation.SpELScriptAssert;

@SpELScriptAssert(script = "a", reportOn = "a", message = "{reportOn} is false")
@SpELScriptAssert(script = "b", reportOn = "b", message = "{reportOn} is false")
@SpELScriptAssert(script = "c", performIf = "d", reportOn = "c", message = "{reportOn} is false")
public class ArrayCollectionBean {
  public int[] a = {};
  public String[] b = {"I"};
  public List<?> c = List.of();
  public Map<?, ?> d = Map.of("II", "III");
}
