package org.mcnimbus.scheduling.domain.schedule;

import java.time.LocalDate;
import java.util.UUID;

public interface ScheduleRepository {

  Schedule retrieveById(UUID id);

  Schedule retrieveByDate(LocalDate date);
}
