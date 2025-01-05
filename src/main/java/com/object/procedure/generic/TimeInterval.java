package com.object.procedure.generic;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TimeInterval {

  private LocalTime startTime;
  private LocalTime endTime;

  public static TimeInterval of(LocalTime startTime, LocalTime endTime) {
    return new TimeInterval(startTime, endTime);
  }


}
