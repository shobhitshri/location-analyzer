package com.location.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Commute {
  private static final double BLOCK_SIZE = 15;
  Location home;
  Location work;
  boolean isFromHomeToWork;

  public static double getWorkDuration(final Commute toWork, final Commute fromWork) {
    LocalDateTime reachingWork = toWork.getWork().getDateFromTimeStamp();
    LocalDateTime leavingWork = fromWork.getWork().getDateFromTimeStamp();
    return getTimeDurationBetween(reachingWork, leavingWork);
  }

  private static double getTimeDurationBetween(final LocalDateTime dateHome,
      final LocalDateTime dateWork) {
    double hours = ChronoUnit.HOURS.between(dateHome, dateWork);
    double minutes = ChronoUnit.MINUTES.between(dateHome, dateWork);
    return getTotalTimeInDecimal(hours, minutes);
  }

  static double getTotalTimeInDecimal(double hours, double minutes) {
    double minuteBlock = roundUp(minutes, BLOCK_SIZE);
    return hours + (minuteBlock % 60) / 60;
  }

  static double roundUp(double toRound, double blockSize) {
    double multiple = blockSize;
    double rem = toRound % multiple;
    double result = toRound - rem;
    if (rem > (multiple / 2)) {
      result += multiple;
    }
    return result;
  }

  public LocalDateTime getCommuteStartTime() {
    return isFromHomeToWork()
        ? home.getDateFromTimeStamp()
        : work.getDateFromTimeStamp();
  }

  public void resetLocations() {
    home = null;
    work = null;
  }

  public double getCommuteDuration() {
    LocalDateTime dateHome = home.getDateFromTimeStamp();
    LocalDateTime dateWork = work.getDateFromTimeStamp();
    return isFromHomeToWork()
        ? getTimeDurationBetween(dateHome, dateWork)
        : getTimeDurationBetween(dateWork, dateHome);
  }

}
