package com.object.procedure.reservation.persistence;

import com.object.procedure.reservation.domain.DiscountPolicy;

public interface DiscountPolicyDAO {
  DiscountPolicy selectDiscountPolicy(Long movieId);

  void insert(DiscountPolicy discountPolicy);

}
