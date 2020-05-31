package com.location.commute.controller;

import com.location.commute.analysis.analyzer.CommuteAnalyzer;
import com.location.model.Location;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Setter
public class Analyzer {

  private static final Logger logger = LogManager.getLogger(Analyzer.class);
  CommuteParameters commuteParameters;

  Analyzer(CommuteParameters commuteParameters) {
    this.commuteParameters = commuteParameters;
  }

  public boolean analyze() {
    try {
      Location homeLocation = getLocation(commuteParameters.getLatitudeHome(),
          commuteParameters.getLongitudeHome());
      Location workLocation = getLocation(commuteParameters.getLatitudeWork(),
          commuteParameters.getLongitudeWork());

      CommuteAnalyzer commuteAnalyzer = new CommuteAnalyzer(homeLocation, workLocation);
      commuteAnalyzer.analyze(commuteParameters.getLocationFilePath());
    } catch (Exception ex) {
      System.out.println(
          "Some unknown error happened, please contact me on the GitHUb page: " + ex.getMessage());
      logger.error("Exception in processing: {} ", ex);
      return false;
    }
    return true;

  }

  private Location getLocation(final double latitude, final double longitude) {
    return Location.builder()
        .latitudeE7(getE7Coordinate(latitude))
        .longitudeE7(getE7Coordinate(longitude))
        .build();
  }

  private static long getE7Coordinate(final double coordinate) {
    return (long) (coordinate * (1e7));
  }
}
