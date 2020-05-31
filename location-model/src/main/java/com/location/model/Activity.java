package com.location.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Activity {

  private final String timestampMs;
  private final ActivityBlock[] activityBlocks;
}
