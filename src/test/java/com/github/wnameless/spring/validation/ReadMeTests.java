package com.github.wnameless.spring.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.wnameless.spring.validation.readme.ArrayCollectionBean;
import com.github.wnameless.spring.validation.readme.CharSequenceBean;
import com.github.wnameless.spring.validation.readme.NullBean;
import com.github.wnameless.spring.validation.readme.NumBean;
import com.github.wnameless.spring.validation.readme.OptionalBean;
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

}
