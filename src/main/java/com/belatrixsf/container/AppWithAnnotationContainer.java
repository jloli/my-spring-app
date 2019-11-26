package com.belatrixsf.container;

import com.belatrixsf.beans.InMemoryMovieFinder;
import com.belatrixsf.beans.Movie;
import com.belatrixsf.beans.SimpleMovieLister;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppWithAnnotationContainer {

  public static void main(String[] args) {
    //Container setup
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        SimpleMovieLister.class, InMemoryMovieFinder.class);

    SimpleMovieLister movieLister = applicationContext.getBean(SimpleMovieLister.class);

    List<Movie> movies = movieLister.findMoviesByDirector("Francis");
    for (Movie m : movies) {
      System.out.printf("%s - %s", m.getTitle(), m.getDirector());
    }

  }

}
