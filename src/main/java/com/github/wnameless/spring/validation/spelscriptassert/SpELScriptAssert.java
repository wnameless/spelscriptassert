package com.github.wnameless.spring.validation.spelscriptassert;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * A class-level constraint, that evaluates a SpEL script expression against the annotated bean.
 * 
 * @author Wei-Ming Wu
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = SpELScriptAssertValidator.class)
@Repeatable(SpELScriptAsserts.class)
public @interface SpELScriptAssert {

  String message() default "SpELScriptAssert validation failed";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * @return The name of the property for which you would like to report a validation error. If
   *         given, the resulting constraint violation will be reported on the specified property.
   *         If not given, the constraint violation will be reported on the annotated bean.
   */
  String reportOn() default "";

  /**
   * @return The validation SpEL script.
   */
  String script();

  /**
   * @return The optional target which is written in SpEL script.
   */
  String target() default "";

  /**
   * @return A class is used to print the target value in message template.
   */
  Class<? extends TargetPrinter> targetPrinter() default StandardTargetPrinter.class;

  /**
   * @return The evaluating condition of the validation SpEL script.
   */
  String performIf() default "";

  /**
   * @return Classes with static methods can be called as #staticMethod(arg1, ...) in the validation
   *         SpEL script expression.
   */
  Class<?>[] helpers() default {};

}
