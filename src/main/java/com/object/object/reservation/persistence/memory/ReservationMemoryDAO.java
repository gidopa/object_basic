package com.object.object.reservation.persistence.memory;

import com.object.object.reservation.domain.Reservation;
import com.object.object.reservation.persistence.ReservationDAO;

public class ReservationMemoryDAO extends InMemoryDAO<Reservation> implements ReservationDAO {

  @Override
  public void insert(Reservation reservation) {
    super.insert(reservation);
  }
}
