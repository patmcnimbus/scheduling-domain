package org.mcnimbus.scheduling.domain.aircrew;

import java.util.List;
import java.util.UUID;

public interface CurrencyItemRepository {

  List<CurrencyItem> retrieveListByCrewMemberId(UUID crewMemberId);
}
