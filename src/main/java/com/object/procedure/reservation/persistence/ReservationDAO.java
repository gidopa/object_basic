package com.object.procedure.reservation.persistence;

import com.object.procedure.reservation.domain.Reservation;

public interface ReservationDAO {
  void insert(Reservation reservation);
}
