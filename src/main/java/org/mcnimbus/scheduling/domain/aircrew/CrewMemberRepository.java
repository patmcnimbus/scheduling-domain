package org.mcnimbus.scheduling.domain.aircrew;

import java.util.UUID;

public interface CrewMemberRepository {

  CrewMember retrieveById(UUID id);
}
