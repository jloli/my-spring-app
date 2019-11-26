package com.belatrixsf.beans;

public class Movie {

  private String title;

  private String director;

  public Movie(String title, String director) {
    this.title = title;
    this.director = director;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

}
