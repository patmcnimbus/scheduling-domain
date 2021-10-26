package org.mcnimbus.scheduling.domain.specification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mcnimbus.scheduling.domain.schedule.Aircraft;
import org.mcnimbus.scheduling.domain.schedule.Schedule;
import org.mcnimbus.scheduling.domain.schedule.Sortie;
import org.mcnimbus.scheduling.infrastructure.StubScheduleRepository;
import org.mcnimbus.scheduling.infrastructure.StubSortieRepository;
import org.mcnimbus.scheduling.utility.TimeUtility;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AircraftHasSufficientTurnTimeSpecificationTest {

  private final LocalDate scheduleDate = LocalDate.parse("2021-10-08");
  private Schedule schedule;

  @BeforeEach
  public void setup() {
    schedule = getMockSchedule();
    Set<Sortie> sortieSet = getMockSorties();
    sortieSet.forEach(sortie -> schedule.addNewSortie(sortie));
  }

  @Test
  public void testNoDoubleTurns() {
    final var spec = new AircraftHasSufficientTurnTimeSpecification();
    assertTrue(spec.test(schedule));
  }

  @Test
  public void testSingleInvalidDoubleTurn() {
    Sortie sortie =
        schedule.getSorties().stream()
            .filter(sortie1 -> sortie1.getAircraft().getTailNumber().equals("580060"))
            .findFirst()
            .get();
    sortie.updateAircraft(new Aircraft("600345"));
    final var spec = new AircraftHasSufficientTurnTimeSpecification();
    assertFalse(spec.test(schedule));
  }

  @Test
  public void testSingleValidDoubleTurn() {
    Sortie sortie =
        schedule.getSorties().stream()
            .filter(sortie1 -> sortie1.getAircraft().getTailNumber().equals("580060"))
            .findFirst()
            .get();
    sortie.updateAircraft(new Aircraft("600345"));
    sortie.updateTakeoffTime(TimeUtility.createDateTime(18, 15));
    sortie.updateLandTime(TimeUtility.createDateTime(22, 15));
    final var spec = new AircraftHasSufficientTurnTimeSpecification();
    assertTrue(spec.test(schedule));
  }

  @Test
  public void testTwoDoubleTurnsOneInvalid() {
    Sortie sortie =
        schedule.getSorties().stream()
            .filter(sortie1 -> sortie1.getAircraft().getTailNumber().equals("580060"))
            .findFirst()
            .get();
    sortie.updateAircraft(new Aircraft("600345"));
    sortie.updateTakeoffTime(TimeUtility.createDateTime(18, 15));
    sortie.updateLandTime(TimeUtility.createDateTime(22, 15));
    Sortie anotherSortie = new Sortie(null);
    anotherSortie.updateAircraft(new Aircraft("591464"));
    anotherSortie.updateTakeoffTime(TimeUtility.createDateTime(16, 22));
    anotherSortie.updateLandTime(TimeUtility.createDateTime(18, 30));
    schedule.addNewSortie(anotherSortie);
    final var spec = new AircraftHasSufficientTurnTimeSpecification();
    assertFalse(spec.test(schedule));
  }

  @Test
  public void testTwoDoubleTurnsBothValid() {
    Sortie sortie =
        schedule.getSorties().stream()
            .filter(sortie1 -> sortie1.getAircraft().getTailNumber().equals("580060"))
            .findFirst()
            .get();
    sortie.updateAircraft(new Aircraft("600345"));
    sortie.updateTakeoffTime(TimeUtility.createDateTime(18, 15));
    sortie.updateLandTime(TimeUtility.createDateTime(22, 15));
    Sortie anotherSortie = new Sortie(null);
    anotherSortie.updateAircraft(new Aircraft("591464"));
    anotherSortie.updateTakeoffTime(TimeUtility.createDateTime(17, 45));
    anotherSortie.updateLandTime(TimeUtility.createDateTime(20, 55));
    schedule.addNewSortie(anotherSortie);
    final var spec = new AircraftHasSufficientTurnTimeSpecification();
    assertTrue(spec.test(schedule));
  }

  private Schedule getMockSchedule() {
    return new StubScheduleRepository().retrieveByDate(scheduleDate);
  }

  private Set<Sortie> getMockSorties() {
    return new StubSortieRepository().retrieveSortiesForDay(scheduleDate);
  }
}
