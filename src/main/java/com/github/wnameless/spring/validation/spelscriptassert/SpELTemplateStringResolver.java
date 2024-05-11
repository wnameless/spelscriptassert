package com.github.wnameless.spring.validation.spelscriptassert;

import static java.util.Objects.requireNonNull;
import java.util.Optional;
import java.util.regex.Pattern;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public final class SpELTemplateStringResolver {

  private final Pattern codeBlockPattern;
  private final ExpressionParser exprParser;

  public SpELTemplateStringResolver() {
    codeBlockPattern = Pattern.compile("#\\{([^}]+)\\}");
    exprParser = new SpelExpressionParser();
  }

  public SpELTemplateStringResolver(Pattern codeBlockPattern, ExpressionParser exprParser) {
    this.codeBlockPattern = requireNonNull(codeBlockPattern);
    this.exprParser = requireNonNull(exprParser);
  }

  public SpELTemplateStringResolver(Pattern codeBlockPattern) {
    this.codeBlockPattern = requireNonNull(codeBlockPattern);
    exprParser = new SpelExpressionParser();
  }

  public SpELTemplateStringResolver(ExpressionParser exprParser) {
    codeBlockPattern = Pattern.compile("#\\{([^}]+)\\}");
    this.exprParser = requireNonNull(exprParser);
  }

  public String resolve(String templateString, EvaluationContext evaluationContext) {
    return codeBlockPattern.matcher(templateString).replaceAll(matchResult -> {
      String expr = matchResult.group(1);
      return Optional.ofNullable(exprParser.parseExpression(expr).getValue(evaluationContext))
          .map(Object::toString).orElse("null");
    });
  }

}
