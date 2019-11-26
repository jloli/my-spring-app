package com.belatrixsf.di.basic;

import com.belatrixsf.beans.Movie;
import com.belatrixsf.beans.MovieFinder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CompoundMovieLister {

  private final List<MovieFinder> movieFinders;


  public CompoundMovieLister(List<MovieFinder> movieFinders) {
    this.movieFinders = movieFinders;
  }

  public List<Movie> findMoviesByDirector(String director) {
    ArrayList<Movie> collectedMovies = new ArrayList<>();
    for (MovieFinder finder : movieFinders) {
      List<Movie> movies = finder.findAll().stream()
          .filter(movie -> movie.getDirector().contains(director))
          .collect(Collectors.toList());
      collectedMovies.addAll(movies);
    }
    return collectedMovies;
  }

}
