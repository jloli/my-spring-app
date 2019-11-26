package com.belatrixsf.beans;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class NonRepeatedInMemoryMovieFinder implements MovieFinder {

  private HashSet<Movie> movies = new HashSet<>();

  public NonRepeatedInMemoryMovieFinder() {
    movies.add(new Movie("Ghostbusters", "Ivan Reitman"));
    movies.add(new Movie("Avatar", "James Cameron"));
  }

  @Override
  public List<Movie> findAll() {
    return movies.stream().collect(Collectors.toList());
  }

}
