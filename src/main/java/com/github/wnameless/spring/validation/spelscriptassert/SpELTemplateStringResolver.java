package com.github.wnameless.spring.validation.spelscriptassert;

import static java.util.Objects.requireNonNull;
import java.util.Optional;
import java.util.regex.Pattern;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * {@link SpELTemplateStringResolver} is designed to resolve any template string which contains SpEL
 * code blocks(#{...}) in it.
 */
public final class SpELTemplateStringResolver {

  private static final Pattern DEFAULT_PATTERN = Pattern.compile("#\\{([^}]+)\\}");

  private final Pattern codeBlockPattern;
  private final ExpressionParser exprParser;

  /**
   * Creates {@link SpELTemplateStringResolver} with {@link #DEFAULT_PATTERN} and
   * {@link SpelExpressionParser}.
   */
  public SpELTemplateStringResolver() {
    codeBlockPattern = DEFAULT_PATTERN;
    exprParser = new SpelExpressionParser();
  }

  /**
   * Creates {@link SpELTemplateStringResolver} with given codeBlockPattern and exprParser.
   * 
   * @param codeBlockPattern used to find SpEL code blocks
   * @param exprParser used to parse SpEL expression
   */
  public SpELTemplateStringResolver(Pattern codeBlockPattern, ExpressionParser exprParser) {
    this.codeBlockPattern = requireNonNull(codeBlockPattern);
    this.exprParser = requireNonNull(exprParser);
  }

  /**
   * Creates {@link SpELTemplateStringResolver} with given codeBlockPattern and
   * {@link SpelExpressionParser}.
   * 
   * @param codeBlockPattern used to find SpEL code blocks
   */
  public SpELTemplateStringResolver(Pattern codeBlockPattern) {
    this.codeBlockPattern = requireNonNull(codeBlockPattern);
    exprParser = new SpelExpressionParser();
  }

  /**
   * Creates {@link SpELTemplateStringResolver} with {@link #DEFAULT_PATTERN} and given exprParser.
   * 
   * @param exprParser used to parse SpEL expression
   */
  public SpELTemplateStringResolver(ExpressionParser exprParser) {
    codeBlockPattern = DEFAULT_PATTERN;
    this.exprParser = requireNonNull(exprParser);
  }

  /**
   * Resolves a template string by evaluating all the SpEL code blocks within it.
   * 
   * @param templateString which may contain SpEL code blocks
   * @param evaluationContext used to evaluate all parsed expressions
   * @return a resolved template string
   */
  public String resolve(String templateString, EvaluationContext evaluationContext) {
    return codeBlockPattern.matcher(templateString).replaceAll(matchResult -> {
      String expr = matchResult.group(1);
      return Optional.ofNullable(exprParser.parseExpression(expr).getValue(evaluationContext))
          .map(Object::toString).orElse("null");
    });
  }

}
