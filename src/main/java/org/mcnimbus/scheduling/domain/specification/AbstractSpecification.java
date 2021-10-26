package org.mcnimbus.scheduling.domain.specification;

import java.util.function.Predicate;

public abstract class AbstractSpecification<T> implements Predicate<T> {

  public AndSpecification<T> and(AbstractSpecification<T> other) {
    return new AndSpecification<>(this, other);
  }

  public OrSpecification<T> or(AbstractSpecification<T> other) {
    return new OrSpecification<>(this, other);
  }

  public NotSpecification<T> not() {
    return new NotSpecification<>(this);
  }
}
