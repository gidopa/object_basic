package com.object.procedure.reservation.persistence;

import com.object.procedure.reservation.domain.DiscountCondition;
import java.util.List;

public interface DiscountConditionDAO {
  List<DiscountCondition> selectDiscountConditions(Long policyId);

  void insert(DiscountCondition discountCondition);
}
