package org.mcnimbus.scheduling.domain.schedule;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

// value object
@Builder
@Getter
public class AirRefueling {

  private String receiverType;
  private String receiverCallSign;
  private String trackName;
  private OffsetDateTime delayStartTime;
  private OffsetDateTime arct;
  private OffsetDateTime refuelingEndTime;
  private String rendezvousType;
}
