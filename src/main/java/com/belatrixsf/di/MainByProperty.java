package com.belatrixsf.di;

import com.belatrixsf.beans.InMemoryMovieFinder;
import com.belatrixsf.beans.Movie;
import com.belatrixsf.beans.SimpleMovieListerByProperty;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainByProperty {

  public static void main(String[] args) {

    SimpleMovieListerByProperty movieLister = setupBean();

    List<Movie> movies = movieLister.findMoviesByDirector("Stanley");
    for (Movie m : movies) {
      System.out.printf("%s - %s", m.getTitle(), m.getDirector());
    }

  }


  private static SimpleMovieListerByProperty setupBean() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        SimpleMovieListerByProperty.class, InMemoryMovieFinder.class);
    return applicationContext.getBean(SimpleMovieListerByProperty.class);
  }

}
