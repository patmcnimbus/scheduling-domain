package org.mcnimbus.scheduling.utility;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class TimeUtility {

  public static final LocalDate SCHEDULE_DATE = LocalDate.of(2021, 10, 8);

  public static OffsetDateTime createDateTime(Integer hour, Integer minute) {
    return OffsetDateTime.of(SCHEDULE_DATE, LocalTime.of(hour, minute), ZoneOffset.of("+00:00"));
  }
}
