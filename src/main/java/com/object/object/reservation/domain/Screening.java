package com.object.object.reservation.domain;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Screening {

  private Long id;
  private Long movieId;
  private Integer sequence;
  private LocalDateTime screeningTime;

  public Screening(Long movieId, Integer sequence, LocalDateTime screeningTime) {
    this(null, movieId, sequence, screeningTime);
  }

  public boolean isPlayedIn(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
    return this.screeningTime.getDayOfWeek().equals(dayOfWeek) &&
        (this.screeningTime.toLocalTime().equals(startTime) || this.screeningTime.toLocalTime().isAfter(startTime)) &&
        (this.screeningTime.toLocalTime().equals(endTime) || this.screeningTime.toLocalTime().isBefore(endTime));
  }
}
