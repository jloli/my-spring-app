package com.belatrixsf.di.basic;

import com.belatrixsf.beans.Movie;
import com.belatrixsf.beans.MovieFinder;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SimpleQualifiedMovieLister {

  private final MovieFinder movieFinder;


  public SimpleQualifiedMovieLister(@Qualifier("fileMovieFinder") MovieFinder movieFinder) {
    this.movieFinder = movieFinder;
  }


  public List<Movie> findMoviesByDirector(String director) {
    return movieFinder.findAll().stream()
        .filter(movie -> movie.getDirector().contains(director))
        .collect(Collectors.toList());
  }

}
