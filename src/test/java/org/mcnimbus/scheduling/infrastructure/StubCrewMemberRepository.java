package org.mcnimbus.scheduling.infrastructure;

import org.mcnimbus.scheduling.domain.aircrew.*;

import java.util.*;

public class StubCrewMemberRepository implements CrewMemberRepository {

  private Map<UUID, CrewMember> crewMemberMap = new HashMap<>();
  private CurrencyItemRepository currencyItemRepository;

  public StubCrewMemberRepository() {
    buildStubRepository();
    currencyItemRepository = new StubCurrencyItemRepository();
  }

  @Override
  public CrewMember retrieveById(UUID id) {
    CrewMember crewMember = crewMemberMap.get(id);
    if (crewMember == null) {
      throw new RuntimeException("crew member not found");
    }
    return reconstituteCurrencyItems(crewMember);
  }

  private CrewMember reconstituteCurrencyItems(CrewMember crewMember) {
    List<CurrencyItem> currencyItems =
        currencyItemRepository.retrieveListByCrewMemberId(crewMember.getId());
    crewMember.getCurrencyItems().addAll(currencyItems);
    return crewMember;
  }

  private void buildStubRepository() {
    CrewMember bg =
        new CrewMember(
            getUUIDfromString("8185adbe-9949-4ea0-bdc5-1c8b82a182c6"),
            "Brian Germanator",
            Position.P,
            Qualification.M,
            new HashSet<>());
    crewMemberMap.put(bg.getId(), bg);
    CrewMember rt =
        new CrewMember(
            getUUIDfromString("fada6a9a-6a4a-4208-bcea-5f0774f0aa6c"),
            "Rachel Twokeys",
            Position.P,
            Qualification.M,
            new HashSet<>());
    crewMemberMap.put(rt.getId(), rt);
    CrewMember sb =
        new CrewMember(
            getUUIDfromString("1919bf74-9761-4271-84f5-e9066f5cda7c"),
            "Scott Burnside",
            Position.P,
            Qualification.E,
            new HashSet<>());
    crewMemberMap.put(sb.getId(), sb);
    CrewMember jr =
        new CrewMember(
            getUUIDfromString("9637760d-fd9f-4b58-8226-fc83d644bfed"),
            "Jim Roughouse",
            Position.P,
            Qualification.I,
            new HashSet<>());
    crewMemberMap.put(jr.getId(), jr);
    CrewMember bu =
        new CrewMember(
            getUUIDfromString("1975d801-7168-4e6b-840a-ee8ad7d48523"),
            "Bill Ushack",
            Position.P,
            Qualification.I,
            new HashSet<>());
    crewMemberMap.put(bu.getId(), bu);
    CrewMember bo =
        new CrewMember(
            getUUIDfromString("54bbea47-620b-4dbb-bd6c-7b7da4d2f222"),
            "Brian Ono",
            Position.P,
            Qualification.I,
            new HashSet<>());
    crewMemberMap.put(bo.getId(), bo);
    CrewMember mf =
        new CrewMember(
            getUUIDfromString("8b85f0c6-3e49-4fa2-a5e6-90594ef9b07b"),
            "Matt Fraulein",
            Position.P,
            Qualification.I,
            new HashSet<>());
    crewMemberMap.put(mf.getId(), mf);
    CrewMember sp =
        new CrewMember(
            getUUIDfromString("eac18c02-ee22-4fac-a7c7-8502fe644c29"),
            "Stacy Pittwob",
            Position.P,
            Qualification.I,
            new HashSet<>());
    crewMemberMap.put(sp.getId(), sp);
    CrewMember jn =
        new CrewMember(
            getUUIDfromString("f9f77931-949a-4385-a963-72f77a7a87a3"),
            "Johnny Noob",
            Position.P,
            Qualification.F,
            new HashSet<>());
    crewMemberMap.put(jn.getId(), jn);
    CrewMember lb =
        new CrewMember(
            getUUIDfromString("c4adb4d6-f792-4a3c-ac67-e6c8bebe102c"),
            "Lazy Bootocks",
            Position.P,
            Qualification.M,
            new HashSet<>());
    crewMemberMap.put(lb.getId(), lb);
    CrewMember ds =
        new CrewMember(
            getUUIDfromString("eccf76d4-97ba-4b30-87d7-645b9a5037d0"),
            "Dave Surfheimer",
            Position.B,
            Qualification.I,
            new HashSet<>());
    crewMemberMap.put(ds.getId(), ds);
    CrewMember tb =
        new CrewMember(
            getUUIDfromString("912d076e-2c9b-4648-a68c-1c637b423d88"),
            "Ted Butane",
            Position.B,
            Qualification.I,
            new HashSet<>());
    crewMemberMap.put(tb.getId(), tb);
    CrewMember wk =
        new CrewMember(
            getUUIDfromString("7db36c63-0015-4557-9b9d-2d139f8286ba"),
            "Warren Kerosene",
            Position.B,
            Qualification.M,
            new HashSet<>());
    crewMemberMap.put(wk.getId(), wk);
    CrewMember ln =
        new CrewMember(
            getUUIDfromString("2e0c5242-b09f-4f4a-9e05-0aeaae84d67e"),
            "Larry Nothanks",
            Position.B,
            Qualification.M,
            new HashSet<>());
    crewMemberMap.put(ln.getId(), ln);
    CrewMember bn =
        new CrewMember(
            getUUIDfromString("6f8b1779-32eb-4fb6-9b70-3acbdb6dfcd9"),
            "Bobby Nocontax",
            Position.B,
            Qualification.F,
            new HashSet<>());
    crewMemberMap.put(bn.getId(), bn);
  }

  private UUID getUUIDfromString(String input) {
    return UUID.fromString(input);
  }
}
