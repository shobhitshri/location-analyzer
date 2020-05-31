package com.location.commute.analysis.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.location.model.Location;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public class LocationStream implements AutoCloseable {

  private final Gson gson = new GsonBuilder().create();
  InputStream stream;
  JsonReader reader;

  public LocationStream(String locationFilePath) throws IOException {
    stream = new FileInputStream(locationFilePath);
    reader = new JsonReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
    reader.beginObject();
    String name = reader.nextName();
    if (!name.equals("locations")) {
      throw new IllegalArgumentException("The file doesn't seem to have right format. The "
          + "expected token on line 2 is locations but found " + name);
    }
    reader.beginArray();
  }

  public boolean hasNext() throws IOException {
    return reader.hasNext();
  }

  public Location getNext() {
    return gson.fromJson(reader, Location.class);
  }

  @Override
  public void close() {
    IOUtils.closeQuietly(reader);
    IOUtils.closeQuietly(stream);
  }
}
