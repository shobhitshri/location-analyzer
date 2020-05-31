package com.location.model;

import lombok.Data;

@Data
public class ActivityBlock {

  ActivityState type;
  long confidence;

  public enum ActivityState {
    IN_VEHICLE,
    ON_BICYCLE,
    ON_FOOT,
    STILL,
    UNKNOWN,
    TILTING,
    WALKING,
    RUNNING,
    IN_ROAD_VEHICLE,
    IN_RAIL_VEHICLE,
    IN_FOUR_WHEELER_VEHICLE,
    IN_CAR,
    IN_BUS,
    EXITING_VEHICLE,
    IN_TWO_WHEELER_VEHICLE
  }

  public ActivityBlock(String type, long confidence) {
    this.confidence = confidence;
    try {
      this.type = Enum.valueOf(ActivityState.class, type);
    } catch (IllegalArgumentException ex) {
      this.type = ActivityState.UNKNOWN;
      System.out.println("A new type of activity state is introduced: " + type);
    }
  }
}
