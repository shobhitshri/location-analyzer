package com.location.commute.analysis.io;

import com.google.gson.Gson;
import com.location.model.Location;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class LocationStreamTest {

  //@Test() //For debugging purposes
  public void getDataForADay() throws IOException {
    OutputStream outputStream = new FileOutputStream(
        CSVOutputWriter.getOutputJSONFileNameWithPath());
    OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
    LocalDateTime timeStart = LocalDateTime.of(2019, 02, 22, 16, 30, 0, 0);
    String locationFilePath = "src/test/resources/sample.json";
    LocationStream locationStream = new LocationStream(locationFilePath);
    Gson gson = new Gson();
    List<Location> locations = new ArrayList();
    while (locationStream.hasNext()) {
      Location location = locationStream.getNext();
      LocalDateTime localDateTime = location.getDateFromTimeStamp();
      if (Math.abs(ChronoUnit.MINUTES.between(localDateTime, timeStart)) < 30) {
        locations.add(location);
      }
    }
    Assert.assertEquals(0, locations.size());
    String json = gson.toJson(locations);
    writer.write(json);
    writer.flush();
    outputStream.flush();
    writer.close();
    outputStream.close();
  }
}