package org.mcnimbus.scheduling.domain.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mcnimbus.scheduling.domain.specification.AircraftDoNotDoubleTurnSpecification;
import org.mcnimbus.scheduling.domain.specification.AircraftHasSufficientTurnTimeSpecification;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

// aggregate root
@AllArgsConstructor
@Getter
public class Schedule {

  private UUID id;
  private Set<Sortie> sorties;
  private OffsetDateTime startOfDay;
  private OffsetDateTime endOfDay;
  private Boolean aircraftAreDeconflicted;

  public Sortie addNewSortie(Sortie sortie) {
    if (sorties.stream()
        .anyMatch(existingSortie -> existingSortie.getId().equals(sortie.getId()))) {
      throw new RuntimeException("a matching sortie already exists...it can't be added");
    }
    sorties.add(sortie);
    validateSchedule();
    return sortie;
  }

  public Sortie updateSortie(Sortie sortie) {
    if (sortie.getId() == null) {
      throw new RuntimeException("this sortie does not have an id...this is the wrong method");
    }
    if (!sorties.stream()
        .anyMatch(existingSortie -> existingSortie.getId().equals(sortie.getId()))) {
      throw new RuntimeException("no matching sortie exists...it can't be updated");
    }
    sorties.add(sortie);
    validateSchedule();
    return sortie;
  }

  public void deleteSortie(Sortie sortie) {
    if (sortie.getId() == null) {
      throw new RuntimeException("this sortie does not have an id...it can't be removed");
    }
    if (!sorties.stream()
        .anyMatch(existingSortie -> existingSortie.getId().equals(sortie.getId()))) {
      throw new RuntimeException("no matching sortie exists...it can't be removed");
    }
    sorties.remove(sortie);
    validateSchedule();
  }

  private void validateSchedule() {
    checkAircraftDeconfliction();
    markInvalidSorties();
  }

  private void checkAircraftDeconfliction() {
    var deconflictionSpec =
        new AircraftDoNotDoubleTurnSpecification()
            .or(new AircraftHasSufficientTurnTimeSpecification());
    aircraftAreDeconflicted = deconflictionSpec.test(this);
  }

  private void markInvalidSorties() {
    sorties.forEach(Sortie::markOwnValidationStatus);
  }
}
