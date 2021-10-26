package org.mcnimbus.scheduling.domain.specification;

import org.mcnimbus.scheduling.domain.aircrew.CrewMember;
import org.mcnimbus.scheduling.domain.aircrew.Position;
import org.mcnimbus.scheduling.domain.aircrew.Qualification;
import org.mcnimbus.scheduling.domain.schedule.Sortie;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class AircrewCurrentQualifiedOrSupervisedSpecification
    extends AbstractSpecification<Sortie> {

  private final Set<CrewMember> unqualifiedCrewMembers = new HashSet<>();
  private final Set<CrewMember> nonCurrentCrewMembers = new HashSet<>();
  private final Set<Qualification> supervisoryQualifications = new HashSet<>();
  private CrewMemberCurrentSpecification currencySpec;

  @Override
  public boolean test(Sortie sortie) {
    supervisoryQualifications.addAll(Set.of(Qualification.E, Qualification.I));
    currencySpec = new CrewMemberCurrentSpecification(sortie.getTakeoffTime().toLocalDate());
    populateUnqualifiedCrewMembers(sortie.getAirCrew());
    populateNonCurrentCrewMembers(sortie.getAirCrew(), sortie.getTakeoffTime().toLocalDate());
    if (unqualifiedCrewMembers.isEmpty() && nonCurrentCrewMembers.isEmpty()) {
      return true;
    } else {
      Set<Position> positionsRequiringSupervision = determinePositionsRequiringSupervision();
      Set<CrewMember> supervisors = determineCrewMembersCapableOfSupervision(sortie.getAirCrew());
      return supervisors.stream()
          .anyMatch(
              supervisor ->
                  positionsRequiringSupervision.contains(supervisor.getPosition())
                      && currencySpec.test(supervisor));
    }
  }

  private Set<CrewMember> determineCrewMembersCapableOfSupervision(Set<CrewMember> aircrew) {
    Set<CrewMember> supervisors = new HashSet<>();
    aircrew.forEach(
        crewMember -> {
          if (supervisoryQualifications.contains(crewMember.getQualification())) {
            supervisors.add(crewMember);
          }
        });
    return supervisors;
  }

  private Set<Position> determinePositionsRequiringSupervision() {
    Set<Position> positionsRequiringSupervision = new HashSet<>();
    unqualifiedCrewMembers.forEach(
        crewMember -> positionsRequiringSupervision.add(crewMember.getPosition()));
    nonCurrentCrewMembers.forEach(
        crewMember -> positionsRequiringSupervision.add(crewMember.getPosition()));
    return positionsRequiringSupervision;
  }

  private void populateUnqualifiedCrewMembers(Set<CrewMember> aircrew) {
    final var qualificationSpec = new CrewMemberQualifiedSpecification();
    aircrew.forEach(
        crewMember -> {
          if (!qualificationSpec.test(crewMember)) {
            unqualifiedCrewMembers.add(crewMember);
          }
        });
  }

  private void populateNonCurrentCrewMembers(Set<CrewMember> aircrew, LocalDate sortieDate) {
    aircrew.forEach(
        crewMember -> {
          if (!currencySpec.test(crewMember)) {
            nonCurrentCrewMembers.add(crewMember);
          }
        });
  }
}
