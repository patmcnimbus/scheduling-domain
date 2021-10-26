package org.mcnimbus.scheduling.domain.specification;

import java.util.List;

public class AndSpecification<T> extends AbstractSpecification<T> {

  private final List<AbstractSpecification<T>> leafComponents;

  @SafeVarargs
  AndSpecification(AbstractSpecification<T>... specifications) {
    this.leafComponents = List.of(specifications);
  }

  @Override
  public boolean test(T t) {
    return leafComponents.stream().allMatch(comp -> (comp.test(t)));
  }
}
