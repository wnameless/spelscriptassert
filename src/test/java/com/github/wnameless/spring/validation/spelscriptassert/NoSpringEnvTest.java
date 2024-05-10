package com.github.wnameless.spring.validation.spelscriptassert;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.github.wnameless.spring.validation.spelscriptassert.bean.ComponentBean;
import com.github.wnameless.spring.validation.spelscriptassert.bean.CustomBean;
import com.github.wnameless.spring.validation.spelscriptassert.bean.HelperBean;
import com.github.wnameless.spring.validation.spelscriptassert.bean.PerformIfBean;
import com.github.wnameless.spring.validation.spelscriptassert.bean.PrimitiveBooleanBean;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class NoSpringEnvTest {

  private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private static final Validator validator = factory.getValidator();

  @Test
  public void testNull() {
    assertTrue(new SpELScriptAssertValidator().isValid(null, null));
  }

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
    HelperBean bean = new HelperBean();
    var violations = List.copyOf(validator.validate(bean));
    assertEquals(0, violations.size());
  }

  @Test
  public void testComponentBeanWithoutSpringEnv() {
    assertThrows(ValidationException.class, () -> {
      var bean = new ComponentBean();
      var violations = List.copyOf(validator.validate(bean));
      assertEquals(0, violations.size());
    });
  }

  @Test
  public void testCustomBean() {
    var bean = new CustomBean();
    var violations = List.copyOf(validator.validate(bean));
    assertEquals(1, violations.size());
    assertEquals("Custom message: test", violations.get(0).getMessage());
  }

}
