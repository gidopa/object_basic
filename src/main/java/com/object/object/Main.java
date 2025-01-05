package com.object.object;

import com.object.object.generic.Money;
import com.object.object.reservation.domain.DiscountCondition;
import com.object.object.reservation.domain.DiscountCondition.ConditionType;
import com.object.object.reservation.domain.DiscountPolicy;
import com.object.object.reservation.domain.DiscountPolicy.PolicyType;
import com.object.object.reservation.domain.Movie;
import com.object.object.reservation.domain.Reservation;
import com.object.object.reservation.domain.Screening;
import com.object.object.reservation.persistence.DiscountConditionDAO;
import com.object.object.reservation.persistence.DiscountPolicyDAO;
import com.object.object.reservation.persistence.MovieDAO;
import com.object.object.reservation.persistence.ReservationDAO;
import com.object.object.reservation.persistence.ScreeningDAO;
import com.object.object.reservation.persistence.memory.DiscountConditionMemoryDAO;
import com.object.object.reservation.persistence.memory.DiscountPolicyMemoryDAO;
import com.object.object.reservation.persistence.memory.MovieMemoryDAO;
import com.object.object.reservation.persistence.memory.ReservationMemoryDAO;
import com.object.object.reservation.persistence.memory.ScreeningMemoryDAO;
import com.object.object.reservation.service.ReservationService;
import com.object.object.generic.TimeInterval;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Main {
  private ScreeningDAO screeningDAO = new ScreeningMemoryDAO();
  private MovieDAO movieDAO = new MovieMemoryDAO();
  private DiscountPolicyDAO discountPolicyDAO = new DiscountPolicyMemoryDAO();
  private DiscountConditionDAO discountConditionDAO = new DiscountConditionMemoryDAO();
  private ReservationDAO reservationDAO = new ReservationMemoryDAO();

  ReservationService reservationService = new ReservationService(
      screeningDAO,
      movieDAO,
      reservationDAO,
      discountConditionDAO,
      discountPolicyDAO);


  private Screening initializeData() {
    Movie movie = new Movie("한산", 150, Money.wons(10000));
    movieDAO.insert(movie);

    DiscountPolicy discountPolicy = new DiscountPolicy(movie.getId(), PolicyType.AMOUNT_POLICY, Money.wons(1000), null);
    discountPolicyDAO.insert(discountPolicy);

    discountConditionDAO.insert(new DiscountCondition(discountPolicy.getId(), ConditionType.PERIOD_CONDITION, null,  null, 1));
    discountConditionDAO.insert(new DiscountCondition(discountPolicy.getId(), ConditionType.SEQUENCE_CONDITION, null,  null, 10));
    discountConditionDAO.insert(new DiscountCondition(discountPolicy.getId(), ConditionType.PERIOD_CONDITION, DayOfWeek.MONDAY, TimeInterval.of(LocalTime.of(10,0), LocalTime.of(12,0)), null));
    discountConditionDAO.insert(new DiscountCondition(discountPolicy.getId(), ConditionType.PERIOD_CONDITION, DayOfWeek.WEDNESDAY,  TimeInterval.of(LocalTime.of(18,0), LocalTime.of(21,0)), null));

    Screening screening = new Screening(movie.getId(), 7, LocalDateTime.of(2024, 12, 11, 18, 0));
    screeningDAO.insert(screening);

    return screening;
  }

  public void run() {
    Screening screening = initializeData();

    Reservation reservation = reservationService.reserveScreening(1L, screening.getId(), 2);

    System.out.printf("관객수 : %d, 요금: %s%n", reservation.getAudienceCount(), reservation.getFee());
  }

  public static void main(String[] args) {
    new Main().run();
  }
}
