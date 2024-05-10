package com.github.wnameless.spring.validation.spelscriptassert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

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
