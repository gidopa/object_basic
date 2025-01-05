package com.object.object.reservation.persistence;

import com.object.object.reservation.domain.Screening;

public interface ScreeningDAO {
  Screening selectScreening(Long screeningId);

  void insert(Screening screening);
}
