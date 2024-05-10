[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.spring/spelscriptassert/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.spring/spelscriptassert)
[![codecov](https://codecov.io/gh/wnameless/spelscriptassert/branch/master/graph/badge.svg)](https://codecov.io/gh/wnameless/spelscriptassert)

spelscriptassert
=============
Spring Expression Language(SpEL) Jakarta validator.

## Purpose
Using Spring Expression Language(SpEL) to validate a Java bean.

Bean
```java
@SpELScriptAssert( //
    script = "@mathComponent.multiply(#add(a, b), c) == result", //
    performIf = "a != null && b != null && c != null", //
    helpers = {MathHelper.class}, //
    reportOn = "result", //
    message = "{com.github.wnameless.spring.validation.SpELScriptAssert.MixBean}")
public class MixBean {

  public Integer result = 10;
  public Integer a = 1;
  public Integer b = 2;
  public Integer c = 3;

}
```

Validation message
```
result != (a + b) * c
```

Message properties
```properties
com.github.wnameless.spring.validation.SpELScriptAssert.MixBean={reportOn} != (a + b) * c
```

Spring Component
```java
@Component
public class MathComponent {

  public int multiply(int a, int b) {
    return a * b;
  }

}
```

Helper class
```java
public class MathHelper {

  public static int add(int a, int b) {
    return a + b;
  }

}
```

# Maven Repo
```xml
<dependency>
	<groupId>com.github.wnameless.spring</groupId>
	<artifactId>spelscriptassert</artifactId>
	<version>${newestVersion}</version>
	<!-- Newest version shows in the maven-central badge above -->
</dependency>
```

# Quick Start
Inject Spring MessageSource to the Jakarta Validator
```java
@Configuration
public class SpELScriptAssertConfig {

  @Autowired
  MessageSource messageSource;

  @Bean
  LocalValidatorFactoryBean validatorFactoryBean() {
    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
    bean.setValidationMessageSource(messageSource);
    return bean;
  }

}
```

# SpELScriptAssert Evaluation Strategies
SpELScriptAssert expects all evaluation scripts (both `script` and `performIf`) which always return Boolean values, however it also accepts other returning value types and treats them as Boolean values.

### 1. Non-zero number is True, zero is False.
```java
@SpELScriptAssert(script = "a", reportOn = "a", message = "{reportOn} is false")
@SpELScriptAssert(script = "b", reportOn = "b", message = "{reportOn} is false")
public class NumBean {
  public int a = 0;
  public long b = 1L;
}
```
Validation message:
```
a is false
```

### 2. Non-empty Array or Collection is True, otherwise False.
```java
@SpELScriptAssert(script = "a", reportOn = "a", message = "{reportOn} is false")
@SpELScriptAssert(script = "b", reportOn = "b", message = "{reportOn} is false")
@SpELScriptAssert(script = "c", performIf = "d", reportOn = "c", message = "{reportOn} is false")
public class ArrayCollectionBean {
  public int[] a = {};
  public String[] b = {"I"};
  public List<?> c = List.of();
  public Map<?, ?> d = Map.of("II", "III");
}
```
Validation message:
```
a is false
c is false
```

### 3. Non-blank CharSequence is True, otherwise False.
```java
@SpELScriptAssert(script = "a", reportOn = "a", message = "{reportOn} is false")
@SpELScriptAssert(script = "b", reportOn = "b", message = "{reportOn} is false")
public class CharSequenceBean {
  public String a = "   ";
  public CharSequence b = "c";
}
```
Validation message:
```
a is false
```

### 4. Non-empty Optional is True, otherwise False. However Optional<Boolean> can only be dertermined by its own returning value.
```java
@SpELScriptAssert(script = "a", reportOn = "a", message = "{reportOn} is false")
@SpELScriptAssert(script = "b", reportOn = "b", message = "{reportOn} is false")
@SpELScriptAssert(script = "c", reportOn = "c", message = "{reportOn} is false")
public class OptionalBean {
  public Optional<String> a = Optional.empty();
  public Optional<String> b = Optional.of("I");
  public Optional<Boolean> c = Optional.of(false);
}

```
Validation message:
```
a is false
c is false
```

