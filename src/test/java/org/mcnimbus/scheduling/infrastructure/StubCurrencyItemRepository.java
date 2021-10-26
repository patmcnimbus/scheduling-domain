package org.mcnimbus.scheduling.infrastructure;

import org.mcnimbus.scheduling.domain.aircrew.CurrencyItem;
import org.mcnimbus.scheduling.domain.aircrew.CurrencyItemRepository;

import java.time.LocalDate;
import java.util.*;

public class StubCurrencyItemRepository implements CurrencyItemRepository {

  private Map<UUID, List<CurrencyItem>> currencyItemMap = new HashMap<>();

  public StubCurrencyItemRepository() {
    buildStubRepository();
  }

  @Override
  public List<CurrencyItem> retrieveListByCrewMemberId(UUID crewMemberId) {
    List<CurrencyItem> list = currencyItemMap.get(crewMemberId);
    if (list == null) {
      throw new RuntimeException("no currency item list found");
    }
    return list;
  }

  private void buildStubRepository() {
    List<CurrencyItem> l1 = new ArrayList<>();
    CurrencyItem ci1 =
        new CurrencyItem(
            "M010",
            UUID.fromString("8185adbe-9949-4ea0-bdc5-1c8b82a182c6"),
            "Sortie",
            3,
            LocalDate.parse("2021-09-20"),
            30);
    l1.add(ci1);
    CurrencyItem ci2 =
        new CurrencyItem(
            "L011",
            UUID.fromString("8185adbe-9949-4ea0-bdc5-1c8b82a182c6"),
            "Landing",
            3,
            LocalDate.parse("2021-09-12"),
            30);
    l1.add(ci2);
    CurrencyItem ci3 =
        new CurrencyItem(
            "A080",
            UUID.fromString("8185adbe-9949-4ea0-bdc5-1c8b82a182c6"),
            "Rendezvous",
            3,
            LocalDate.parse("2021-09-02"),
            45);
    l1.add(ci3);
    currencyItemMap.put(ci1.getCrewMemberId(), l1);
    List<CurrencyItem> l2 = new ArrayList<>();
    CurrencyItem ci4 =
        new CurrencyItem(
            "M010",
            UUID.fromString("fada6a9a-6a4a-4208-bcea-5f0774f0aa6c"),
            "Sortie",
            3,
            LocalDate.parse("2021-09-12"),
            30);
    l2.add(ci4);
    CurrencyItem ci5 =
        new CurrencyItem(
            "L011",
            UUID.fromString("fada6a9a-6a4a-4208-bcea-5f0774f0aa6c"),
            "Landing",
            3,
            LocalDate.parse("2021-09-10"),
            30);
    l2.add(ci5);
    CurrencyItem ci6 =
        new CurrencyItem(
            "A080",
            UUID.fromString("fada6a9a-6a4a-4208-bcea-5f0774f0aa6c"),
            "Rendezvous",
            3,
            LocalDate.parse("2021-09-25"),
            45);
    l2.add(ci6);
    currencyItemMap.put(ci6.getCrewMemberId(), l2);
    List<CurrencyItem> l3 = new ArrayList<>();
    CurrencyItem ci7 =
        new CurrencyItem(
            "M010",
            UUID.fromString("1919bf74-9761-4271-84f5-e9066f5cda7c"),
            "Sortie",
            3,
            LocalDate.parse("2021-09-17"),
            60);
    l3.add(ci7);
    CurrencyItem ci8 =
        new CurrencyItem(
            "L011",
            UUID.fromString("1919bf74-9761-4271-84f5-e9066f5cda7c"),
            "Landing",
            3,
            LocalDate.parse("2021-09-17"),
            60);
    l3.add(ci8);
    CurrencyItem ci9 =
        new CurrencyItem(
            "A080",
            UUID.fromString("1919bf74-9761-4271-84f5-e9066f5cda7c"),
            "Rendezvous",
            3,
            LocalDate.parse("2021-09-15"),
            90);
    l3.add(ci9);
    currencyItemMap.put(ci9.getCrewMemberId(), l3);
    List<CurrencyItem> l4 = new ArrayList<>();
    CurrencyItem ci10 =
        new CurrencyItem(
            "M010",
            UUID.fromString("9637760d-fd9f-4b58-8226-fc83d644bfed"),
            "Sortie",
            3,
            LocalDate.parse("2021-09-12"),
            60);
    l4.add(ci10);
    CurrencyItem ci11 =
        new CurrencyItem(
            "L011",
            UUID.fromString("9637760d-fd9f-4b58-8226-fc83d644bfed"),
            "Landing",
            3,
            LocalDate.parse("2021-09-12"),
            60);
    l4.add(ci11);
    CurrencyItem ci12 =
        new CurrencyItem(
            "A080",
            UUID.fromString("9637760d-fd9f-4b58-8226-fc83d644bfed"),
            "Rendezvous",
            3,
            LocalDate.parse("2021-09-12"),
            90);
    l4.add(ci12);
    currencyItemMap.put(ci12.getCrewMemberId(), l4);
    List<CurrencyItem> l5 = new ArrayList<>();
    CurrencyItem ci13 =
        new CurrencyItem(
            "M010",
            UUID.fromString("1975d801-7168-4e6b-840a-ee8ad7d48523"),
            "Sortie",
            3,
            LocalDate.parse("2021-09-19"),
            60);
    l5.add(ci13);
    CurrencyItem ci14 =
        new CurrencyItem(
            "L011",
            UUID.fromString("1975d801-7168-4e6b-840a-ee8ad7d48523"),
            "Landing",
            3,
            LocalDate.parse("2021-09-18"),
            60);
    l5.add(ci14);
    CurrencyItem ci15 =
        new CurrencyItem(
            "A080",
            UUID.fromString("1975d801-7168-4e6b-840a-ee8ad7d48523"),
            "Rendezvous",
            3,
            LocalDate.parse("2021-09-17"),
            90);
    l5.add(ci15);
    currencyItemMap.put(ci15.getCrewMemberId(), l5);
    List<CurrencyItem> l6 = new ArrayList<>();
    CurrencyItem ci16 =
        new CurrencyItem(
            "M010",
            UUID.fromString("54bbea47-620b-4dbb-bd6c-7b7da4d2f222"),
            "Sortie",
            3,
            LocalDate.parse("2021-09-12"),
            60);
    l6.add(ci16);
    CurrencyItem ci17 =
        new CurrencyItem(
            "L011",
            UUID.fromString("54bbea47-620b-4dbb-bd6c-7b7da4d2f222"),
            "Landing",
            3,
            LocalDate.parse("2021-09-12"),
            60);
    l6.add(ci17);
    CurrencyItem ci18 =
        new CurrencyItem(
            "A080",
            UUID.fromString("54bbea47-620b-4dbb-bd6c-7b7da4d2f222"),
            "Rendezvous",
            3,
            LocalDate.parse("2021-09-12"),
            90);
    l6.add(ci18);
    currencyItemMap.put(ci18.getCrewMemberId(), l6);
    List<CurrencyItem> l7 = new ArrayList<>();
    CurrencyItem ci19 =
        new CurrencyItem(
            "M010",
            UUID.fromString("8b85f0c6-3e49-4fa2-a5e6-90594ef9b07b"),
            "Sortie",
            3,
            LocalDate.parse("2021-08-29"),
            60);
    l7.add(ci19);
    CurrencyItem ci20 =
        new CurrencyItem(
            "L011",
            UUID.fromString("8b85f0c6-3e49-4fa2-a5e6-90594ef9b07b"),
            "Landing",
            3,
            LocalDate.parse("2021-08-29"),
            60);
    l7.add(ci20);
    CurrencyItem ci21 =
        new CurrencyItem(
            "A080",
            UUID.fromString("8b85f0c6-3e49-4fa2-a5e6-90594ef9b07b"),
            "Rendezvous",
            3,
            LocalDate.parse("2021-08-29"),
            90);
    l7.add(ci21);
    currencyItemMap.put(ci21.getCrewMemberId(), l7);
    List<CurrencyItem> l8 = new ArrayList<>();
    CurrencyItem ci22 =
        new CurrencyItem(
            "M010",
            UUID.fromString("eac18c02-ee22-4fac-a7c7-8502fe644c29"),
            "Sortie",
            3,
            LocalDate.parse("2021-10-01"),
            60);
    l8.add(ci22);
    CurrencyItem ci23 =
        new CurrencyItem(
            "L011",
            UUID.fromString("eac18c02-ee22-4fac-a7c7-8502fe644c29"),
            "Landing",
            3,
            LocalDate.parse("2021-10-01"),
            60);
    l8.add(ci23);
    CurrencyItem ci24 =
        new CurrencyItem(
            "A080",
            UUID.fromString("eac18c02-ee22-4fac-a7c7-8502fe644c29"),
            "Rendezvous",
            3,
            LocalDate.parse("2021-10-01"),
            90);
    l8.add(ci24);
    currencyItemMap.put(ci24.getCrewMemberId(), l8);
    // pilot cutoff
    List<CurrencyItem> l9 = new ArrayList<>();
    CurrencyItem ci25 =
        new CurrencyItem(
            "M010",
            UUID.fromString("eccf76d4-97ba-4b30-87d7-645b9a5037d0"),
            "Sortie",
            3,
            LocalDate.parse("2021-09-12"),
            60);
    l9.add(ci25);
    CurrencyItem ci26 =
        new CurrencyItem(
            "A011",
            UUID.fromString("eccf76d4-97ba-4b30-87d7-645b9a5037d0"),
            "Contact",
            3,
            LocalDate.parse("2021-09-12"),
            60);
    l9.add(ci26);
    CurrencyItem ci27 =
        new CurrencyItem(
            "E099",
            UUID.fromString("eccf76d4-97ba-4b30-87d7-645b9a5037d0"),
            "Manual Gear Extension",
            3,
            LocalDate.parse("2021-09-12"),
            120);
    l9.add(ci27);
    currencyItemMap.put(ci27.getCrewMemberId(), l9);
    List<CurrencyItem> l10 = new ArrayList<>();
    CurrencyItem ci28 =
        new CurrencyItem(
            "M010",
            UUID.fromString("912d076e-2c9b-4648-a68c-1c637b423d88"),
            "Sortie",
            3,
            LocalDate.parse("2021-09-21"),
            60);
    l10.add(ci28);
    CurrencyItem ci29 =
        new CurrencyItem(
            "A011",
            UUID.fromString("912d076e-2c9b-4648-a68c-1c637b423d88"),
            "Contact",
            3,
            LocalDate.parse("2021-09-21"),
            60);
    l10.add(ci29);
    CurrencyItem ci30 =
        new CurrencyItem(
            "E099",
            UUID.fromString("912d076e-2c9b-4648-a68c-1c637b423d88"),
            "Manual Gear Extension",
            3,
            LocalDate.parse("2021-09-21"),
            120);
    l10.add(ci30);
    currencyItemMap.put(ci30.getCrewMemberId(), l10);
    List<CurrencyItem> l11 = new ArrayList<>();
    CurrencyItem ci31 =
        new CurrencyItem(
            "M010",
            UUID.fromString("7db36c63-0015-4557-9b9d-2d139f8286ba"),
            "Sortie",
            3,
            LocalDate.parse("2021-09-29"),
            60);
    l11.add(ci31);
    CurrencyItem ci32 =
        new CurrencyItem(
            "A011",
            UUID.fromString("7db36c63-0015-4557-9b9d-2d139f8286ba"),
            "Contact",
            3,
            LocalDate.parse("2021-09-29"),
            30);
    l11.add(ci32);
    CurrencyItem ci33 =
        new CurrencyItem(
            "E099",
            UUID.fromString("7db36c63-0015-4557-9b9d-2d139f8286ba"),
            "Manual Gear Extension",
            3,
            LocalDate.parse("2021-09-29"),
            90);
    l11.add(ci33);
    currencyItemMap.put(ci33.getCrewMemberId(), l11);
    List<CurrencyItem> l12 = new ArrayList<>();
    CurrencyItem ci34 =
        new CurrencyItem(
            "M010",
            UUID.fromString("2e0c5242-b09f-4f4a-9e05-0aeaae84d67e"),
            "Sortie",
            3,
            LocalDate.parse("2021-09-15"),
            60);
    l12.add(ci34);
    CurrencyItem ci35 =
        new CurrencyItem(
            "A011",
            UUID.fromString("2e0c5242-b09f-4f4a-9e05-0aeaae84d67e"),
            "Contact",
            3,
            LocalDate.parse("2021-09-15"),
            30);
    l12.add(ci35);
    CurrencyItem ci36 =
        new CurrencyItem(
            "E099",
            UUID.fromString("2e0c5242-b09f-4f4a-9e05-0aeaae84d67e"),
            "Manual Gear Extension",
            3,
            LocalDate.parse("2021-09-15"),
            90);
    l12.add(ci36);
    currencyItemMap.put(ci36.getCrewMemberId(), l12);

    // extras for currency and qual validation
    List<CurrencyItem> l13 = new ArrayList<>();
    CurrencyItem ci37 =
        new CurrencyItem(
            "M010",
            UUID.fromString("6f8b1779-32eb-4fb6-9b70-3acbdb6dfcd9"),
            "Sortie",
            3,
            LocalDate.parse("2021-09-15"),
            60);
    l13.add(ci37);
    CurrencyItem ci38 =
        new CurrencyItem(
            "A011",
            UUID.fromString("6f8b1779-32eb-4fb6-9b70-3acbdb6dfcd9"),
            "Contact",
            3,
            LocalDate.parse("2021-09-15"),
            30);
    l13.add(ci38);
    CurrencyItem ci39 =
        new CurrencyItem(
            "E099",
            UUID.fromString("6f8b1779-32eb-4fb6-9b70-3acbdb6dfcd9"),
            "Manual Gear Extension",
            3,
            LocalDate.parse("2021-09-15"),
            90);
    l13.add(ci39);
    currencyItemMap.put(ci39.getCrewMemberId(), l13);
    List<CurrencyItem> l14 = new ArrayList<>();
    CurrencyItem ci40 =
        new CurrencyItem(
            "M010",
            UUID.fromString("f9f77931-949a-4385-a963-72f77a7a87a3"),
            "Sortie",
            3,
            LocalDate.parse("2021-10-01"),
            60);
    l14.add(ci40);
    CurrencyItem ci41 =
        new CurrencyItem(
            "L011",
            UUID.fromString("f9f77931-949a-4385-a963-72f77a7a87a3"),
            "Landing",
            3,
            LocalDate.parse("2021-10-01"),
            60);
    l14.add(ci41);
    CurrencyItem ci42 =
        new CurrencyItem(
            "A080",
            UUID.fromString("f9f77931-949a-4385-a963-72f77a7a87a3"),
            "Rendezvous",
            3,
            LocalDate.parse("2021-10-01"),
            90);
    l14.add(ci42);
    currencyItemMap.put(ci42.getCrewMemberId(), l14);
    List<CurrencyItem> l15 = new ArrayList<>();
    CurrencyItem ci43 =
        new CurrencyItem(
            "M010",
            UUID.fromString("c4adb4d6-f792-4a3c-ac67-e6c8bebe102c"),
            "Sortie",
            3,
            LocalDate.parse("2021-07-15"),
            60);
    l15.add(ci43);
    CurrencyItem ci44 =
        new CurrencyItem(
            "L011",
            UUID.fromString("c4adb4d6-f792-4a3c-ac67-e6c8bebe102c"),
            "Landing",
            3,
            LocalDate.parse("2021-07-15"),
            60);
    l15.add(ci44);
    CurrencyItem ci45 =
        new CurrencyItem(
            "A080",
            UUID.fromString("c4adb4d6-f792-4a3c-ac67-e6c8bebe102c"),
            "Rendezvous",
            3,
            LocalDate.parse("2021-05-01"),
            90);
    l15.add(ci45);
    currencyItemMap.put(ci45.getCrewMemberId(), l15);
  }
}
