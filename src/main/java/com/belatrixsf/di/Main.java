package com.belatrixsf.di;

import com.belatrixsf.beans.InMemoryMovieFinder;
import com.belatrixsf.beans.Movie;
import com.belatrixsf.beans.SimpleMovieLister;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {

    SimpleMovieLister movieLister = setupBean();

    List<Movie> movies = movieLister.findMoviesByDirector("Stanley");
    for (Movie m : movies) {
      System.out.printf("%s - %s", m.getTitle(), m.getDirector());
    }

  }


  private static SimpleMovieLister setupBean() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        SimpleMovieLister.class, InMemoryMovieFinder.class);
    return applicationContext.getBean(SimpleMovieLister.class);
  }

}
