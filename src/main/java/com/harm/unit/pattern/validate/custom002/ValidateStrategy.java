package com.harm.unit.pattern.validate.custom002;

import java.util.function.Predicate;

public interface ValidateStrategy<T> extends Predicate<T> {
    default String getName() {
        return this.getClass().getSimpleName();
    }
}
