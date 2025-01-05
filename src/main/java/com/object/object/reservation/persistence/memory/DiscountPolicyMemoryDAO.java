package com.object.object.reservation.persistence.memory;

import com.object.object.reservation.domain.DiscountPolicy;
import com.object.object.reservation.persistence.DiscountPolicyDAO;

public class DiscountPolicyMemoryDAO extends InMemoryDAO<DiscountPolicy> implements
    DiscountPolicyDAO {

  @Override
  public DiscountPolicy selectDiscountPolicy(Long movieId) {
    return findOne(policy -> policy.getMovieId().equals(movieId));
  }

}
