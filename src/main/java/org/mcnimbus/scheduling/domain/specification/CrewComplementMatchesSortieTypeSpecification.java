package org.mcnimbus.scheduling.domain.specification;

import org.mcnimbus.scheduling.domain.aircrew.CrewMember;
import org.mcnimbus.scheduling.domain.aircrew.Position;
import org.mcnimbus.scheduling.domain.aircrew.Qualification;
import org.mcnimbus.scheduling.domain.schedule.Sortie;
import org.mcnimbus.scheduling.domain.schedule.SortieType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CrewComplementMatchesSortieTypeSpecification extends AbstractSpecification<Sortie> {

  @Override
  public boolean test(Sortie sortie) {
    return sortie.getSortieTypes().keySet().stream()
        .allMatch(
            position ->
                aircrewForPositionMeetsRequirement(
                    position, sortie.getSortieTypes().get(position), sortie.getAirCrew()));
  }

  private boolean aircrewForPositionMeetsRequirement(
      Position position, SortieType sortieType, Set<CrewMember> aircrew) {
    Set<Qualification> quals = new HashSet<>();
    Set<CrewMember> filteredCrew =
        aircrew.stream()
            .filter(crewMember -> crewMember.getPosition().equals(position))
            .collect(Collectors.toSet());
    if (sortieType.equals(SortieType.OPS)) {
      quals.addAll(List.of(Qualification.E, Qualification.I, Qualification.M));
    } else if (sortieType.equals(SortieType.CHECK)) {
      quals.add(Qualification.E);
    } else {
      quals.addAll(List.of(Qualification.I, Qualification.E));
    }
    return filteredAircrewMeetQualRequirements(filteredCrew, quals);
  }

  private boolean filteredAircrewMeetQualRequirements(
      Set<CrewMember> filteredCrew, Set<Qualification> quals) {
    return filteredCrew.stream()
        .anyMatch(crewMember -> quals.contains(crewMember.getQualification()));
  }
}
