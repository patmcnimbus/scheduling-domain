package org.mcnimbus.scheduling.application;

import org.mcnimbus.scheduling.domain.schedule.Schedule;
import org.mcnimbus.scheduling.domain.schedule.Sortie;

import java.time.LocalDate;
import java.util.UUID;

public interface ScheduleService {

  public Schedule retrieveScheduleForDate(LocalDate scheduleDate);

  public Sortie retrieveSortieById(UUID id);

  public Sortie addSortieToSchedule(Sortie sortie);

  public Sortie updateSortieOnSchedule(Sortie sortie);

  public Boolean removeSortieFromSchedule(Sortie sortie);
}
