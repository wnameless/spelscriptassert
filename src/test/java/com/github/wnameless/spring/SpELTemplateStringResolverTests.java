package com.github.wnameless.spring;

import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import com.github.wnameless.spring.validation.spelscriptassert.SpELTemplateStringResolver;

public class SpELTemplateStringResolverTests {

  @Test
  public void testAllConstructors() {
    new SpELTemplateStringResolver();
    new SpELTemplateStringResolver(Pattern.compile("#\\{([^}]+)\\}"), new SpelExpressionParser());
    new SpELTemplateStringResolver(Pattern.compile("#\\{([^}]+)\\}"));
    new SpELTemplateStringResolver(new SpelExpressionParser());
  }

  @Test
  public void testAllConstructorExceptions() {
    assertThrows(NullPointerException.class, () -> {
      new SpELTemplateStringResolver(null, new SpelExpressionParser());
    });
    assertThrows(NullPointerException.class, () -> {
      new SpELTemplateStringResolver(Pattern.compile("#\\{([^}]+)\\}"), null);
    });
    assertThrows(NullPointerException.class, () -> {
      new SpELTemplateStringResolver((Pattern) null);
    });
    assertThrows(NullPointerException.class, () -> {
      new SpELTemplateStringResolver((SpelExpressionParser) null);
    });
  }

}
