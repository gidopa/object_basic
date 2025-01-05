package com.object.object.reservation.persistence.memory;

import com.object.object.reservation.domain.Movie;
import com.object.object.reservation.persistence.MovieDAO;

public class MovieMemoryDAO extends InMemoryDAO<Movie> implements MovieDAO {

  @Override
  public Movie selectMovie(Long movieId) {
    return findOne(movie -> movie.getId().equals(movieId));
  }
}
