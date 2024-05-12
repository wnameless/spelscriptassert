[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.spring/spelscriptassert/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.spring/spelscriptassert)
[![codecov](https://codecov.io/gh/wnameless/spelscriptassert/branch/master/graph/badge.svg)](https://codecov.io/gh/wnameless/spelscriptassert)

SpELScriptAssert
=============
Spring Expression Language(SpEL) Jakarta validator.

## Purpose
Apply SpEL to Hibernate @ScriptAssert by creating a new validation annotation @SpELScriptAssert.

## Demo - basic usage
Using Spring Expression Language(SpEL) to validate a Java bean.

Bean
```java
@SpELScriptAssert( //
    script = "@mathComponent.multiply(#add(a, b), c) == output", //
    performIf = "a != null && b != null && c != null", //
    helpers = {MathHelper.class}, //
    reportOn = "output", //
    message = "{validation.MixBean.output}")
public class MixBean {

  public Integer output = 10;
  public Integer a = 1;
  public Integer b = 2;
  public Integer c = 3;

}
```

Validation message:
```
output != (a + b) * c
```

Message properties:
```properties
validation.MixBean.output={reportOn} != (a + b) * c
```

Spring Component:
```java
@Component
public class MathComponent {
  public int multiply(int a, int b) { return a * b; }
}
```

Helper class:
```java
public class MathHelper {
  public static int add(int a, int b) { return a + b; }
}
```

## Demo - advanced usage
`target` evaluated value can be reused in script and message attributes by calling **#target**
<br>
`#{...}` blocks in message attribute are all SpEL expressions which will be evaluated eventually

Bean
```java
@SpELScriptAssert( //
    target = "a * b * c", //
    script = "#target >= 10", //
    message = "#{#target} {validation.AdvancedBean.msg} #{T(java.lang.Math).abs(#target - 10)}")
public class AdvancedBean {

  public Integer a = 1;
  public Integer b = 2;
  public Integer c = 3;

}
```

Validation message:
```
6 is NOT greater than or equal to Ten before adding 4
```

Message properties:
```properties
validation.AdvancedBean.msg=is NOT greater than or equal to Ten before adding
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

## Quick Start - configure ValidationMessages resource bundle
Spring Environment - Inject MessageSource into the Jakarta Validator.
```java
@Configuration
public class SpELScriptAssertConfig {
  @Bean
  LocalValidatorFactoryBean validatorFactoryBean(MessageSource messageSource) {
    var bean = new LocalValidatorFactoryBean();
    bean.setValidationMessageSource(messageSource);
    return bean;
  }
}
```

Standalone mode
```java
// Jakarta Validator looks up `ValidationMessages.properties` file under classpath by default
```

# Feature List<a id='top'></a>
| Name | Description | Since |
| --- | --- | --- |
| [SpEL code blocks in message template](#1.1.0_1) | Them message attribute of @SpELScriptAssert now accepts #{spel_expr} code blocks. | v1.1.0 |
| [target](#1.1.0_2) | An optional attribute can hold an evaluation result for further use. | v1.1.0 |
| Optional Spring Environment | @SpELScriptAssert can be used standalone or with Spring Environment. The only difference is that `@springComponent` syntax of SpEL expression won't work in standalone mode. | v1.0.0 |
| Java Module supported  | The module-info.java included. | v1.0.0 |
| performIf | A condition expression for determining whether a validation is performed or NOT. | v1.0.0 |
| helpers | Register static methods from given Helper Classes. Methods can be called by `#helperMethod` syntax in SpEL expression. | v1.0.0 |
| reportOn | Same as `reportOn` in Hibernate validation @ScriptAssert annotation. | v1.0.0 |
| [Smart Evaluation Strategies](#1.0.0_1) | Smart boolean convertion for evaluation values. | v1.0.0 |

## [:top:](#top) SpEL code blocks(#{...}) in message template<a id="1.1.0_1"></a>
```java
@SpELScriptAssert( //
    script = "str.empty", //
    message = "str is NOT empty, str is '#{str}' with length of #{str.length}")
public class CodeBlockBean {
  public String str = "SpELScriptAssert";
}
```
Validation message:
```
str is NOT empty, str is 'SpELScriptAssert' with length of 16
```

## [:top:](#top) Utilize `target` attribute<a id="1.1.0_2"></a>
The `target` attribute can hold an evaluation value for further uses in `script` and `message` contents. It can not only impove the readability but also save computation time if the evaluation of the expression in `target` attribute is highly time comsuming.
```java
@SpELScriptAssert( //
    target = "bi1.multiply(bi2)", //
    script = "#target.toString.length < 10", //
    reportOn = "bi1 * bi2", //
    message = "{reportOn} = #{#target} and the digits are more than 10")
public class TargetBean {
  public BigInteger bi1 = new BigInteger("123456789");
  public BigInteger bi2 = new BigInteger("987654321");
}
```
Validation message:
```
bi1 * bi2 = 121932631112635269 and the digits are more than 10
```

## [:top:](#top) Smart Evaluation Strategies<a id="1.0.0_1"></a>
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
  public CharSequence b = "I";
}
```
Validation message:
```
a is false
```

### 4. Non-empty Optional is True, otherwise False. However Optional\<Boolean\> can only be dertermined by its own returning value.
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

### 5. Default behavior: Non-null is True, otherwise False.
```java
@SpELScriptAssert(script = "a", reportOn = "a", message = "{reportOn} is false")
@SpELScriptAssert(script = "b", reportOn = "b", message = "{reportOn} is false")
public class NullBean {
  public BigInteger a = null;
  public BigDecimal b = BigDecimal.valueOf(123L);
}
```
Validation message:
```
a is false
```
