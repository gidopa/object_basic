package com.object.object.reservation.domain;

import com.object.object.generic.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
  private Long id;
  private String title;
  private Integer runningTime;
  private Money fee;

  public Movie() {
  }

  public Movie(String title, Integer runningTime, Money fee) {
    this(null, title, runningTime, fee);
  }

  public Movie(Long id, String title, Integer runningTime, Money fee) {
    this.id = id;
    this.title = title;
    this.runningTime = runningTime;
    this.fee = fee;
  }
}
