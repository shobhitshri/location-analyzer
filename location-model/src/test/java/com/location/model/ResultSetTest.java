package com.location.model;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class ResultSetTest {

  @Test
  public void getHeaders() {
    String[] headers = ResultSet.getHeaders();
    assertEquals(9, headers.length);
  }

  @Test
  public void findStartTimeBlockUpperCeil() {
    int hour = 9;
    int minute = 14;
    String block = ResultSet.findStartTimeBlock(hour, minute);
    Assert.assertEquals("9.1", block);
  }

  @Test
  public void findStartTimeBlockLowerCeil() {
    int hour = 9;
    int minute = 17;
    String block = ResultSet.findStartTimeBlock(hour, minute);
    Assert.assertEquals("9.2", block);
  }

  @Test
  public void findStartTimeBlockAtBoundary() {
    int hour = 9;
    int minute = 20;
    String block = ResultSet.findStartTimeBlock(hour, minute);
    Assert.assertEquals("9.2", block);
  }
}