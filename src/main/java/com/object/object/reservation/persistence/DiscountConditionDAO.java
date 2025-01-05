package com.object.object.reservation.persistence;

import com.object.object.reservation.domain.DiscountCondition;
import java.util.List;

public interface DiscountConditionDAO {
  List<DiscountCondition> selectDiscountConditions(Long policyId);

  void insert(DiscountCondition discountCondition);
}
