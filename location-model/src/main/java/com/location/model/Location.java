package com.location.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
@Builder
public class Location {

  public static final double DEFAULT_MARGIN = 5000;
  public static final ZoneId SYSTEM_DEFAULT_ZONE_ID = ZoneId.systemDefault();
  @Setter
  private static double margin = DEFAULT_MARGIN;
  @Setter
  private static ZoneId zoneId = SYSTEM_DEFAULT_ZONE_ID;
  String timestampMs;
  long latitudeE7;
  long longitudeE7;
  long accuracy;
  long altitude;
  long velocity;
  long verticalAccuracy;
  Activity[] activity;

  public LocalDateTime getDateFromTimeStamp() {
    return LocalDateTime
        .ofInstant(Instant.ofEpochMilli(this.getTimestampMs()), zoneId);
  }

  private long getTimestampMs() {
    return timestampMs != null ? Long.parseLong(timestampMs) : 0L;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof Location)) {
      return false;
    }

    Location location = (Location) obj;

    return !(Math.abs(this.longitudeE7 - location.getLongitudeE7()) >= margin) &&
        !(Math.abs(this.latitudeE7 - location.getLatitudeE7()) >= margin);

  }
}
