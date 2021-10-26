package org.mcnimbus.scheduling.domain.specification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mcnimbus.scheduling.domain.schedule.Aircraft;
import org.mcnimbus.scheduling.domain.schedule.Schedule;
import org.mcnimbus.scheduling.domain.schedule.Sortie;
import org.mcnimbus.scheduling.infrastructure.StubScheduleRepository;
import org.mcnimbus.scheduling.infrastructure.StubSortieRepository;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AircraftDoNotDoubleTurnSpecificationTest {

  private final LocalDate scheduleDate = LocalDate.parse("2021-10-08");
  private Schedule schedule;

  @BeforeEach
  public void setup() {
    schedule = getMockSchedule();
    Set<Sortie> sortieSet = getMockSorties();
    sortieSet.forEach(sortie -> schedule.addNewSortie(sortie));
  }

  @Test
  public void testSpecTrue() {
    final var spec = new AircraftDoNotDoubleTurnSpecification();
    assertTrue(spec.test(schedule));
  }

  @Test
  public void testSpecFalse() {
    schedule.getSorties().stream().forEach(sortie -> sortie.updateAircraft(new Aircraft("591464")));
    final var spec = new AircraftDoNotDoubleTurnSpecification();
    assertFalse(spec.test(schedule));
  }

  private Schedule getMockSchedule() {
    return new StubScheduleRepository().retrieveByDate(scheduleDate);
  }

  private Set<Sortie> getMockSorties() {
    return new StubSortieRepository().retrieveSortiesForDay(scheduleDate);
  }
}
