package org.mcnimbus.scheduling.domain.specification;

import org.mcnimbus.scheduling.domain.schedule.Sortie;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class FuelLoadSpecification extends AbstractSpecification<Sortie> {

  @Override
  public boolean test(Sortie sortie) {
    return sortie.getFuelLoad().compareTo(targetFuelLoad(sortie.getDuration())) >= 0;
  }

  private Integer targetFuelLoad(BigDecimal duration) {
    BigDecimal rawTargetFuelLoad =
        duration
            .subtract(new BigDecimal("1.0"))
            .multiply(new BigDecimal("10000"))
            .add(new BigDecimal("12000"))
            .add(new BigDecimal("25000"));
    return 5000
        * (roundUp(rawTargetFuelLoad.divide(new BigDecimal("5000"), 1, RoundingMode.HALF_UP)))
            .intValue();
  }

  private Integer roundUp(BigDecimal number) {
    return number.round(new MathContext(2, RoundingMode.UP)).intValue();
  }
}
