package com.location.commute.controller;

import com.location.commute.analysis.io.CSVOutputWriter;
import com.location.model.Location;
import java.io.File;
import java.time.ZoneId;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommuteParameters {

  private String locationFilePath;
  private String outputDirectoryPath;
  private double longitudeHome;
  private double latitudeHome;
  private double longitudeWork;
  private double latitudeWork;
  private double margin;
  private String timeZone;

  public boolean validate() {
    if (areCoordinatesValid() &&
        isInputFileJson() &&
        isInputFileExists() &&
        isOutputLocationADirectory() &&
        isTimeZoneValid()) {
      initParams();
      return true;
    }
    return false;
  }

  private void initParams() {
    CSVOutputWriter.setOutputDir(outputDirectoryPath);
    Location.setMargin(margin);
    if (timeZone != null) {
      Location.setZoneId(ZoneId.of(ZoneId.SHORT_IDS.get(timeZone)));
    }
  }

  boolean isTimeZoneValid() {
    if (timeZone == null) {
      return true;
    }
    return ZoneId.SHORT_IDS.containsKey(timeZone);
  }

  private boolean isInputFileJson() {
    if (!locationFilePath.endsWith(".json")) {
      System.out.println("location file path should be a json file.");
      return false;
    }
    return true;
  }

  private boolean isInputFileExists() {
    if (!(new File(locationFilePath).isFile())) {
      System.out.println("There is no file existing in the given location file path.");
      return false;
    }
    return true;
  }

  private boolean isOutputLocationADirectory() {
    if (!(outputDirectoryPath != null && new File(outputDirectoryPath).isDirectory())) {
      System.out.println("output file path is incorrect, it should be an existing directory.");
      return false;
    }
    return true;
  }

  private boolean areCoordinatesValid() {
    return isLatitudeValid(latitudeHome) &&
        isLatitudeValid(latitudeWork) &&
        isLongitudeValid(longitudeHome) &&
        isLongitudeValid(longitudeWork);
  }

  private boolean isLongitudeValid(double longitude) {
    if (longitude > 180 || longitude < -180) {
      System.out.println("longitude value " + longitude + " is out of range. It should be between"
          + " +180 to -180.");
      return false;
    }
    return true;
  }

  private boolean isLatitudeValid(double latitude) {
    if (latitude > 90 || latitude < -90) {
      System.out.println("latitude value " + latitude + " is out of range. It should be between"
          + " +90 to -90.");
      return false;
    }
    return true;
  }
}
