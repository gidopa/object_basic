package com.object.procedure.reservation.service;

import com.object.procedure.generic.Money;
import com.object.procedure.reservation.domain.DiscountCondition;
import com.object.procedure.reservation.domain.DiscountPolicy;
import com.object.procedure.reservation.domain.Movie;
import com.object.procedure.reservation.domain.Reservation;
import com.object.procedure.reservation.domain.Screening;
import com.object.procedure.reservation.persistence.DiscountConditionDAO;
import com.object.procedure.reservation.persistence.DiscountPolicyDAO;
import com.object.procedure.reservation.persistence.MovieDAO;
import com.object.procedure.reservation.persistence.ReservationDAO;
import com.object.procedure.reservation.persistence.ScreeningDAO;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReservationService {
  private ScreeningDAO screeningDAO;
  private MovieDAO movieDAO;
  private ReservationDAO reservationDAO;
  private DiscountConditionDAO discountConditionDAO;
  private DiscountPolicyDAO discountPolicyDAO;

  public Reservation reserveScreening(Long customerId, Long screeningId, Integer audienceCount) {
    Screening screening = screeningDAO.selectScreening(screeningId);
    Movie movie = movieDAO.selectMovie(screening.getMovieId());
    DiscountPolicy policy = discountPolicyDAO.selectDiscountPolicy(movie.getId());
    List<DiscountCondition> conditions = discountConditionDAO.selectDiscountConditions(policy.getId());

    DiscountCondition condition = findDiscountCondition(screening, conditions);

    Money fee;
    if (condition != null) {
      fee = movie.getFee().minus(calculateDiscount(policy, movie));
    } else {
      fee = movie.getFee();
    }

    Reservation reservation = makeReservation(customerId, screeningId, audienceCount, fee);
    reservationDAO.insert(reservation);

    return reservation;
  }

  private DiscountCondition findDiscountCondition(Screening screening, List<DiscountCondition> conditions) {
    for(DiscountCondition condition : conditions) {
      if (condition.isPeriodCondition()) {
        if (screening.isPlayedIn(condition.getDayOfWeek(),
            condition.getinterval().getStartTime(),
            condition.getinterval().getEndTime())) {
          return condition;
        }
      } else if (condition.isSequenceCondition()){
        if (condition.getSequence().equals(screening.getSequence())) {
          return condition;
        }
      } else if (condition.isCombinedCondition()) {
        if((condition.getSequence().equals(screening.getSequence())) &&
            (screening.isPlayedIn(condition.getDayOfWeek(), condition.getinterval().getStartTime(), condition.getinterval().getEndTime()))) {
          return condition;
        }
      }
    }

    return null;
  }

  private Money calculateDiscount(DiscountPolicy policy, Movie movie) {
    if (policy.isAmountPolicy()) {
      return policy.getAmount();
    } else if (policy.isPercentPolicy()) {
      return movie.getFee().times(policy.getPercent());
    }

    return Money.ZERO;
  }

  private Reservation makeReservation(Long customerId, Long screeningId, Integer audienceCount, Money fee) {
    return new Reservation(customerId, screeningId, audienceCount, fee.times(audienceCount));
  }
}
