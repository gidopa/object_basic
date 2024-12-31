package com.object.procedure.reservation.persistence;

import com.object.procedure.reservation.domain.Movie;

public interface MovieDAO {
  Movie selectMovie(Long movieId);

  void insert(Movie movie);
}
