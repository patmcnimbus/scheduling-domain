package org.mcnimbus.scheduling.domain.specification;

import org.mcnimbus.scheduling.domain.schedule.Aircraft;
import org.mcnimbus.scheduling.domain.schedule.Schedule;
import org.mcnimbus.scheduling.domain.schedule.Sortie;

import java.util.*;
import java.util.stream.Collectors;

public class AircraftHasSufficientTurnTimeSpecification extends AbstractSpecification<Schedule> {

  @Override
  public boolean test(Schedule schedule) {
    List<Aircraft> doubleTurningAircraft = findDoubleTurningAircraft(schedule);
    if (doubleTurningAircraft.isEmpty()) {
      return true;
    }
    Map<String, List<Sortie>> sortiesByTailNumberInTimeOrder =
        sortiesByTailNumberInTimeOrder(doubleTurningAircraft, schedule);
    return sortiesByTailNumberInTimeOrder.entrySet().stream()
        .allMatch(
            entry -> {
              return sortiePairHasRequiredTurnTime(entry.getValue());
            });
  }

  private List<Aircraft> findDoubleTurningAircraft(Schedule schedule) {
    List<Aircraft> allAircraft = new ArrayList<>();
    List<Aircraft> duplicates = new ArrayList<>();
    schedule.getSorties().forEach(sortie -> allAircraft.add(sortie.getAircraft()));
    Set<Aircraft> uniqueAircraft = new HashSet<>();
    allAircraft.forEach(
        aircraft -> {
          if (!uniqueAircraft.add(aircraft)) {
            duplicates.add(aircraft);
          }
        });
    return duplicates;
  }

  private Map<String, List<Sortie>> sortiesByTailNumberInTimeOrder(
      List<Aircraft> doubleTurningAircraft, Schedule schedule) {
    Map<String, List<Sortie>> sortiesByTailNumberInTimeOrder = new HashMap<>();
    List<Sortie> sortieList = new ArrayList<>();
    sortieList =
        schedule.getSorties().stream()
            .filter(sortie -> doubleTurningAircraft.contains(sortie.getAircraft()))
            .collect(Collectors.toList());
    Comparator<Sortie> byTailNumber =
        (Sortie s1, Sortie s2) ->
            s1.getAircraft().getTailNumber().compareTo(s2.getAircraft().getTailNumber());
    sortieList.stream().sorted(byTailNumber).collect(Collectors.toList());
    sortieList.stream()
        .forEach(
            sortie -> {
              if (sortiesByTailNumberInTimeOrder.containsKey(
                  sortie.getAircraft().getTailNumber())) {
                sortiesByTailNumberInTimeOrder
                    .get(sortie.getAircraft().getTailNumber())
                    .add(sortie);
              } else {
                List<Sortie> sortiesInTimeOrder = new ArrayList<>();
                sortiesInTimeOrder.add(sortie);
                sortiesByTailNumberInTimeOrder.put(
                    sortie.getAircraft().getTailNumber(), sortiesInTimeOrder);
              }
            });
    Comparator<Sortie> byTakeoffTime =
        (Sortie s1, Sortie s2) -> s1.getTakeoffTime().compareTo(s2.getTakeoffTime());
    sortiesByTailNumberInTimeOrder.entrySet().stream()
        .map(Map.Entry::getValue)
        .forEach(sorties -> sorties.sort(byTakeoffTime));
    return sortiesByTailNumberInTimeOrder;
  }

  private Boolean sortiePairHasRequiredTurnTime(List<Sortie> sortiePair) {
    if (sortiePair
        .get(0)
        .getLandTime()
        .plusHours(4)
        .plusMinutes(15)
        .isAfter(sortiePair.get(1).getTakeoffTime())) {
      return false;
    }
    return true;
  }
}
