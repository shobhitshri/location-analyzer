package com.location.model;

import org.junit.Assert;
import org.junit.Test;

public class LocationTest {

  @Test
  public void testHomeLocation() {
    long longitude = 12979307;
    long latitude = 77711321;
    Location homeLocation = Location.builder().latitudeE7(latitude).longitudeE7(longitude).build();
    double margin = 1.0;
    Location.setMargin(margin);
    Assert.assertEquals(homeLocation, homeLocation);
  }

  @Test
  public void testNearLocation() {
    long longitude = 12979307;
    long latitude = 77711321;
    Location homeLocation = Location.builder().latitudeE7(latitude).longitudeE7(longitude).build();
    long nearLongitude = 12983275;
    long nearLatitude = 77708095;
    Location nearLocation = Location.builder().latitudeE7(nearLatitude).longitudeE7(nearLongitude)
        .build();
    double margin = 1.0;
    Location.setMargin(margin);
    Assert.assertNotEquals(homeLocation, nearLocation);
  }

  @Test
  public void testNearLocation2() {
    long longitude = 12979307;
    long latitude = 77711321;
    Location homeLocation = Location.builder().latitudeE7(latitude).longitudeE7(longitude).build();
    long nearLongitude = 12982127;
    long nearLatitude = 77710015;
    Location nearLocation = Location.builder().latitudeE7(nearLatitude).longitudeE7(nearLongitude)
        .build();
    double margin = 1.0;
    Location.setMargin(margin);
    Assert.assertNotEquals(homeLocation, nearLocation);
  }

  @Test
  public void testNearLocation3() {
    long longitude = 12982094;
    long latitude = 77710625;
    Location homeLocation = Location.builder().latitudeE7(latitude).longitudeE7(longitude).build();
    long nearLongitude = 12982479;
    long nearLatitude = 77710798;
    Location nearLocation = Location.builder().latitudeE7(nearLatitude).longitudeE7(nearLongitude)
        .build();
    double margin = 1000.0;
    Location.setMargin(margin);
    Assert.assertEquals(homeLocation, nearLocation);
  }

  @Test
  public void testNearLocation4() {
    long longitude = 12982094;
    long latitude = 77710625;
    Location homeLocation = Location.builder().latitudeE7(latitude).longitudeE7(longitude).build();
    long nearLongitude = 12974563;
    long nearLatitude = 77626211;
    Location nearLocation = Location.builder().latitudeE7(nearLatitude).longitudeE7(nearLongitude)
        .build();
    double margin = 1000.0;
    Location.setMargin(margin);
    Assert.assertNotEquals(homeLocation, nearLocation);
  }
}
