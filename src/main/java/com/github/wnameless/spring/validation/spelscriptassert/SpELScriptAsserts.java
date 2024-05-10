package com.github.wnameless.spring.validation.spelscriptassert;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The containing annotation type for {@link SpELScriptAssert}.
 * 
 * @author Wei-Ming Wu
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SpELScriptAsserts {

  /**
   * @return An array of {@link SpELScriptAssert} to be validated.
   */
  SpELScriptAssert[] value();

}
