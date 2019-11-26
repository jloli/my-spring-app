package com.belatrixsf.di.scopes;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.SimpleThreadScope;

public class SpringScopesTest {

  @Test
  public void whenObjectIsSingleton_shouldReturnTheSameBean() {
    ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConnection.class);

    DatabaseConnection cnx1 = context.getBean(DatabaseConnection.class);
    cnx1.setUrl("http://localhost");
    DatabaseConnection cnx2 = context.getBean(DatabaseConnection.class);

    assertThat(cnx1).isEqualTo(cnx2);
    assertThat(cnx1.getUrl()).isEqualTo("http://localhost");
    assertThat(cnx1.getUrl()).isEqualTo(cnx1.getUrl());
  }

  @Test
  public void whenObjectIsPrototype_shouldReturnDifferentBeans() {
    ApplicationContext context = new AnnotationConfigApplicationContext(Person.class);

    Person personA = context.getBean(Person.class);
    personA.setName("Juan");
    personA.setLastName("Loli");

    Person personB = context.getBean(Person.class);
    personB.setName("Andres");
    personB.setLastName("Perez");

    assertThat(personA).isNotEqualTo(personB);
    assertThat(personA.getName()).isNotEqualTo(personB.getName());
    assertThat(personA.getLastName()).isNotEqualTo(personB.getLastName());
  }

  @Test
  public void whenObjectsAreFromDifferentContainers_shouldReturnDifferentBeans() {
    ApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConnection.class);
    ApplicationContext anotherContext = new AnnotationConfigApplicationContext(
        DatabaseConnection.class);

    DatabaseConnection cnx1 = context.getBean(DatabaseConnection.class);
    cnx1.setUrl("http://url1");
    DatabaseConnection cnx2 = anotherContext.getBean(DatabaseConnection.class);
    cnx2.setUrl("http://url2");

    assertThat(cnx1).isNotEqualTo(cnx2);
    assertThat(cnx1.getUrl()).isNotEqualTo(cnx2.getUrl());
  }


  @Test
  public void whenObjectsAreBoundToThread_shouldReturnDifferentBeans() {
    GenericApplicationContext context = new AnnotationConfigApplicationContext();
    context.getBeanFactory().registerScope("thread", new SimpleThreadScope());
    context.registerBean(SimpleTask.class);
    context.refresh();

    SimpleTask task1 = context.getBean(SimpleTask.class);
    SimpleTask task2 = context.getBean(SimpleTask.class);

    System.out.println(">>> Running in thread " + Thread.currentThread().getId());
    assertThat(task1.getUniqueId()).isEqualTo(task2.getUniqueId());

    Runnable runnable = () -> {
      System.out.println(">>> Running in thread " + Thread.currentThread().getId());
      SimpleTask task3 = context.getBean(SimpleTask.class);
      assertThat(task1.getUniqueId()).isEqualTo(task2.getUniqueId());
      assertThat(task1.getUniqueId()).isNotEqualTo(task3.getUniqueId());

    };

    Thread t = new Thread(runnable);
    t.start();

  }


}
