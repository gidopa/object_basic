package com.object.procedure.reservation.persistence.memory;

import com.object.procedure.reservation.domain.Screening;
import com.object.procedure.reservation.persistence.ScreeningDAO;

public class ScreeningMemoryDAO extends InMemoryDAO<Screening> implements ScreeningDAO {

  @Override
  public Screening selectScreening(Long id) {
    return findOne(screening -> screening.getId().equals(id));
  }

}
