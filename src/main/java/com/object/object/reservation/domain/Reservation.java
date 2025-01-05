package com.object.object.reservation.domain;

import com.object.object.generic.Money;
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
