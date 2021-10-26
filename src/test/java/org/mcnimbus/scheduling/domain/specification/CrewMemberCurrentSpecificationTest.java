package org.mcnimbus.scheduling.domain.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mcnimbus.scheduling.domain.aircrew.CrewMember;
import org.mcnimbus.scheduling.domain.aircrew.CurrencyItem;
import org.mcnimbus.scheduling.infrastructure.StubCurrencyItemRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class CrewMemberCurrentSpecificationTest {

  @Test
  public void testCurrentCrewMember() {
    final var sortieDate = LocalDate.parse("2021-10-08");
    final var currentCrewMember = mock(CrewMember.class);
    when(currentCrewMember.getCurrencyItems()).thenReturn(Set.copyOf(getMockCurrencyItems()));
    final var crewMemberCurrentSpec = new CrewMemberCurrentSpecification(sortieDate);
    assertTrue(crewMemberCurrentSpec.test(currentCrewMember));
  }

  @Test
  public void testNonCurrentCrewMember() {
    final var sortieDate = LocalDate.parse("2021-11-11");
    final var nonCurrentCrewMember = mock(CrewMember.class);
    when(nonCurrentCrewMember.getCurrencyItems()).thenReturn(Set.copyOf(getMockCurrencyItems()));
    final var crewMemberCurrentSpec = new CrewMemberCurrentSpecification(sortieDate);
    assertFalse(crewMemberCurrentSpec.test(nonCurrentCrewMember));
  }

  private List<CurrencyItem> getMockCurrencyItems() {
    return new StubCurrencyItemRepository()
        .retrieveListByCrewMemberId(UUID.fromString("8185adbe-9949-4ea0-bdc5-1c8b82a182c6"));
  }
}
