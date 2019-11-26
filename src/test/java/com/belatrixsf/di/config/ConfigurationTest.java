package com.belatrixsf.di.config;

import static org.assertj.core.api.Assertions.assertThat;

import com.belatrixsf.beans.SimpleMovieLister;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationTest {

  @Test
  public void whenAProxyIsUsed_shouldReturnCompleteList() throws Exception {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfigWithProxy.class);

    RequestHandler handler = context.getBean(RequestHandler.class);

    for (int i = 0; i < 5; i++) {
      handler.addRequestIdToList();
    }

    assertThat(handler.getRequestsIds()).hasSize(5);
  }


  @Test
  public void whenAProxyIsNotUsed_shouldReturnOneObjectInList() throws Exception {
    ApplicationContext context = new AnnotationConfigApplicationContext(
        AppConfigWithoutProxy.class);

    RequestHandler handler = context.getBean(RequestHandler.class);

    for (int i = 0; i < 5; i++) {
      handler.addRequestIdToList();
    }

    assertThat(handler.getRequestsIds()).hasSize(1);
  }

  @Test
  public void whenAutoScanIsUsed_shouldUseNonRepeatedMemoryFinder() {
    ApplicationContext context = new AnnotationConfigApplicationContext(
        AppConfigWithComponentScan.class);

    SimpleMovieLister movieLister = context.getBean(SimpleMovieLister.class);

    assertThat(movieLister.findMoviesByDirector("Cameron")).hasSize(1);
    assertThat(movieLister.findMoviesByDirector("Kubrick")).hasSize(0);
  }

  @Test
  public void whenAutoScanIsUsedWithFilters_shouldUseFileFinder() {
    ApplicationContext context = new AnnotationConfigApplicationContext(
        AppConfigWithComponentScanWithFilters.class);

    SimpleMovieLister movieLister = context.getBean(SimpleMovieLister.class);

    assertThat(movieLister.findMoviesByDirector("Kubrick")).hasSize(2);
    assertThat(movieLister.findMoviesByDirector("Cameron")).hasSize(0);
  }

}
