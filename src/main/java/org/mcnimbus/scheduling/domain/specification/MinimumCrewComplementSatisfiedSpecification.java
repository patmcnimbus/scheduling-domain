package org.mcnimbus.scheduling.domain.specification;

import org.mcnimbus.scheduling.domain.aircrew.Position;
import org.mcnimbus.scheduling.domain.schedule.Sortie;

public class MinimumCrewComplementSatisfiedSpecification extends AbstractSpecification<Sortie> {

  @Override
  public boolean test(Sortie sortie) {
    return thereAreAtLeastTwoPilots(sortie) && thereIsAtLeastOneBoomOperator(sortie);
  }

  private boolean thereAreAtLeastTwoPilots(Sortie sortie) {
    return sortie.getAirCrew().stream()
            .filter(crewMember -> crewMember.getPosition().equals(Position.P))
            .count()
        >= 2;
  }

  private boolean thereIsAtLeastOneBoomOperator(Sortie sortie) {
    return sortie.getAirCrew().stream()
            .filter(crewMember -> crewMember.getPosition().equals(Position.B))
            .count()
        >= 1;
  }
}
