package com.location.commute.controller;

import com.location.commute.analysis.io.CSVOutputWriter;
import com.location.model.Location;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParameterException;

@Command(description = "Generates the csv file with commute analysis.",
    name = "commute Analysis", version = "1.0")
public class Commute {

  private static final Logger logger = LogManager.getLogger(Commute.class);
  static CommuteParameters commuteParameters;

  @Option(names = {"-i", "--input"}, required = true, description = "the location history file")
  static String locationFilePath;

  @Option(names = {"-o", "--output"}, description = "the directory for "
      + "generated commute file")
  static String outputDirectoryPath;

  @Option(names = "-loh", required = true, description = "longitude coordinate for home location")
  static double longitudeHome;

  @Option(names = "-lah", required = true, description = "latitude coordinate for home location")
  static double latitudeHome;

  @Option(names = "-low", required = true, description = "longitude coordinate for work location")
  static double longitudeWork;

  @Option(names = "-law", required = true, description = "latitude coordinate for work location")
  static double latitudeWork;

  @Option(names = "-t", description = "the time zone to be used for analyzing data.")
  static String timeZone = null;

  @Option(names = "-m", description = "the size of the bounding box around your locations.")
  static double margin = Location.DEFAULT_MARGIN;

  public static void main(String... argv) {
    logger.info("starting analysis with arguments: {}", Arrays.toString(argv));
    if (parseArgs(argv) && validateParameters() && analyze()) {
      System.out.println("The output csv is generated at " + CSVOutputWriter
          .getOutputFileNameWithPath() + ". Have fun, Thank you!");
      logger.info("completed successfully {} ", Arrays.toString(argv));
    }
  }

  public static boolean analyze() {
    Analyzer analyzer = new Analyzer(commuteParameters);
    return analyzer.analyze();
  }

  public static boolean validateParameters() {
    commuteParameters = CommuteParameters.builder().latitudeHome(latitudeHome)
        .latitudeWork(latitudeWork).longitudeHome(longitudeHome).longitudeWork(longitudeWork)
        .outputDirectoryPath(outputDirectoryPath).locationFilePath(locationFilePath)
        .timeZone(timeZone).margin(margin).build();
    return commuteParameters.validate();
  }

  private static boolean parseArgs(final String[] argv) {
    try {
      Commute tar = new Commute();
      new CommandLine(tar).parseArgs(argv);
    } catch (ParameterException ex) {
      System.out.println("Please check the arguments, there is an error: " + ex.getMessage());
      logger.error("Exception in argument parsing: {} ", ex);
      return false;
    }
    return true;
  }
}
