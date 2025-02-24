package com.object.object.reservation.persistence;

import com.object.object.reservation.domain.Movie;

public interface MovieDAO {
  Movie selectMovie(Long movieId);

  void insert(Movie movie);
}
