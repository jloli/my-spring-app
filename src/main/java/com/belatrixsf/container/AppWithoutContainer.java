package com.belatrixsf.container;

import com.belatrixsf.beans.InMemoryMovieFinder;
import com.belatrixsf.beans.Movie;
import com.belatrixsf.beans.SimpleMovieLister;
import java.util.List;

public class AppWithoutContainer {


  public static void main(String[] args) {

    InMemoryMovieFinder movieFinder = new InMemoryMovieFinder();
    SimpleMovieLister movieLister = new SimpleMovieLister(movieFinder);
    List<Movie> movies = movieLister.findMoviesByDirector("Stanley");
    for (Movie m : movies) {
      System.out.printf("%s - %s", m.getTitle(), m.getDirector());
    }

  }


}
