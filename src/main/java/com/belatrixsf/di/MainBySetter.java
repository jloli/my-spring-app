package com.belatrixsf.di;

import com.belatrixsf.beans.InMemoryMovieFinder;
import com.belatrixsf.beans.Movie;
import com.belatrixsf.beans.SimpleMovieListerBySetter;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainBySetter {

  public static void main(String[] args) {

    SimpleMovieListerBySetter movieLister = setupBean();

    List<Movie> movies = movieLister.findMoviesByDirector("Stanley");
    for (Movie m : movies) {
      System.out.printf("%s - %s", m.getTitle(), m.getDirector());
    }

  }


  private static SimpleMovieListerBySetter setupBean() {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        SimpleMovieListerBySetter.class, InMemoryMovieFinder.class);
    return applicationContext.getBean(SimpleMovieListerBySetter.class);
  }

}
