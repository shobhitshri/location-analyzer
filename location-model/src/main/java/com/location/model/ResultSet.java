package com.location.model;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;

@Builder
public class ResultSet {

  String year;
  String month;
  String date;
  String day;
  String hour;
  String minute;
  String block;
  String duration;
  String toWork;
  String workHours;
  String totalCommute;
  String ratio;

  public static String[] getHeaders() {
    java.lang.reflect.Field[] allFields = ResultSet.class.getDeclaredFields();
    String[] record = new String[allFields.length];
    for (int ii = 0; ii < allFields.length; ii++) {
      record[ii] = allFields[ii].getName();
    }
    return record;
  }

  //TODO: Change it to builder
  public static List<String> buildRecord(Commute commute, double workTime, double commuteTime) {
    List<String> record = new ArrayList<>();
    LocalDateTime date = commute.getCommuteStartTime();
    record.add(String.valueOf(date.getYear()));
    record.add(String.valueOf(date.getMonth()));
    record.add(String.valueOf(date.getDayOfMonth()));
    record.add(String.valueOf(date.getDayOfWeek()));
    record.add(String.valueOf(date.getHour()));
    record.add(String.valueOf(date.getMinute()));
    record.add(findStartTimeBlock(date.getHour(), date.getMinute()));
    record.add(getFormattedTimeInterval(commute.getCommuteDuration()));
    record.add(String.valueOf(commute.isFromHomeToWork()));
    if (workTime != -1) {
      record.add(getFormattedTimeInterval(workTime));
    }
    if (commuteTime != -1) {
      record.add(getFormattedTimeInterval(commuteTime));
      record.add(getFormattedTimeInterval(
          ((commuteTime / (commuteTime + workTime)) * 100)));
    }

    return record;
  }

  static String getFormattedTimeInterval(final double interval) {
    DecimalFormat df = new DecimalFormat("#.##");
    df.setRoundingMode(RoundingMode.CEILING);
    return df.format(interval);
  }

  static String findStartTimeBlock(int hour, int minute) {
    double minuteBlock = roundUp(minute, 15);
    double block = hour + minuteBlock / 60;
    return String.valueOf(block);
  }

  static double roundUp(int toRound, double blockSize) {
    double multiple = blockSize;
    double rem = toRound % multiple;
    double result = toRound - rem;
    if (rem > (multiple / 2)) {
      result += multiple;
    }
    return result;
  }
}
