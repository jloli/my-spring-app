package com.belatrixsf.container;

import com.belatrixsf.beans.Movie;
import com.belatrixsf.beans.SimpleMovieLister;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.belatrixsf.beans"})
public class AppWithAutomaticScan implements CommandLineRunner {

  @Autowired
  private SimpleMovieLister movieLister;


  @Override
  public void run(String... args) throws Exception {
    List<Movie> movies = movieLister.findMoviesByDirector("Murnau");
    for (Movie m : movies) {
      System.out.printf("%s - %s", m.getTitle(), m.getDirector());
    }
  }

  public static void main(String[] args) {
    SpringApplication.run(AppWithAutomaticScan.class);
  }

}
