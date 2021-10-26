package org.mcnimbus.scheduling.domain.schedule;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public interface SortieRepository {

  Sortie retrieveById(UUID id);

  Set<Sortie> retrieveSortiesForDay(LocalDate date);
}
