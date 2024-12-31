package com.object.procedure;

import com.object.procedure.generic.Money;
import com.object.procedure.reservation.domain.DiscountCondition;
import com.object.procedure.reservation.domain.DiscountCondition.ConditionType;
import com.object.procedure.reservation.domain.DiscountPolicy;
import com.object.procedure.reservation.domain.DiscountPolicy.PolicyType;
import com.object.procedure.reservation.domain.Movie;
import com.object.procedure.reservation.domain.Reservation;
import com.object.procedure.reservation.domain.Screening;
import com.object.procedure.reservation.persistence.DiscountConditionDAO;
import com.object.procedure.reservation.persistence.DiscountPolicyDAO;
import com.object.procedure.reservation.persistence.MovieDAO;
import com.object.procedure.reservation.persistence.ReservationDAO;
import com.object.procedure.reservation.persistence.ScreeningDAO;
import com.object.procedure.reservation.persistence.memory.DiscountConditionMemoryDAO;
import com.object.procedure.reservation.persistence.memory.DiscountPolicyMemoryDAO;
import com.object.procedure.reservation.persistence.memory.MovieMemoryDAO;
import com.object.procedure.reservation.persistence.memory.ReservationMemoryDAO;
import com.object.procedure.reservation.persistence.memory.ScreeningMemoryDAO;
import com.object.procedure.reservation.service.ReservationService;
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

    discountConditionDAO.insert(new DiscountCondition(discountPolicy.getId(), ConditionType.PERIOD_CONDITION, null, null, null, 1));
    discountConditionDAO.insert(new DiscountCondition(discountPolicy.getId(), ConditionType.SEQUENCE_CONDITION, null, null, null, 10));
    discountConditionDAO.insert(new DiscountCondition(discountPolicy.getId(), ConditionType.PERIOD_CONDITION, DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(12, 0), null));
    discountConditionDAO.insert(new DiscountCondition(discountPolicy.getId(), ConditionType.PERIOD_CONDITION, DayOfWeek.WEDNESDAY, LocalTime.of(18, 0), LocalTime.of(21, 0), null));

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
