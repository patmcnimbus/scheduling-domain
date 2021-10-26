package org.mcnimbus.scheduling.domain.specification;

import org.mcnimbus.scheduling.domain.aircrew.CrewMember;
import org.mcnimbus.scheduling.domain.aircrew.Qualification;

public class CrewMemberQualifiedSpecification extends AbstractSpecification<CrewMember> {

  @Override
  public boolean test(CrewMember crewMember) {
    return !crewMember.getQualification().equals(Qualification.U)
        && !crewMember.getQualification().equals(Qualification.F);
  }
}
