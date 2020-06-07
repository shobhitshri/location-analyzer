package com.location.commute.analysis.io;

import com.location.model.ResultSet;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import lombok.Setter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CSVOutputWriter implements AutoCloseable {

  private static final String COMMUTE_LOG_CSV = "commute_log.csv";
  @Setter
  private static String outputDir = System.getProperty("user.home");
  private static Logger logger = LogManager.getLogger(CSVOutputWriter.class);
  FileWriter out;
  CSVPrinter printer;

  public void createCSVFile() throws IOException {
    out = new FileWriter(getOutputFileNameWithPath());
    printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(ResultSet.getHeaders()));
  }

  public static String getOutputFileNameWithPath() {
    String dir = getDirectory();
    return dir + COMMUTE_LOG_CSV;
  }

  private static String getDirectory() {
    String directoryPath = outputDir;
    if (!outputDir.endsWith(File.separator)) {
      directoryPath = directoryPath + File.separator;
    }

    File directory = new File(directoryPath);
    if (!directory.exists()) {
      directory.mkdir();
    }
    return directoryPath;
  }

  public void writeToCSV(List<String> content) throws IOException {
    printer.printRecord(content);
  }

  public void close() {
    try {
      if (printer != null) {
        printer.flush();
        printer.close();
      }
    } catch (IOException ex) {
      logger.warn("Failed to close the CSV writer with exception: {}", ex);
    }
  }
}
