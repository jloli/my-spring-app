package com.belatrixsf.container;

import com.belatrixsf.beans.InMemoryMovieFinder;
import com.belatrixsf.beans.Movie;
import com.belatrixsf.beans.SimpleMovieLister;
import java.util.List;
import org.springframework.context.support.GenericApplicationContext;

public class AppWithContainer {

  public static void main(String[] args) {
    //Container setup
    GenericApplicationContext applicationContext = new GenericApplicationContext();
    applicationContext.registerBean(InMemoryMovieFinder.class);
    applicationContext.registerBean(SimpleMovieLister.class);
    applicationContext.refresh();

    SimpleMovieLister movieLister = applicationContext.getBean(SimpleMovieLister.class);

    List<Movie> movies = movieLister.findMoviesByDirector("Francis");
    for (Movie m : movies) {
      System.out.printf("%s - %s", m.getTitle(), m.getDirector());
    }

  }

}
