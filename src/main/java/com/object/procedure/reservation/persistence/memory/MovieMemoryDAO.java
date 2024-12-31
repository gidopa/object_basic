package com.object.procedure.reservation.persistence.memory;

import com.object.procedure.reservation.domain.Movie;
import com.object.procedure.reservation.persistence.MovieDAO;

public class MovieMemoryDAO extends InMemoryDAO<Movie> implements MovieDAO {

  @Override
  public Movie selectMovie(Long movieId) {
    return findOne(movie -> movie.getId().equals(movieId));
  }
}
