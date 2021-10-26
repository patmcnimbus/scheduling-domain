package org.mcnimbus.scheduling.domain.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mcnimbus.scheduling.domain.aircrew.CrewMember;
import org.mcnimbus.scheduling.domain.schedule.Sortie;
import org.mcnimbus.scheduling.infrastructure.StubCrewMemberRepository;

import java.util.UUID;

public class MinimumCrewComplementSatisfiedSpecificationTest {

  private Sortie testSortie = mockTestSortie();

  @BeforeEach
  public void setup() {
    mockTestSortie();
  }

  @Test
  public void testValidMinCrew() {
    final var spec = new MinimumCrewComplementSatisfiedSpecification();
    assertTrue(spec.test(testSortie));
  }

  @Test
  public void testInvalidMinCrew() {
    CrewMember crewMemberToRemove =
        testSortie.getAirCrew().stream()
            .filter(
                crewMember ->
                    crewMember
                        .getId()
                        .equals(UUID.fromString("fada6a9a-6a4a-4208-bcea-5f0774f0aa6c")))
            .findFirst()
            .get();
    testSortie.removeCrewMember(crewMemberToRemove);
    final var spec = new MinimumCrewComplementSatisfiedSpecification();
    assertFalse(spec.test(testSortie));
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
    return testSortie;
  }
}
