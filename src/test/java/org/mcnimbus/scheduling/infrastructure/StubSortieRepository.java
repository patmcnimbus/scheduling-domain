package org.mcnimbus.scheduling.infrastructure;

import org.mcnimbus.scheduling.domain.schedule.AirRefueling;
import org.mcnimbus.scheduling.domain.schedule.Aircraft;
import org.mcnimbus.scheduling.domain.schedule.Sortie;
import org.mcnimbus.scheduling.domain.schedule.SortieRepository;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

public class StubSortieRepository implements SortieRepository {

  private Map<UUID, Sortie> sortieMap = new HashMap<>();

  public StubSortieRepository() {
    buildStubRepository();
  }

  @Override
  public Sortie retrieveById(UUID id) {
    Sortie sortie = sortieMap.get(id);
    if (sortie == null) {
      throw new RuntimeException("sortie not found");
    }
    return sortie;
  }

  @Override
  public Set<Sortie> retrieveSortiesForDay(LocalDate date) {
    Set<Sortie> sortieSet = new HashSet<>();
    sortieMap
        .entrySet()
        .forEach(
            entry -> {
              if (entry.getValue().getTakeoffTime().getDayOfMonth() == date.getDayOfMonth()) {
                sortieSet.add(entry.getValue());
              }
            });
    if (sortieSet.isEmpty()) {
      throw new RuntimeException("no sorties found for requested day");
    }
    return sortieSet;
  }

  private void buildStubRepository() {
    Sortie s1 =
        new Sortie(
            UUID.fromString("95ab0f9e-d515-423e-83d1-5831065f60be"),
            new HashMap<>(),
            new HashSet<>(),
            new Aircraft("591464"),
            null,
            OffsetDateTime.of(
                LocalDateTime.of(2021, Month.OCTOBER, 8, 8, 00), ZoneOffset.of("+00:00")),
            OffsetDateTime.of(
                LocalDateTime.of(2021, Month.OCTOBER, 8, 13, 22), ZoneOffset.of("+00:00")),
            new BigDecimal(0),
            Integer.valueOf(80),
            false);
    sortieMap.put(s1.getId(), s1);
    Sortie s2 =
        new Sortie(
            UUID.fromString("b84f7f42-9564-44cd-b9b2-cde6939064be"),
            new HashMap<>(),
            new HashSet<>(),
            new Aircraft("600345"),
            null,
            OffsetDateTime.of(
                LocalDateTime.of(2021, Month.OCTOBER, 8, 9, 45), ZoneOffset.of("+00:00")),
            OffsetDateTime.of(
                LocalDateTime.of(2021, Month.OCTOBER, 8, 13, 57), ZoneOffset.of("+00:00")),
            new BigDecimal(0),
            Integer.valueOf(80),
            false);
    sortieMap.put(s2.getId(), s2);
    Sortie s3 =
        new Sortie(
            UUID.fromString("6d75db6e-c3e0-48cf-be1f-17f6161fcdd2"),
            new HashMap<>(),
            new HashSet<>(),
            new Aircraft("580060"),
            null,
            OffsetDateTime.of(
                LocalDateTime.of(2021, Month.OCTOBER, 8, 11, 10), ZoneOffset.of("+00:00")),
            OffsetDateTime.of(
                LocalDateTime.of(2021, Month.OCTOBER, 8, 16, 52), ZoneOffset.of("+00:00")),
            new BigDecimal(0),
            Integer.valueOf(80),
            false);
    sortieMap.put(s3.getId(), s3);
  }
}
