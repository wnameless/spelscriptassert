package com.github.wnameless.spring.validation;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.TypeConverter;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.expression.spel.support.StandardTypeConverter;
import org.springframework.lang.NonNull;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * {@link SpELScriptAssertValidator} is designed to execuate validation SpEL scripts of
 * {@link SpELScriptAssert}. It implements Jakarta {@link ConstraintValidator} and Spring
 * {@link BeanFactoryAware}.
 * 
 * @author Wei-Ming Wu
 */
public class SpELScriptAssertValidator
    implements ConstraintValidator<SpELScriptAssert, Object>, BeanFactoryAware {

  private static final ExpressionParser EXPR_PARSER = new SpelExpressionParser();
  private static final TypeConverter TYPE_CONV = new StandardTypeConverter();

  private BeanFactory beanFactory;

  private Expression expression;
  private Expression performIfExpr;
  private List<Method> helperMethods = new ArrayList<>();

  @Override
  public void setBeanFactory(@NonNull BeanFactory beanFactory) {
    this.beanFactory = beanFactory;
  }

  @Override
  public void initialize(SpELScriptAssert constraint) {
    expression = EXPR_PARSER.parseExpression(constraint.script());
    if (!constraint.performIf().isBlank()) {
      performIfExpr = EXPR_PARSER.parseExpression(constraint.performIf());
    }

    // Extract all helper methods from helper classes
    Arrays.asList(constraint.helpers()) //
        .stream().flatMap(clazz -> Stream.of(clazz.getMethods()))
        .filter(m -> Modifier.isStatic(m.getModifiers())) //
        .forEach(helperMethods::add);
  }

  @Override
  public boolean isValid(Object validatedValue,
      ConstraintValidatorContext constraintValidatorContext) {
    if (validatedValue == null) return true;

    EvaluationContext evaluationContext = createEvaluationContext(validatedValue);
    if (performIfExpr == null || evaluate(performIfExpr, evaluationContext)) {
      return evaluate(expression, evaluationContext);
    }
    return true;
  }

  private boolean evaluate(Expression expression, EvaluationContext context) {
    Object value = expression.getValue(context);

    if (value instanceof Boolean bool) {
      return bool;
    }
    if (value instanceof Number number) {
      return number.intValue() != 0;
    }
    if (value instanceof Collection coll) {
      return !coll.isEmpty();
    }
    if (value instanceof Map<?, ?> map) {
      return !map.isEmpty();
    }
    if (value instanceof byte[] byteAry) {
      return byteAry.length != 0;
    }
    if (value instanceof short[] shortAry) {
      return shortAry.length != 0;
    }
    if (value instanceof int[] intAry) {
      return intAry.length != 0;
    }
    if (value instanceof long[] longAry) {
      return longAry.length != 0;
    }
    if (value instanceof float[] floatAry) {
      return floatAry.length != 0;
    }
    if (value instanceof double[] doubleAry) {
      return doubleAry.length != 0;
    }
    if (value instanceof boolean[] booleanAry) {
      return booleanAry.length != 0;
    }
    if (value instanceof char[] charAry) {
      return charAry.length != 0;
    }
    if (value instanceof Object[] array) {
      return array.length != 0;
    }
    if (value instanceof CharSequence words) {
      return !String.valueOf(words).isBlank();
    }
    if (value instanceof Optional opt) {
      if (opt.isEmpty()) {
        return false;
      } else {
        if (opt.get() instanceof Boolean optBool) {
          return optBool;
        }
        return true;
      }
    }

    return value != null;
  }

  private StandardEvaluationContext createEvaluationContext(Object rootObject) {
    var context = new StandardEvaluationContext(rootObject);
    context.setTypeConverter(TYPE_CONV);

    if (beanFactory != null) {
      context.setBeanResolver(new BeanFactoryResolver(beanFactory));
    }

    helperMethods.stream()
        .forEach(helperMethod -> context.registerFunction(helperMethod.getName(), helperMethod));

    return context;
  }

}
