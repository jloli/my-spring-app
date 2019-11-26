package com.belatrixsf.beans;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class InMemoryMovieFinder implements MovieFinder {

  private ArrayList<Movie> movies = new ArrayList<>();

  public InMemoryMovieFinder() {
    movies.add(new Movie("The Shining", "Stanley Kubrick"));
    movies.add(new Movie("The Godfather", "Francis Ford Coppola"));
    movies.add(new Movie("Nosferatu", "Friedrich Wilhelm Murnau"));
  }

  @Override
  public List<Movie> findAll() {
    return movies;
  }

}
