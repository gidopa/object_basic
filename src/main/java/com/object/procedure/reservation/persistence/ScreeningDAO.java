package com.object.procedure.reservation.persistence;

import com.object.procedure.reservation.domain.Screening;

public interface ScreeningDAO {
  Screening selectScreening(Long screeningId);

  void insert(Screening screening);
}
