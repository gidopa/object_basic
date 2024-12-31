package com.object.procedure.reservation.persistence.memory;

import com.object.procedure.reservation.domain.Reservation;
import com.object.procedure.reservation.persistence.ReservationDAO;

public class ReservationMemoryDAO extends InMemoryDAO<Reservation> implements ReservationDAO {

  @Override
  public void insert(Reservation reservation) {
    super.insert(reservation);
  }
}
