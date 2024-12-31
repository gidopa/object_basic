package com.object.procedure.reservation.domain;

import com.object.procedure.generic.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Reservation {

  private Long id;
  private Long customerId;
  private Long screeningId;
  private Integer audienceCount;
  private Money fee;

  public Reservation(Long customerId, Long screeningId, Integer audienceCount, Money fee) {
    this(null, customerId, screeningId, audienceCount, fee);
  }
}
