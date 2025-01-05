package com.object.object.reservation.service;

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
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {
  @InjectMocks
  private ReservationService reservationService;

  @Mock
  private ScreeningDAO screeningDAO;
  @Mock private MovieDAO movieDAO;
  @Mock private DiscountPolicyDAO discountPolicyDAO;
  @Mock private DiscountConditionDAO discountConditionDAO;
  @Mock private ReservationDAO reservationDAO;

  @Test
  public void 금액할인정책_계산() {
    // given
    Long customerId = 1L;
    Long screeningId = 1L;
    Long movieId = 1L;
    Long policyId = 1L;

    Mockito.when(screeningDAO.selectScreening(screeningId))
        .thenReturn(new Screening(screeningId, movieId, 1, LocalDateTime.of(2024, 12, 11, 18, 0)));

    Mockito.when(movieDAO.selectMovie(movieId))
        .thenReturn(new Movie(movieId, "한신", 120, Money.wons(10000)));

    Mockito.when(discountPolicyDAO.selectDiscountPolicy(movieId))
        .thenReturn(new DiscountPolicy(policyId, movieId, PolicyType.AMOUNT_POLICY, Money.wons(1000), null));

    Mockito.when(discountConditionDAO.selectDiscountConditions(policyId))
        .thenReturn(List.of(
            new DiscountCondition(1L, policyId, ConditionType.SEQUENCE_CONDITION, null, null, null, 1),
            new DiscountCondition(2L, policyId, ConditionType.SEQUENCE_CONDITION, null, null, null, 10),
            new DiscountCondition(3L, policyId, ConditionType.PERIOD_CONDITION, DayOfWeek.MONDAY, LocalTime.of(10, 12), LocalTime.of(12, 0), null),
            new DiscountCondition(4L, policyId, ConditionType.PERIOD_CONDITION, DayOfWeek.WEDNESDAY, LocalTime.of(18, 0), LocalTime.of(21, 0), null)));

    // when
    Reservation reservation = reservationService.reserveScreening(customerId, screeningId, 2);

    // then
    Assertions.assertEquals(reservation.getFee(), Money.wons(18000));
  }

  @Test
  public void 비율할인정책_계산() {
    // given
    Long customerId = 1L;
    Long screeningId = 1L;
    Long movieId = 1L;
    Long policyId = 1L;

    Mockito.when(screeningDAO.selectScreening(screeningId))
        .thenReturn(new Screening(screeningId, movieId, 1, LocalDateTime.of(2024, 12, 11, 18, 0)));

    Mockito.when(movieDAO.selectMovie(movieId))
        .thenReturn(new Movie(movieId, "한신", 120, Money.wons(10000)));

    Mockito.when(discountPolicyDAO.selectDiscountPolicy(movieId))
        .thenReturn(new DiscountPolicy(policyId, movieId, PolicyType.PERCENT_POLICY, null, 0.1));

    Mockito.when(discountConditionDAO.selectDiscountConditions(policyId))
        .thenReturn(List.of(
            new DiscountCondition(1L, policyId, ConditionType.SEQUENCE_CONDITION, null, null, null, 1),
            new DiscountCondition(2L, policyId, ConditionType.SEQUENCE_CONDITION, null, null, null, 10),
            new DiscountCondition(3L, policyId, ConditionType.PERIOD_CONDITION, DayOfWeek.MONDAY, LocalTime.of(10, 12), LocalTime.of(12, 0), null),
            new DiscountCondition(4L, policyId, ConditionType.PERIOD_CONDITION, DayOfWeek.WEDNESDAY, LocalTime.of(18, 0), LocalTime.of(21, 0), null)));

    // when
    Reservation reservation = reservationService.reserveScreening(customerId, screeningId, 2);

    // then
    Assertions.assertEquals(reservation.getFee(), Money.wons(18000));
  }
}