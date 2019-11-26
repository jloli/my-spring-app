package com.belatrixsf.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.belatrixsf.beans"})
public class BeanDefinitions implements CommandLineRunner {

  @Autowired
  private ConfigurableListableBeanFactory beanFactory;


  @Override
  public void run(String... args) throws Exception {
    BeanDefinition beanDefinition = beanFactory.getBeanDefinition("simpleMovieLister");
    System.out.println(beanDefinition.toString());
  }

  public static void main(String[] args) {
    SpringApplication.run(BeanDefinitions.class);
  }

}
