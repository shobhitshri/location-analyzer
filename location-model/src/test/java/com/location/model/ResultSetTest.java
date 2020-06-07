package com.location.model;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

//TODO: FIx test names
public class ResultSetTest {

  @Test
  public void getHeaders() {
    String[] headers = ResultSet.getHeaders();
    assertEquals(12, headers.length);
  }

  @Test
  public void findStartTimeBlockUpperCeil() {
    int hour = 9;
    int minute = 14;
    String block = ResultSet.findStartTimeBlock(hour, minute);
    Assert.assertEquals("9.25", block);
  }

  @Test
  public void findStartTimeBlockLowerCeil() {
    int hour = 9;
    int minute = 17;
    String block = ResultSet.findStartTimeBlock(hour, minute);
    Assert.assertEquals("9.25", block);
  }

  @Test
  public void findStartTimeBlockAtBoundary() {
    int hour = 9;
    int minute = 20;
    String block = ResultSet.findStartTimeBlock(hour, minute);
    Assert.assertEquals("9.25", block);
  }

  @Test
  public void findStartTimeBlockAtBoundary1() {
    int hour = 9;
    int minute = 23;
    String block = ResultSet.findStartTimeBlock(hour, minute);
    Assert.assertEquals("9.5", block);
  }

  @Test
  public void findStartTimeBlockAtBoundary2() {
    int hour = 9;
    int minute = 27;
    String block = ResultSet.findStartTimeBlock(hour, minute);
    Assert.assertEquals("9.5", block);
  }

  @Test
  public void findStartTimeBlockAtBoundary4() {
    int hour = 9;
    int minute = 36;
    String block = ResultSet.findStartTimeBlock(hour, minute);
    Assert.assertEquals("9.5", block);
  }

  @Test
  public void findStartTimeBlockAtBoundary5() {
    int hour = 9;
    int minute = 40;
    String block = ResultSet.findStartTimeBlock(hour, minute);
    Assert.assertEquals("9.75", block);
  }

  @Test
  public void findStartTimeBlockAtBoundary6() {
    int hour = 9;
    int minute = 56;
    String block = ResultSet.findStartTimeBlock(hour, minute);
    Assert.assertEquals("10.0", block);
  }
}