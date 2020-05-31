package com.location.commute.controller;

import org.junit.Assert;
import org.junit.Test;

public class CommuteParametersTest {

  private String locationFilePath = "src/test/resources/sample.json";
  private String outputDirectoryPath = "src/test/resources/";
  private double longitudeHome = -122.038907;
  private double latitudeHome = 47.552843;
  private double longitudeWork = -122.336076;
  private double latitudeWork = 47.615706;
  private double margin = 10000;
  private String timeZone = "PST";

  @Test
  public void allParamCorrect_ValidateTrue() {
    CommuteParameters commuteParameters = CommuteParameters.builder().latitudeHome(latitudeHome)
        .latitudeWork(latitudeWork).longitudeHome(longitudeHome).longitudeWork(longitudeWork)
        .outputDirectoryPath(outputDirectoryPath).locationFilePath(locationFilePath)
        .timeZone(timeZone).margin(margin).build();
    Assert.assertTrue(commuteParameters.validate());
  }

  @Test
  public void outputDirInCorrect_ValidateFalse() {
    String localOutputDirectoryPath = "src/test/resources/outputDirectoryPath";
    CommuteParameters commuteParameters = CommuteParameters.builder().latitudeHome(latitudeHome)
        .latitudeWork(latitudeWork).longitudeHome(longitudeHome).longitudeWork(longitudeWork)
        .outputDirectoryPath(localOutputDirectoryPath).locationFilePath(locationFilePath)
        .timeZone(timeZone).margin(margin).build();
    Assert.assertFalse(commuteParameters.validate());
  }

  @Test
  public void inputLocationNotJson_ValidateFalse() {
    String localLocationFilePath = "src/test/resources/sample.txt";
    CommuteParameters commuteParameters = CommuteParameters.builder().latitudeHome(latitudeHome)
        .latitudeWork(latitudeWork).longitudeHome(longitudeHome).longitudeWork(longitudeWork)
        .outputDirectoryPath(outputDirectoryPath).locationFilePath(localLocationFilePath)
        .timeZone(timeZone).margin(margin).build();
    Assert.assertFalse(commuteParameters.validate());
  }

  @Test
  public void inputLocationNotExisting_ValidateFalse() {
    String localLocationFilePath = "src/test/resources/sampleNotExisting.json";
    CommuteParameters commuteParameters = CommuteParameters.builder().latitudeHome(latitudeHome)
        .latitudeWork(latitudeWork).longitudeHome(longitudeHome).longitudeWork(longitudeWork)
        .outputDirectoryPath(outputDirectoryPath).locationFilePath(localLocationFilePath)
        .timeZone(timeZone).margin(margin).build();
    Assert.assertFalse(commuteParameters.validate());
  }

  @Test
  public void latitudeHomeIncorrectPositive_ValidateFalse() {
    double localLatitudeHome = 120;
    CommuteParameters commuteParameters = CommuteParameters.builder()
        .latitudeHome(localLatitudeHome)
        .latitudeWork(latitudeWork).longitudeHome(longitudeHome).longitudeWork(longitudeWork)
        .outputDirectoryPath(outputDirectoryPath).locationFilePath(locationFilePath)
        .timeZone(timeZone).margin(margin).build();
    Assert.assertFalse(commuteParameters.validate());
  }

  @Test
  public void latitudeHomeIncorrectNegative_ValidateFalse() {
    double localLatitudeHome = -120;
    CommuteParameters commuteParameters = CommuteParameters.builder()
        .latitudeHome(localLatitudeHome)
        .latitudeWork(latitudeWork).longitudeHome(longitudeHome).longitudeWork(longitudeWork)
        .outputDirectoryPath(outputDirectoryPath).locationFilePath(locationFilePath)
        .timeZone(timeZone).margin(margin).build();
    Assert.assertFalse(commuteParameters.validate());
  }

  @Test
  public void latitudeWorkIncorrectPositive_ValidateFalse() {
    double localLatitudeWork = 120;
    CommuteParameters commuteParameters = CommuteParameters.builder().latitudeHome(latitudeHome)
        .latitudeWork(localLatitudeWork).longitudeHome(longitudeHome).longitudeWork(longitudeWork)
        .outputDirectoryPath(outputDirectoryPath).locationFilePath(locationFilePath)
        .timeZone(timeZone).margin(margin).build();
    Assert.assertFalse(commuteParameters.validate());
  }

  @Test
  public void latitudeWorkIncorrectNegative_ValidateFalse() {
    double localLatitudeWork = -120;
    CommuteParameters commuteParameters = CommuteParameters.builder().latitudeHome(latitudeHome)
        .latitudeWork(localLatitudeWork).longitudeHome(longitudeHome).longitudeWork(longitudeWork)
        .outputDirectoryPath(outputDirectoryPath).locationFilePath(locationFilePath)
        .timeZone(timeZone).margin(margin).build();
    Assert.assertFalse(commuteParameters.validate());
  }

  @Test
  public void longitudeHomeIncorrectPositive_ValidateFalse() {
    double localLongitudeHome = 190;
    CommuteParameters commuteParameters = CommuteParameters.builder()
        .latitudeHome(latitudeHome)
        .latitudeWork(latitudeWork).longitudeHome(localLongitudeHome).longitudeWork(longitudeWork)
        .outputDirectoryPath(outputDirectoryPath).locationFilePath(locationFilePath)
        .timeZone(timeZone).margin(margin).build();
    Assert.assertFalse(commuteParameters.validate());
  }

  @Test
  public void longitudeHomeIncorrectNegative_ValidateFalse() {
    double localLongitudeHome = -190;
    CommuteParameters commuteParameters = CommuteParameters.builder()
        .latitudeHome(latitudeHome)
        .latitudeWork(latitudeWork).longitudeHome(localLongitudeHome).longitudeWork(longitudeWork)
        .outputDirectoryPath(outputDirectoryPath).locationFilePath(locationFilePath)
        .timeZone(timeZone).margin(margin).build();
    Assert.assertFalse(commuteParameters.validate());
  }

  @Test
  public void longitudeWorkIncorrectPositive_ValidateFalse() {
    double localLongitudeWork = 190;
    CommuteParameters commuteParameters = CommuteParameters.builder().latitudeHome(latitudeHome)
        .latitudeWork(latitudeWork).longitudeHome(longitudeHome).longitudeWork(localLongitudeWork)
        .outputDirectoryPath(outputDirectoryPath).locationFilePath(locationFilePath)
        .timeZone(timeZone).margin(margin).build();
    Assert.assertFalse(commuteParameters.validate());
  }

  @Test
  public void longitudeWorkIncorrectNegative_ValidateFalse() {
    double localLongitudeWork = -190;
    CommuteParameters commuteParameters = CommuteParameters.builder().latitudeHome(latitudeHome)
        .latitudeWork(latitudeWork).longitudeHome(longitudeHome).longitudeWork(localLongitudeWork)
        .outputDirectoryPath(outputDirectoryPath).locationFilePath(locationFilePath)
        .timeZone(timeZone).margin(margin).build();
    Assert.assertFalse(commuteParameters.validate());
  }

  @Test
  public void timeZoneInCorrect_ValidateFalse() {
    String testTimeZone = "ABC";
    CommuteParameters commuteParameters = CommuteParameters.builder().latitudeHome(latitudeHome)
        .latitudeWork(latitudeWork).longitudeHome(longitudeHome).longitudeWork(longitudeWork)
        .outputDirectoryPath(outputDirectoryPath).locationFilePath(locationFilePath)
        .timeZone(testTimeZone).margin(margin).build();
    Assert.assertFalse(commuteParameters.validate());
  }

}