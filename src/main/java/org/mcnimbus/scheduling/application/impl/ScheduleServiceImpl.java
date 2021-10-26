package org.mcnimbus.scheduling.application.impl;

import lombok.AllArgsConstructor;
import org.mcnimbus.scheduling.application.ScheduleService;
import org.mcnimbus.scheduling.domain.schedule.Schedule;
import org.mcnimbus.scheduling.domain.schedule.ScheduleRepository;
import org.mcnimbus.scheduling.domain.schedule.Sortie;
import org.mcnimbus.scheduling.domain.schedule.SortieRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

  private ScheduleRepository scheduleRepository;
  private SortieRepository sortieRepository;

  public Schedule retrieveScheduleForDate(LocalDate scheduleDate) {
    return scheduleRepository.retrieveByDate(scheduleDate);
  }

  public Sortie retrieveSortieById(UUID id) {
    return sortieRepository.retrieveById(id);
  }

  public Sortie addSortieToSchedule(Sortie sortie) {
    Schedule schedule = scheduleRepository.retrieveByDate(LocalDate.from(sortie.getTakeoffTime()));
    if (schedule.getSorties().contains(sortie)) {
      throw new RuntimeException("sortie already exists...it cannot be added");
    }
    schedule.addNewSortie(sortie);
    // you would persist the schedule here
    return sortie;
  }

  public Sortie updateSortieOnSchedule(Sortie sortie) {
    Schedule schedule = scheduleRepository.retrieveByDate(LocalDate.from(sortie.getTakeoffTime()));
    if (!schedule.getSorties().contains(sortie)) {
      throw new RuntimeException("sortie is not on schedule...it cannot be updated");
    }
    schedule.updateSortie(sortie);
    // you would persist the schedule here
    return sortie;
  }

  public Boolean removeSortieFromSchedule(Sortie sortie) {
    Schedule schedule = scheduleRepository.retrieveByDate(LocalDate.from(sortie.getTakeoffTime()));
    if (!schedule.getSorties().contains(sortie)) {
      throw new RuntimeException("sortie is not on schedule...it cannot be deleted");
    }
    schedule.deleteSortie(sortie);
    // you would persist the schedule here
    return true;
  }
}
