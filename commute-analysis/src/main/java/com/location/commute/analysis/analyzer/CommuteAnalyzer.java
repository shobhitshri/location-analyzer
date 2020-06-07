package com.location.commute.analysis.analyzer;

import com.location.commute.analysis.io.CSVOutputWriter;
import com.location.commute.analysis.io.LocationStream;
import com.location.model.Commute;
import com.location.model.Location;
import com.location.model.ResultSet;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class CommuteAnalyzer {

  @Setter
  Location homeLocation;
  @Setter
  Location workLocation;

  public void analyze(String locationFilePath) {
    int day = -1;
    Commute morningCommute = Commute.builder().isFromHomeToWork(true).build();
    Commute eveningCommute = Commute.builder().isFromHomeToWork(false).build();
    Location lastHomeLocation = null;
    Location lastWorkLocation = null;

    try (CSVOutputWriter csvOutputWriter = new CSVOutputWriter();
        LocationStream locationIterator = new LocationStream(locationFilePath)) {
      csvOutputWriter.createCSVFile();
      while (locationIterator.hasNext()) {
        Location location = locationIterator.getNext();
        LocalDateTime date = location.getDateFromTimeStamp();
        if (day != date.getDayOfYear()) {
          day = date.getDayOfYear();
          morningCommute.resetLocations();
          eveningCommute.resetLocations();
          lastHomeLocation = null;
          lastWorkLocation = null;
        }

        if (isWork(location)) {
          lastWorkLocation = location;
          if (morningCommute.getHome() == null) {
            morningCommute.setHome(lastHomeLocation);
          }
          if (morningCommute.getWork() == null) {
            morningCommute.setWork(location);
          }
        }

        if (isHome(location)) {
          lastHomeLocation = location;
          if (lastWorkLocation != null && eveningCommute.getWork() == null) {
            eveningCommute.setWork(lastWorkLocation);
          }
          if (morningCommute.getWork() != null) {
            eveningCommute.setHome(location);
          }
        }

        if (morningCommute.getHome() != null && morningCommute.getWork() != null &&
            eveningCommute.getWork() != null && eveningCommute.getHome() != null) {
          writeCommuteToCSV(morningCommute, csvOutputWriter);
          writeReturnCommuteToCSV(morningCommute, eveningCommute, csvOutputWriter);
          morningCommute.resetLocations();
          eveningCommute.resetLocations();
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("Something unexpected occurred in analyzing the commute. Please "
          + "contact in GitHub project page if you need help.", e);
    }
  }

  private boolean isHome(Location location) {
    return homeLocation.equals(location);
  }

  private boolean isWork(Location location) {
    return workLocation.equals(location);
  }

  private void writeCommuteToCSV(Commute commute, CSVOutputWriter csvOutputWriter)
      throws IOException {
    //TODO: fix -1;
    List<String> records = ResultSet.buildRecord(commute, -1);
    csvOutputWriter.writeToCSV(records);
  }

  private void writeReturnCommuteToCSV(Commute morningCommute, Commute eveningCommute,
      CSVOutputWriter csvOutputWriter) throws IOException {
    double workDuration = getTimeInterval(morningCommute, eveningCommute);
    List<String> records = ResultSet.buildRecord(eveningCommute, workDuration);
    csvOutputWriter.writeToCSV(records);
  }

  public static double getTimeInterval(Commute toWork, Commute fromWork) {
    LocalDateTime reachingWork = toWork.getWork().getDateFromTimeStamp();
    LocalDateTime leavingWork = fromWork.getWork().getDateFromTimeStamp();
    double hours = ChronoUnit.HOURS.between(reachingWork, leavingWork);
    double minutes = ChronoUnit.MINUTES.between(reachingWork, leavingWork);
    return getTotalTimeInDecimal(hours, minutes);
  }

  static double getTotalTimeInDecimal(double hours, double minutes) {
    return hours + (minutes % 60) / 60;
  }

}
