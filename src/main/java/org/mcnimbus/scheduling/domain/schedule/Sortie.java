package org.mcnimbus.scheduling.domain.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mcnimbus.scheduling.domain.aircrew.CrewMember;
import org.mcnimbus.scheduling.domain.aircrew.Position;
import org.mcnimbus.scheduling.domain.specification.AircrewCurrentQualifiedOrSupervisedSpecification;
import org.mcnimbus.scheduling.domain.specification.CrewComplementMatchesSortieTypeSpecification;
import org.mcnimbus.scheduling.domain.specification.FuelLoadSpecification;
import org.mcnimbus.scheduling.domain.specification.MinimumCrewComplementSatisfiedSpecification;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

// aggregate
@Getter
@AllArgsConstructor
public class Sortie {

  private UUID id;
  private Map<Position, SortieType> sortieTypes;
  private Set<CrewMember> airCrew;
  private Aircraft aircraft;
  private AirRefueling airRefueling;
  private OffsetDateTime takeoffTime;
  private OffsetDateTime landTime;
  private BigDecimal duration;
  private Integer fuelLoad;
  private Boolean allValidationsPass;

  public Sortie(AirRefueling airRefueling) {
    this.id = UUID.randomUUID();
    this.airRefueling = airRefueling;
    this.airCrew = new HashSet<>();
    this.sortieTypes = new HashMap<>();
    this.allValidationsPass = false;
  }

  public void updateSortieType(Position position, SortieType sortieType) {
    sortieTypes.put(position, sortieType);
  }

  public void addCrewMember(CrewMember crewMember) {
    airCrew.add(crewMember);
  }

  public void removeCrewMember(CrewMember crewMember) {
    airCrew.remove(crewMember);
  }

  public void updateAircraft(Aircraft aircraft) {
    this.aircraft = aircraft;
  }

  public void updateAirRefueling(AirRefueling airRefueling) {
    this.airRefueling = airRefueling;
  }

  public void updateTakeoffTime(OffsetDateTime takeoffTime) {
    this.takeoffTime = takeoffTime;
    calculateDuration();
  }

  public void updateLandTime(OffsetDateTime landTime) {
    this.landTime = landTime;
    calculateDuration();
  }

  public void updateFuelLoad(Integer fuelLoad) {
    this.fuelLoad = fuelLoad;
  }

  private void calculateDuration() {
    if (takeoffTime != null && landTime != null && landTime.isAfter(takeoffTime)) {
      BigDecimal durationInMinutes =
          BigDecimal.valueOf(ChronoUnit.MINUTES.between(takeoffTime, landTime));
      BigDecimal hours =
          durationInMinutes
              .divide(new BigDecimal("60"), 0, RoundingMode.DOWN)
              .setScale(0, RoundingMode.DOWN);
      BigDecimal remainingMinutes =
          durationInMinutes.subtract(new BigDecimal("60").multiply(hours));
      BigDecimal tenthsOfAnHour =
          remainingMinutes.compareTo(BigDecimal.ZERO) == 0
              ? BigDecimal.ZERO
              : remainingMinutes.divide(new BigDecimal("6"), 0, RoundingMode.HALF_EVEN);
      duration = new BigDecimal(hours.toString() + "." + tenthsOfAnHour.toString());
    } else {
      duration = new BigDecimal("0.0");
    }
  }

  public void markOwnValidationStatus() {
    final var minCrewComplement = new MinimumCrewComplementSatisfiedSpecification();
    final var fuelLoadChecks = new FuelLoadSpecification();
    final var crewMatchesSortieType = new CrewComplementMatchesSortieTypeSpecification();
    final var crewCurrencyAndQualificationCheck =
        new AircrewCurrentQualifiedOrSupervisedSpecification();
    allValidationsPass =
        airRefuelingFitsInTimeWindow()
            && minCrewComplement.test(this)
            && fuelLoadChecks.test(this)
            && crewMatchesSortieType.test(this)
            && crewCurrencyAndQualificationCheck.test(this);
  }

  private Boolean airRefuelingFitsInTimeWindow() {
    if (airRefueling != null && takeoffTime != null && landTime != null) {
      return airRefueling.getDelayStartTime().isAfter(takeoffTime)
          && airRefueling.getRefuelingEndTime().isBefore(landTime);
    }
    return true;
  }
}
