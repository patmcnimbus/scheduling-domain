package org.mcnimbus.scheduling.infrastructure;

import org.mcnimbus.scheduling.domain.schedule.Schedule;
import org.mcnimbus.scheduling.domain.schedule.ScheduleRepository;

import java.time.*;
import java.util.HashSet;
import java.util.UUID;

public class StubScheduleRepository implements ScheduleRepository {

  private Schedule schedule =
      new Schedule(
          UUID.fromString("4f97a979-60da-4c93-9b84-35a7046fb04e"),
          new HashSet<>(),
          OffsetDateTime.of(
              LocalDateTime.of(2021, Month.OCTOBER, 8, 00, 01), ZoneOffset.of("+00:00")),
          OffsetDateTime.of(
              LocalDateTime.of(2021, Month.OCTOBER, 8, 23, 59), ZoneOffset.of("+00:00")),
          false);

  @Override
  public Schedule retrieveById(UUID id) {
    if (!id.equals(schedule.getId())) {
      throw new RuntimeException("schedule not found");
    }
    return schedule;
  }

  @Override
  public Schedule retrieveByDate(LocalDate date) {
    if (!date.equals(schedule.getStartOfDay().toLocalDate())) {
      throw new RuntimeException("schedule not found");
    }
    return schedule;
  }
}
