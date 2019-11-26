package com.belatrixsf.di.basic;

import com.belatrixsf.beans.Movie;
import com.belatrixsf.beans.MovieFinder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fileMovieFinder")
public class FileMovieFinder implements MovieFinder {

  private final ResourceLoader resourceLoader;

  private ArrayList<Movie> cachedMovies = new ArrayList<>();


  public FileMovieFinder(ResourceLoader resourceLoader) throws Exception {
    this.resourceLoader = resourceLoader;
  }


  @Override
  public List<Movie> findAll() {
    return cachedMovies;
  }


  @PostConstruct
  private void loadMoviesFromFile() throws Exception {
    Resource resource = resourceLoader.getResource("classpath:movies.csv");
    try (Scanner scanner = new Scanner(resource.getInputStream());) {
      while (scanner.hasNextLine()) {
        cachedMovies.add(createMovie(scanner.nextLine()));
      }
    }
  }

  private Movie createMovie(String line) {
    String[] text = line.split(",");
    return new Movie(text[0], text[1]);
  }

}
