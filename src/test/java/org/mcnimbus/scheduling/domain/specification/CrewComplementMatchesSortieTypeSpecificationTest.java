package org.mcnimbus.scheduling.domain.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mcnimbus.scheduling.domain.aircrew.CrewMember;
import org.mcnimbus.scheduling.domain.aircrew.Position;
import org.mcnimbus.scheduling.domain.schedule.Sortie;
import org.mcnimbus.scheduling.domain.schedule.SortieType;
import org.mcnimbus.scheduling.infrastructure.StubCrewMemberRepository;

import java.util.UUID;

public class CrewComplementMatchesSortieTypeSpecificationTest {

  private Sortie testSortie = mockTestSortie();

  @BeforeEach
  public void setup() {
    testSortie = mockTestSortie();
  }

  @Test
  public void testOpsSortie() {
    final var spec = new CrewComplementMatchesSortieTypeSpecification();
    assertTrue(spec.test(testSortie));
  }

  @Test
  public void testCheckSortie() {
    final var spec = new CrewComplementMatchesSortieTypeSpecification();
    testSortie.updateSortieType(Position.P, SortieType.CHECK);
    testSortie.updateSortieType(Position.B, SortieType.CHECK);
    assertFalse(spec.test(testSortie));
  }

  @Test
  public void testTrainingSortie() {
    final var spec = new CrewComplementMatchesSortieTypeSpecification();
    testSortie.updateSortieType(Position.P, SortieType.NORMAL_TRAINING);
    testSortie.updateSortieType(Position.B, SortieType.NORMAL_TRAINING);
    assertFalse(spec.test(testSortie));
  }

  private Sortie mockTestSortie() {
    StubCrewMemberRepository crewMemberRepository = new StubCrewMemberRepository();
    Sortie testSortie = new Sortie(null);
    testSortie.updateSortieType(Position.P, SortieType.OPS);
    testSortie.updateSortieType(Position.B, SortieType.OPS);
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
