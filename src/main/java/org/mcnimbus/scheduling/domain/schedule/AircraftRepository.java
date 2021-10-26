package org.mcnimbus.scheduling.domain.schedule;

public interface AircraftRepository {

  Aircraft retrieveByTailNumber(String tailNumber);
}
