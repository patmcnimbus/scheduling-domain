package org.mcnimbus.scheduling.domain.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mcnimbus.scheduling.domain.schedule.Sortie;
import org.mcnimbus.scheduling.utility.TimeUtility;

import java.time.LocalDate;

public class FuelLoadSpecificationTest {

  private final LocalDate scheduleDate = LocalDate.parse("2021-10-08");
  private Sortie testSortie = mockTestSortie();

  @BeforeEach
  public void setup() {
    testSortie = mockTestSortie();
  }

  @Test
  public void testSpecValidFuelLoad() {
    testSortie.updateLandTime(TimeUtility.createDateTime(12, 0));
    testSortie.updateFuelLoad(80000);
    final var spec = new FuelLoadSpecification();
    assertTrue(spec.test(testSortie));
  }

  @Test
  public void testSpecInvalidFuelLoad() {
    testSortie.updateLandTime(TimeUtility.createDateTime(12, 0));
    testSortie.updateFuelLoad(65000);
    final var spec = new FuelLoadSpecification();
    assertFalse(spec.test(testSortie));
  }

  private Sortie mockTestSortie() {
    Sortie testSortie = new Sortie(null);
    testSortie.updateTakeoffTime(TimeUtility.createDateTime(8, 0));
    return testSortie;
  }
}
