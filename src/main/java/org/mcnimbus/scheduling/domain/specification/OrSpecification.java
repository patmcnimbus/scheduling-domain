package org.mcnimbus.scheduling.domain.specification;

import java.util.List;

public class OrSpecification<T> extends AbstractSpecification<T> {

  private final List<AbstractSpecification<T>> leafComponents;

  @SafeVarargs
  OrSpecification(AbstractSpecification<T>... specifications) {
    this.leafComponents = List.of(specifications);
  }

  @Override
  public boolean test(T t) {
    return leafComponents.stream().anyMatch(comp -> comp.test(t));
  }
}
