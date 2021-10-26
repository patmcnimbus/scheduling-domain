package org.mcnimbus.scheduling.domain.specification;

import org.junit.jupiter.api.Test;
import org.mcnimbus.scheduling.domain.aircrew.CrewMember;
import org.mcnimbus.scheduling.domain.aircrew.Qualification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CrewMemberQualifiedSpecificationTest {

  @Test
  public void test() {
    final var qualifiedCrewMember = mock(CrewMember.class);
    when(qualifiedCrewMember.getQualification()).thenReturn(Qualification.M);

    final var unqualifiedCrewMember = mock(CrewMember.class);
    when(unqualifiedCrewMember.getQualification()).thenReturn(Qualification.U);

    final var qualifiedCrewMemberSpecification = new CrewMemberQualifiedSpecification();
    assertTrue(qualifiedCrewMemberSpecification.test(qualifiedCrewMember));
    assertFalse(qualifiedCrewMemberSpecification.test(unqualifiedCrewMember));
  }
}
