package org.mcnimbus.scheduling.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mcnimbus.scheduling.application.impl.ScheduleServiceImpl;
import org.mcnimbus.scheduling.domain.aircrew.CrewMember;
import org.mcnimbus.scheduling.domain.aircrew.CrewMemberRepository;
import org.mcnimbus.scheduling.domain.aircrew.Position;
import org.mcnimbus.scheduling.domain.schedule.*;
import org.mcnimbus.scheduling.infrastructure.StubAircraftRepository;
import org.mcnimbus.scheduling.infrastructure.StubCrewMemberRepository;
import org.mcnimbus.scheduling.infrastructure.StubScheduleRepository;
import org.mcnimbus.scheduling.infrastructure.StubSortieRepository;
import org.mcnimbus.scheduling.utility.TimeUtility;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplicationLayerTest {

  private static final LocalDate scheduleDate = LocalDate.of(2021, Month.OCTOBER, 8);

  private ScheduleService scheduleService;

  private CrewMemberRepository crewMemberRepository;
  private AircraftRepository aircraftRepository;

  @BeforeEach
  public void setup() {
    crewMemberRepository = new StubCrewMemberRepository();
    aircraftRepository = new StubAircraftRepository();
    SortieRepository sortieRepository = new StubSortieRepository();
    ScheduleRepository scheduleRepository = new StubScheduleRepository();
    scheduleService = new ScheduleServiceImpl(scheduleRepository, sortieRepository);
  }

  @Test
  public void testScheduleBuild() {
    Schedule testSchedule = scheduleService.retrieveScheduleForDate(scheduleDate);
    AirRefueling refueling =
        AirRefueling.builder()
            .trackName("AR5LW")
            .receiverType("C5")
            .receiverCallSign("Jumbo50")
            .delayStartTime(TimeUtility.createDateTime(9, 15))
            .arct(TimeUtility.createDateTime(9, 30))
            .refuelingEndTime(TimeUtility.createDateTime(10, 30))
            .rendezvousType("PP")
            .build();
    Sortie testSortie = new Sortie(refueling);
    testSchedule.addNewSortie(testSortie);
    setSortieTypes(testSortie);
    addCrewMembers(testSortie);
    setTimes(testSortie);
    setFuel(testSortie);
    setTailNumber(testSortie);
    testSchedule.updateSortie(testSortie);
    assertTrue(testSchedule.getAircraftAreDeconflicted());
    assertTrue(testSortie.getAllValidationsPass());
  }

  private void setSortieTypes(Sortie sortie) {
    sortie.updateSortieType(Position.P, SortieType.CHECK);
    sortie.updateSortieType(Position.B, SortieType.OPS);
  }

  private void addCrewMembers(Sortie sortie) {
    CrewMember toBeEvaluated =
        crewMemberRepository.retrieveById(UUID.fromString("fada6a9a-6a4a-4208-bcea-5f0774f0aa6c"));
    sortie.addCrewMember(toBeEvaluated);
    CrewMember helper =
        crewMemberRepository.retrieveById(UUID.fromString("8185adbe-9949-4ea0-bdc5-1c8b82a182c6"));
    sortie.addCrewMember(helper);
    CrewMember evaluator =
        crewMemberRepository.retrieveById(UUID.fromString("1919bf74-9761-4271-84f5-e9066f5cda7c"));
    sortie.addCrewMember(evaluator);
    CrewMember boomOperator =
        crewMemberRepository.retrieveById(UUID.fromString("912d076e-2c9b-4648-a68c-1c637b423d88"));
    sortie.addCrewMember(boomOperator);
  }

  private void setTimes(Sortie sortie) {
    sortie.updateTakeoffTime(TimeUtility.createDateTime(7, 30));
    sortie.updateLandTime(TimeUtility.createDateTime(13, 30));
  }

  private void setFuel(Sortie sortie) {
    sortie.updateFuelLoad(95000);
  }

  private void setTailNumber(Sortie sortie) {
    Aircraft aircraft = aircraftRepository.retrieveByTailNumber("600345");
    sortie.updateAircraft(aircraft);
  }
}
