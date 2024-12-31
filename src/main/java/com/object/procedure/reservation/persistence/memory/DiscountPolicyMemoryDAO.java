package com.object.procedure.reservation.persistence.memory;

import com.object.procedure.reservation.domain.DiscountPolicy;
import com.object.procedure.reservation.persistence.DiscountPolicyDAO;

public class DiscountPolicyMemoryDAO extends InMemoryDAO<DiscountPolicy> implements
    DiscountPolicyDAO {

  @Override
  public DiscountPolicy selectDiscountPolicy(Long movieId) {
    return findOne(policy -> policy.getMovieId().equals(movieId));
  }

}
