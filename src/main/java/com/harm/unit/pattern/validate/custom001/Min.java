package com.harm.unit.pattern.validate.custom001;

import java.util.function.Predicate;

public class Min implements Predicate<Integer> {
    private final int min;
    public Min(int min) {
        this.min = min;
    }
    @Override
    public boolean test(Integer target) {
        return min <= target;
    }
}
