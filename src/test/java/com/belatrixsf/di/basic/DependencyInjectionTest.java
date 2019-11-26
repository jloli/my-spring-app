package com.belatrixsf.di.basic;

import static org.assertj.core.api.Assertions.assertThat;

import com.belatrixsf.beans.InMemoryMovieFinder;
import com.belatrixsf.beans.NonRepeatedInMemoryMovieFinder;
import com.belatrixsf.beans.SimpleMovieLister;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DependencyInjectionTest {

  @Test
  public void whenInjectionIsByConstructor_shouldReturnObject() {
    ApplicationContext context = new AnnotationConfigApplicationContext(
        InMemoryMovieFinder.class,
        SimpleMovieLister.class);

    SimpleMovieLister movieLister = context.getBean(SimpleMovieLister.class);

    assertThat(movieLister.findMoviesByDirector("Kubrick")).hasSize(1);
  }

  @Test
  public void whenDependencyIsNotSatisfied_shouldFail() {
    try {
      ApplicationContext context = new AnnotationConfigApplicationContext(SimpleMovieLister.class);
      SimpleMovieLister movieLister = context.getBean(SimpleMovieLister.class);
    } catch (Exception e) {
      assertThat(e).isInstanceOf(UnsatisfiedDependencyException.class);
      assertThat(e.getCause()).isInstanceOf(NoSuchBeanDefinitionException.class);
    }
  }

  @Test
  public void whenMoreThanOneSatisfies_shouldFail() {
    try {
      ApplicationContext context = new AnnotationConfigApplicationContext(
          SimpleMovieLister.class,
          InMemoryMovieFinder.class,
          FileMovieFinder.class);

      SimpleMovieLister movieLister = context.getBean(SimpleMovieLister.class);

    } catch (Exception e) {
      assertThat(e).isInstanceOf(UnsatisfiedDependencyException.class);
      assertThat(e.getCause()).isInstanceOf(NoUniqueBeanDefinitionException.class);
    }
  }

  @Test
  public void whenQualifiedDependencyIsDeclared_shouldReturnObject() {
    ApplicationContext context = new AnnotationConfigApplicationContext(
        InMemoryMovieFinder.class,
        FileMovieFinder.class,
        SimpleQualifiedMovieLister.class);

    SimpleQualifiedMovieLister movieLister = context.getBean(SimpleQualifiedMovieLister.class);

    assertThat(movieLister.findMoviesByDirector("Kubrick")).hasSize(2);
  }

  @Test
  public void whenPrimaryQualifierIsUserd_shouldReturnObject() {
    ApplicationContext context = new AnnotationConfigApplicationContext(
        InMemoryMovieFinder.class,
        FileMovieFinder.class,
        NonRepeatedInMemoryMovieFinder.class,
        SimpleMovieLister.class);

    SimpleMovieLister movieLister = context.getBean(SimpleMovieLister.class);

    assertThat(movieLister.findMoviesByDirector("Kubrick")).hasSize(0);
    assertThat(movieLister.findMoviesByDirector("Cameron")).hasSize(1);
  }

  @Test
  public void whenDependencyIsAList_shouldReturnObject() {
    ApplicationContext context = new AnnotationConfigApplicationContext(
        InMemoryMovieFinder.class,
        FileMovieFinder.class,
        CompoundMovieLister.class);

    CompoundMovieLister movieLister = context.getBean(CompoundMovieLister.class);

    assertThat(movieLister.findMoviesByDirector("Kubrick")).hasSize(3);
  }

}
