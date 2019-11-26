package com.belatrixsf.di.config;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.SimpleThreadScope;

@Configuration
public class AppConfigWithoutProxy {

  @Bean
  public CustomScopeConfigurer customScopeConfigurer() {
    CustomScopeConfigurer configurer = new CustomScopeConfigurer();
    configurer.addScope("thread", new SimpleThreadScope());
    return configurer;
  }

  @Bean
  @Scope(scopeName = "prototype")
  public RequestInfo requestInfo() {
    return new RequestInfo();
  }


  @Bean
  public RequestHandler requestHandler(RequestInfo requestInfo) {
    return new RequestHandler(requestInfo);
  }

}
