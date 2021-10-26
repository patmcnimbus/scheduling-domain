package org.mcnimbus.scheduling.infrastructure;

import org.mcnimbus.scheduling.domain.schedule.Aircraft;
import org.mcnimbus.scheduling.domain.schedule.AircraftRepository;

import java.util.HashMap;
import java.util.Map;

public class StubAircraftRepository implements AircraftRepository {

  private Map<String, Aircraft> aircraftMap = new HashMap<>();

  public StubAircraftRepository() {
    Aircraft a1 = new Aircraft("591464");
    aircraftMap.put(a1.getTailNumber(), a1);
    Aircraft a2 = new Aircraft("600345");
    aircraftMap.put(a2.getTailNumber(), a2);
    Aircraft a3 = new Aircraft("580060");
    aircraftMap.put(a3.getTailNumber(), a3);
  }

  @Override
  public Aircraft retrieveByTailNumber(String tailNumber) {
    Aircraft aircraft = aircraftMap.get(tailNumber);
    if (aircraft == null) {
      throw new RuntimeException("aircraft not found");
    }
    return aircraft;
  }
}
