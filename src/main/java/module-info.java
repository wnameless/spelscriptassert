module com.github.wnameless.spring.validation.spelscriptassert {
  requires org.slf4j;
  requires transitive spring.core;
  requires transitive spring.context;
  requires transitive spring.expression;
  requires transitive spring.beans;
  requires transitive jakarta.validation;

  exports com.github.wnameless.spring.validation.spelscriptassert;
}
