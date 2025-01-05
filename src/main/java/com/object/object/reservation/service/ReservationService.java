package com.object.object.reservation.service;

import com.object.object.generic.Money;
import com.object.object.reservation.domain.DiscountCondition;
import com.object.object.reservation.domain.DiscountPolicy;
import com.object.object.reservation.domain.Movie;
import com.object.object.reservation.domain.Reservation;
import com.object.object.reservation.domain.Screening;
import com.object.object.reservation.persistence.DiscountConditionDAO;
import com.object.object.reservation.persistence.DiscountPolicyDAO;
import com.object.object.reservation.persistence.MovieDAO;
import com.object.object.reservation.persistence.ReservationDAO;
import com.object.object.reservation.persistence.ScreeningDAO;
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
    boolean found = policy.findDiscountCondition(screening);

    Money fee;
    if (found) {
      fee = movie.getFee().minus(policy.calculateDiscount(policy, movie));
    } else {
      fee = movie.getFee();
    }

    Reservation reservation = makeReservation(customerId, screeningId, audienceCount, fee);
    reservationDAO.insert(reservation);

    return reservation;
  }

  private DiscountCondition findDiscountCondition(Screening screening, List<DiscountCondition> conditions) {
    for(DiscountCondition condition : conditions) {

    }

    return null;
  }



  private Reservation makeReservation(Long customerId, Long screeningId, Integer audienceCount, Money fee) {
    return new Reservation(customerId, screeningId, audienceCount, fee.times(audienceCount));
  }
}
