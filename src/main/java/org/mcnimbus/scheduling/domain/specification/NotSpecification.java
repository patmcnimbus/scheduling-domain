package org.mcnimbus.scheduling.domain.specification;

import java.util.List;

public class NotSpecification<T> extends AbstractSpecification<T> {

  private final AbstractSpecification<T> component;

  NotSpecification(AbstractSpecification<T> specification) {
    this.component = specification;
  }

  @Override
  public boolean test(T t) {
    return !(component.test(t));
  }
}
