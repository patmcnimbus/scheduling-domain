package org.mcnimbus.scheduling.domain.specification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mcnimbus.scheduling.domain.aircrew.CrewMember;
import org.mcnimbus.scheduling.domain.schedule.Sortie;
import org.mcnimbus.scheduling.utility.TimeUtility;
import org.mcnimbus.scheduling.infrastructure.StubCrewMemberRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AircrewCurrentQualifiedOrSupervisedSpecificationTest {

  private Sortie testSortie = mockTestSortie();

  @BeforeEach
  public void setup() {
    mockTestSortie();
  }

  @Test
  public void testValidSortie() {
    final var spec = new AircrewCurrentQualifiedOrSupervisedSpecification();
    assertTrue(spec.test(testSortie));
  }

  @Test
  public void testOpsSortieAllNonCurrent() {
    final var spec = new AircrewCurrentQualifiedOrSupervisedSpecification();
    testSortie.updateTakeoffTime(
        OffsetDateTime.of(
            LocalDateTime.of(2021, Month.DECEMBER, 8, 9, 45), ZoneOffset.of("+00:00")));
    assertFalse(spec.test(testSortie));
  }

  @Test
  public void testCurrQualSupervized() {
    testSortie = mockCurrencyAndQualCases();
    final var spec = new AircrewCurrentQualifiedOrSupervisedSpecification();
    assertTrue(spec.test(testSortie));
  }

  @Test
  public void testCurrQualUnSupervised() {
    testSortie = mockCurrencyAndQualCasesInvalid();
    final var spec = new AircrewCurrentQualifiedOrSupervisedSpecification();
    assertFalse(spec.test(testSortie));
  }

  private Sortie mockCurrencyAndQualCasesInvalid() {
    StubCrewMemberRepository crewMemberRepository = new StubCrewMemberRepository();
    Sortie currQualSortie = new Sortie(null);
    CrewMember firstPilot =
        crewMemberRepository.retrieveById(UUID.fromString("8185adbe-9949-4ea0-bdc5-1c8b82a182c6"));
    currQualSortie.addCrewMember(firstPilot);
    CrewMember secondPilot =
        crewMemberRepository.retrieveById(UUID.fromString("f9f77931-949a-4385-a963-72f77a7a87a3"));
    currQualSortie.addCrewMember(secondPilot);
    CrewMember nc =
        crewMemberRepository.retrieveById(UUID.fromString("c4adb4d6-f792-4a3c-ac67-e6c8bebe102c"));
    currQualSortie.addCrewMember(nc);
    CrewMember boomOperator =
        crewMemberRepository.retrieveById(UUID.fromString("7db36c63-0015-4557-9b9d-2d139f8286ba"));
    currQualSortie.addCrewMember(boomOperator);
    CrewMember fb =
        crewMemberRepository.retrieveById(UUID.fromString("6f8b1779-32eb-4fb6-9b70-3acbdb6dfcd9"));
    currQualSortie.addCrewMember(fb);
    currQualSortie.updateTakeoffTime(TimeUtility.createDateTime(8, 00));
    return currQualSortie;
  }

  private Sortie mockCurrencyAndQualCases() {
    StubCrewMemberRepository crewMemberRepository = new StubCrewMemberRepository();
    Sortie currQualSortie = new Sortie(null);
    CrewMember firstPilot =
        crewMemberRepository.retrieveById(UUID.fromString("54bbea47-620b-4dbb-bd6c-7b7da4d2f222"));
    currQualSortie.addCrewMember(firstPilot);
    CrewMember secondPilot =
        crewMemberRepository.retrieveById(UUID.fromString("f9f77931-949a-4385-a963-72f77a7a87a3"));
    currQualSortie.addCrewMember(secondPilot);
    CrewMember nc =
        crewMemberRepository.retrieveById(UUID.fromString("c4adb4d6-f792-4a3c-ac67-e6c8bebe102c"));
    currQualSortie.addCrewMember(nc);
    CrewMember boomOperator =
        crewMemberRepository.retrieveById(UUID.fromString("eccf76d4-97ba-4b30-87d7-645b9a5037d0"));
    currQualSortie.addCrewMember(boomOperator);
    CrewMember fb =
        crewMemberRepository.retrieveById(UUID.fromString("6f8b1779-32eb-4fb6-9b70-3acbdb6dfcd9"));
    currQualSortie.addCrewMember(fb);
    currQualSortie.updateTakeoffTime(TimeUtility.createDateTime(8, 00));
    return currQualSortie;
  }

  private Sortie mockTestSortie() {
    StubCrewMemberRepository crewMemberRepository = new StubCrewMemberRepository();
    Sortie testSortie = new Sortie(null);
    CrewMember firstPilot =
        crewMemberRepository.retrieveById(UUID.fromString("8185adbe-9949-4ea0-bdc5-1c8b82a182c6"));
    testSortie.addCrewMember(firstPilot);
    CrewMember secondPilot =
        crewMemberRepository.retrieveById(UUID.fromString("fada6a9a-6a4a-4208-bcea-5f0774f0aa6c"));
    testSortie.addCrewMember(secondPilot);
    CrewMember boomOperator =
        crewMemberRepository.retrieveById(UUID.fromString("eccf76d4-97ba-4b30-87d7-645b9a5037d0"));
    testSortie.addCrewMember(boomOperator);
    testSortie.updateTakeoffTime(TimeUtility.createDateTime(8, 00));
    return testSortie;
  }
}
