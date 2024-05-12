package com.github.wnameless.spring.validation.spelscriptassert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.wnameless.spring.validation.spelscriptassert.readme.AdvancedBean;
import com.github.wnameless.spring.validation.spelscriptassert.readme.ArrayCollectionBean;
import com.github.wnameless.spring.validation.spelscriptassert.readme.CharSequenceBean;
import com.github.wnameless.spring.validation.spelscriptassert.readme.NullBean;
import com.github.wnameless.spring.validation.spelscriptassert.readme.NumBean;
import com.github.wnameless.spring.validation.spelscriptassert.readme.OptionalBean;
import com.github.wnameless.spring.validation.spelscriptassert.readme.TargetBean;
import jakarta.validation.Validator;

@SpringBootTest(classes = SpringTestApplication.class)
public class ReadMeTests {

  @Autowired
  Validator validator;

  @Test
  public void testNumBean() {
    var bean = new NumBean();
    var violations = List.copyOf(validator.validate(bean));
    assertEquals(1, violations.size());
    assertEquals("a is false", violations.get(0).getMessage());
  }

  @Test
  public void testArrayCollectionBean() {
    var bean = new ArrayCollectionBean();
    var violations = List.copyOf(validator.validate(bean));
    assertEquals(2, violations.size());
    assertEquals(Set.of("a is false", "c is false"),
        violations.stream().map(v -> v.getMessage()).collect(Collectors.toSet()));
  }

  @Test
  public void testCharSequenceBean() {
    var bean = new CharSequenceBean();
    var violations = List.copyOf(validator.validate(bean));
    assertEquals(1, violations.size());
    assertEquals("a is false", violations.get(0).getMessage());
  }

  @Test
  public void testOptionalBean() {
    var bean = new OptionalBean();
    var violations = List.copyOf(validator.validate(bean));
    assertEquals(2, violations.size());
    assertEquals(Set.of("a is false", "c is false"),
        violations.stream().map(v -> v.getMessage()).collect(Collectors.toSet()));
  }

  @Test
  public void testNullBean() {
    var bean = new NullBean();
    var violations = List.copyOf(validator.validate(bean));
    assertEquals(1, violations.size());
    assertEquals("a is false", violations.get(0).getMessage());
  }

  @Test
  public void testAdvancedBean() {
    var bean = new AdvancedBean();
    var violations = List.copyOf(validator.validate(bean));
    assertEquals(1, violations.size());
    assertEquals("6 is NOT greater than or equal to Ten before adding 4",
        violations.get(0).getMessage());
  }

  @Test
  public void testTargetBean() {
    var bean = new TargetBean();
    var violations = List.copyOf(validator.validate(bean));
    assertEquals(1, violations.size());
    assertEquals("bi1 * bi2 = 121932631112635269 and the digits are more than 10",
        violations.get(0).getMessage());
  }

}
