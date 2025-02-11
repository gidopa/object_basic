package com.object.object.reservation.persistence;

import com.object.object.reservation.domain.DiscountPolicy;

public interface DiscountPolicyDAO {
  DiscountPolicy selectDiscountPolicy(Long movieId);

  void insert(DiscountPolicy discountPolicy);

}
