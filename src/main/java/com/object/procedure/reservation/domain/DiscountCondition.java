package com.object.procedure.reservation.domain;

import com.object.procedure.generic.interval;
import java.time.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCondition {

  // 조합 조건 등의 데이터의 변경이 생기면 프로세스 (ReservationService)에서의 코드 변경도 같이 일어남
  public enum ConditionType {PERIOD_CONDITION, SEQUENCE_CONDITION, COMBINED_CONDITION}

  private Long id;
  private Long policyId;
  private ConditionType conditionType;
  private DayOfWeek dayOfWeek;
  // 시간 범위라는 하나의 개념을 startTime, endTime 두 개의 속성으로 표현
  //  private LocalTime startTime;
  //  private LocalTime endTime;
  private interval interval;
  private Integer sequence;

  public DiscountCondition(Long policyId, ConditionType conditionType, DayOfWeek dayOfWeek,
      interval interval, Integer sequence) {
    this(null, policyId, conditionType, dayOfWeek, interval, sequence);
  }

  public boolean isPeriodCondition() {
    return ConditionType.PERIOD_CONDITION.equals(conditionType);
  }

  public boolean isSequenceCondition() {
    return ConditionType.SEQUENCE_CONDITION.equals(conditionType);
  }

  public boolean isCombinedCondition() {
    return ConditionType.COMBINED_CONDITION.equals(conditionType);
  }
}
