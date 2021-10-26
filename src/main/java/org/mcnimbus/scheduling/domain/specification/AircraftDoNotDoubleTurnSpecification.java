package org.mcnimbus.scheduling.domain.specification;

import org.mcnimbus.scheduling.domain.schedule.Schedule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AircraftDoNotDoubleTurnSpecification extends AbstractSpecification<Schedule> {

  @Override
  public boolean test(Schedule schedule) {
    List<String> tailList = new ArrayList<>();

    schedule.getSorties().stream()
        .forEach(
            sortie -> {
              if (sortie.getAircraft() == null) return;
              tailList.add(sortie.getAircraft().getTailNumber());
            });
    if (tailList.isEmpty()) {
      return false;
    }
    Set<String> tailSet = new HashSet<>();
    tailSet.addAll(tailList);
    if (tailList.size() == tailSet.size()) {
      return true;
    }
    return false;
  }
}
