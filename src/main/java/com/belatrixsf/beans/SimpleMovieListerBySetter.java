package com.belatrixsf.beans;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleMovieListerBySetter {

  private MovieFinder movieFinder;


  public List<Movie> findMoviesByDirector(String director) {
    return movieFinder.findAll().stream()
        .filter(movie -> movie.getDirector().contains(director))
        .collect(Collectors.toList());
  }


  @Autowired
  public void setMovieFinder(MovieFinder movieFinder) {
    this.movieFinder = movieFinder;
  }

}
