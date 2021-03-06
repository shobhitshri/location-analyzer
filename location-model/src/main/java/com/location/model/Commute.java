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

  Location home;
  Location work;
  boolean isFromHomeToWork;

  public static double getWorkDuration(final Commute toWork, final Commute fromWork) {
    LocalDateTime reachingWork = toWork.getWork().getDateFromTimeStamp();
    LocalDateTime leavingWork = fromWork.getWork().getDateFromTimeStamp();
    return getTimeDuration(reachingWork, leavingWork);
  }

  private static double getTimeDuration(final LocalDateTime dateHome,
      final LocalDateTime dateWork) {
    double hours = ChronoUnit.HOURS.between(dateHome, dateWork);
    double minutes = ChronoUnit.MINUTES.between(dateHome, dateWork);
    return getTotalTimeInDecimal(hours, minutes);
  }

  static double getTotalTimeInDecimal(double hours, double minutes) {
    return hours + (minutes % 60) / 60;
  }

  public double getCommuteDuration() {
    LocalDateTime dateHome = home.getDateFromTimeStamp();
    LocalDateTime dateWork = work.getDateFromTimeStamp();
    return isFromHomeToWork()
        ? getTimeDuration(dateHome, dateWork)
        : getTimeDuration(dateWork, dateHome);
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

}
