[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.spring/spelscriptassert/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless.spring/spelscriptassert)
[![codecov](https://codecov.io/gh/wnameless/spelscriptassert/branch/master/graph/badge.svg)](https://codecov.io/gh/wnameless/spelscriptassert)

spelscriptassert
=============
Spring Expression Language(SpEL) Jakarta validator.

## Purpose
Using Spring Expression Language(SpEL) to validate a Java bean.

Bean
```java
@SpELScriptAssert(script = "@mathComponent.multiply(#add(a, b), c) == result", reportOn = "result",
    performIf = "a != null && b != null && c != null", helpers = {MathHelper.class})
public class MixBean {

  public Integer result = 9;

  public Integer a = 1;

  public Integer b = 2;

  public Integer c = 3;

}
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
