package org.mcnimbus.scheduling.domain.aircrew;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

// entity
@Getter
@AllArgsConstructor
public class CurrencyItem {

  private String eventCode;
  private UUID crewMemberId;
  private String eventName;
  private Integer numberRemaining;
  private LocalDate dateLastAccomplished;
  private Integer currencyRequirementInDays;
}
