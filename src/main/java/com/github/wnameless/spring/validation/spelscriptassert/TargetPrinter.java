package com.github.wnameless.spring.validation.spelscriptassert;

/**
 * An interface defines only one method which is used to print the target value of
 * {@link SpELScriptAssert} in the message template.
 */
public interface TargetPrinter {

  String print(Object target);

}
