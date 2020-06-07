package com.location.model;

import org.junit.Assert;
import org.junit.Test;

public class CommuteTest {

  @Test
  public void getTravelTime() {
    Location homeStart = Location.builder().timestampMs(String.valueOf(1546314000000L)).build();
    Location workReach = Location.builder().timestampMs(String.valueOf(1546318200000L)).build();
    Commute commuteHome = new Commute(homeStart, workReach, true);
    double duration = commuteHome.getCommuteDuration();
    Assert.assertEquals(1.25, duration, 0.01);
  }
}