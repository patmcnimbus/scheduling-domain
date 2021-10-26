package org.mcnimbus.scheduling.domain.specification;

import org.mcnimbus.scheduling.domain.aircrew.CrewMember;

import java.time.LocalDate;

public class CrewMemberCurrentSpecification extends AbstractSpecification<CrewMember> {

  private final LocalDate sortieDate;

  public CrewMemberCurrentSpecification(LocalDate sortieDate) {
    this.sortieDate = sortieDate;
  }

  @Override
  public boolean test(CrewMember crewMember) {
    return crewMember.getCurrencyItems().stream()
        .allMatch(
            currencyItem ->
                currencyItem
                    .getDateLastAccomplished()
                    .isAfter(
                        sortieDate.minusDays(
                            currencyItem.getCurrencyRequirementInDays().longValue())));
  }
}
