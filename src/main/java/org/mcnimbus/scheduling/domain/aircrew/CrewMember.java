package org.mcnimbus.scheduling.domain.aircrew;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Set;
import java.util.UUID;

// entity
@Getter
@AllArgsConstructor
public class CrewMember {

  private UUID id;
  private String name;
  private Position position;
  private Qualification qualification;
  private Set<CurrencyItem> currencyItems;
}
