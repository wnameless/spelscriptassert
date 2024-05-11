package com.github.wnameless.spring.validation.spelscriptassert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.wnameless.spring.validation.spelscriptassert.bean.CodeBlockBean;
import com.github.wnameless.spring.validation.spelscriptassert.bean.ComponentBean;
import com.github.wnameless.spring.validation.spelscriptassert.bean.CustomBean;
import com.github.wnameless.spring.validation.spelscriptassert.bean.HelperBean;
import com.github.wnameless.spring.validation.spelscriptassert.bean.MixBean;
import com.github.wnameless.spring.validation.spelscriptassert.bean.PerformIfBean;
import com.github.wnameless.spring.validation.spelscriptassert.bean.PrimitiveBooleanBean;
import com.github.wnameless.spring.validation.spelscriptassert.bean.TargetBean;
import jakarta.validation.Validator;

@SpringBootTest(classes = SpringTestApplication.class)
public class SpELScriptAssertTests {

  @Autowired
  Validator validator;

  @Test
  public void testPrimitiveBooleanBean() {
    var bean = new PrimitiveBooleanBean();
    var violations = List.copyOf(validator.validate(bean));
    assertEquals(2, violations.size());
    assertEquals("SpELScriptAssert validation failed", violations.get(0).getMessage());
  }

  @Test
  public void testPerformIfBean() {
    var bean = new PerformIfBean();
    var violations = List.copyOf(validator.validate(bean));
    assertEquals(17, violations.size());
  }

  @Test
  public void testHelperBean() {
    var bean = new HelperBean();
    var violations = List.copyOf(validator.validate(bean));
    assertEquals(0, violations.size());
  }

  @Test
  public void testComponentBean() {
    var bean = new ComponentBean();
    var violations = List.copyOf(validator.validate(bean));
    assertEquals(0, violations.size());
  }

  @Test
  public void testCustomBean() {
    var bean = new CustomBean();
    var violations = List.copyOf(validator.validate(bean));
    assertEquals(1, violations.size());
    assertEquals("Custom message: test", violations.get(0).getMessage());
  }

  @Test
  public void testMixBean() {
    var bean = new MixBean();
    var violations = List.copyOf(validator.validate(bean));
    assertEquals(1, violations.size());
    assertEquals("output != (a + b) * c", violations.get(0).getMessage());
  }

  @Test
  public void testTargetBean() {
    var bean = new TargetBean();
    var violations = List.copyOf(validator.validate(bean));
    assertEquals(1, violations.size());
    assertEquals("Multiplication(a,b,c): 1000 > 100", violations.get(0).getMessage());
  }

  @Test
  public void testCodeBlockBean() {
    var bean = new CodeBlockBean();
    var violations = List.copyOf(validator.validate(bean));
    assertEquals(1, violations.size());
    assertEquals("str is NOT empty, str is 'SpELScriptAssert' with length of 16",
        violations.get(0).getMessage());
  }

}
