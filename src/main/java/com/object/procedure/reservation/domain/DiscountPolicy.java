package com.object.procedure.reservation.domain;

import com.object.procedure.generic.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountPolicy {
  public enum PolicyType {PERCENT_POLICY, AMOUNT_POLICY}

  private Long id;
  private Long movieId;
  private PolicyType policyType;
  private Money amount;
  private Double percent;

  public DiscountPolicy(Long movieId, PolicyType policyType, Money amount, Double percent) {
   this(null, movieId, policyType, amount, percent);
  }

  public boolean isAmountPolicy() {
    return PolicyType.AMOUNT_POLICY.equals(policyType);
  }

  public boolean isPercentPolicy() {
    return PolicyType.PERCENT_POLICY.equals(policyType);
  }
}
