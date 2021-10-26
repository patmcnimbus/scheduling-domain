package org.mcnimbus.scheduling.domain.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

// entity
@Getter
@AllArgsConstructor
public class Aircraft {

  private String tailNumber;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Aircraft aircraft = (Aircraft) o;
    return tailNumber.equals(aircraft.tailNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tailNumber);
  }
}
