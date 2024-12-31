package com.object.procedure.reservation.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCondition {
  public enum ConditionType {PERIOD_CONDITION, SEQUENCE_CONDITION}

  private Long id;
  private Long policyId;
  private ConditionType conditionType;
  private DayOfWeek dayOfWeek;
  private LocalTime startTime;
  private LocalTime endTime;
  private Integer sequence;

  public DiscountCondition(Long policyId, ConditionType conditionType, DayOfWeek dayOfWeek,
      LocalTime startTime, LocalTime endTime, Integer sequence) {
    this(null, policyId, conditionType, dayOfWeek, startTime, endTime, sequence);
  }

  public boolean isPeriodCondition() {
    return ConditionType.PERIOD_CONDITION.equals(conditionType);
  }

  public boolean isSequenceCondition() {
    return ConditionType.SEQUENCE_CONDITION.equals(conditionType);
  }
}
