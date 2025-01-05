package com.object.object.reservation.persistence.memory;

import com.object.object.reservation.domain.DiscountCondition;
import com.object.object.reservation.persistence.DiscountConditionDAO;
import java.util.List;

public class DiscountConditionMemoryDAO extends InMemoryDAO<DiscountCondition> implements
    DiscountConditionDAO {

  @Override
  public List<DiscountCondition> selectDiscountConditions(Long policyId) {
    return findMany(cond -> cond.getPolicyId().equals(policyId));
  }

}
