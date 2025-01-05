package com.object.object.reservation.persistence.memory;

import com.object.object.reservation.domain.Screening;
import com.object.object.reservation.persistence.ScreeningDAO;

public class ScreeningMemoryDAO extends InMemoryDAO<Screening> implements ScreeningDAO {

  @Override
  public Screening selectScreening(Long id) {
    return findOne(screening -> screening.getId().equals(id));
  }

}
