package com.object.object.reservation.persistence;

import com.object.object.reservation.domain.Reservation;

public interface ReservationDAO {
  void insert(Reservation reservation);
}
