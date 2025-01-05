package com.object.object.reservation.domain;

import com.object.object.generic.Money;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountPolicy {

  public enum PolicyType {PERCENT_POLICY, AMOUNT_POLICY}

  private Long id;
  private Long movieId;
  private PolicyType policyType;
  private Money amount;
  private Double percent;
  private List<DiscountCondition> conditions;

  public DiscountPolicy(Long movieId, PolicyType policyType, Money amount, Double percent) {
    this(null, movieId, policyType, amount, percent, new ArrayList<>());
  }

  public boolean findDiscountCondition(Screening screening) {
    for (DiscountCondition condition : conditions) {
      if (condition.isSatisfiedBy(screening)) {
        return true;
      }
    }
    return false;
  }

  private boolean isAmountPolicy() {
    return PolicyType.AMOUNT_POLICY.equals(policyType);
  }

  private boolean isPercentPolicy() {
    return PolicyType.PERCENT_POLICY.equals(policyType);
  }

  public Money calculateDiscount(Movie movie) {
    if (isAmountPolicy()) {
      return amount;
    } else if (isPercentPolicy()) {
      return movie.getFee().times(percent);
    }
    return Money.ZERO;
  }
}
