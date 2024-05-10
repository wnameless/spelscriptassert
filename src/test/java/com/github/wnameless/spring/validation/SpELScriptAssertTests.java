package com.github.wnameless.spring.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.wnameless.spring.validation.bean.ComponentBean;
import com.github.wnameless.spring.validation.bean.CustomBean;
import com.github.wnameless.spring.validation.bean.HelperBean;
import com.github.wnameless.spring.validation.bean.MixBean;
import com.github.wnameless.spring.validation.bean.PerformIfBean;
import com.github.wnameless.spring.validation.bean.PrimitiveBooleanBean;
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
    assertEquals("result != (a + b) * c", violations.get(0).getMessage());
  }

}
