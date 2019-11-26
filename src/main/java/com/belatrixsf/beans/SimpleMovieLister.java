package com.belatrixsf.beans;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class SimpleMovieLister {

  private final MovieFinder movieFinder;


  public SimpleMovieLister(MovieFinder movieFinder) {
    this.movieFinder = movieFinder;
  }


  public List<Movie> findMoviesByDirector(String director) {
    return movieFinder.findAll().stream()
        .filter(movie -> movie.getDirector().contains(director))
        .collect(Collectors.toList());
  }

}
