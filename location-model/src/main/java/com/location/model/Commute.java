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

  public void resetLocations() {
    home = null;
    work = null;
  }

  public long getCommuteDuration() {
    LocalDateTime dateHome = home.getDateFromTimeStamp();
    LocalDateTime dateWork = work.getDateFromTimeStamp();
    return isFromHomeToWork()
        ? ChronoUnit.MINUTES.between(dateHome, dateWork)
        : ChronoUnit.MINUTES.between(dateWork, dateHome);
  }

  public LocalDateTime getCommuteStartTime() {
    return isFromHomeToWork()
        ? home.getDateFromTimeStamp()
        : work.getDateFromTimeStamp();
  }
}
