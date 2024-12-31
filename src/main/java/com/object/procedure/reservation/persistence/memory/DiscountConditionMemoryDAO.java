package com.object.procedure.reservation.persistence.memory;

import com.object.procedure.reservation.domain.DiscountCondition;
import com.object.procedure.reservation.persistence.DiscountConditionDAO;
import java.util.List;

public class DiscountConditionMemoryDAO extends InMemoryDAO<DiscountCondition> implements
    DiscountConditionDAO {

  @Override
  public List<DiscountCondition> selectDiscountConditions(Long policyId) {
    return findMany(cond -> cond.getPolicyId().equals(policyId));
  }

}
