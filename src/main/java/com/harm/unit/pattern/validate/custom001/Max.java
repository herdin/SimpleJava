package com.harm.unit.pattern.validate.custom001;

import java.util.function.Predicate;

public class Max implements Predicate<Integer> {
    private final int max;
    public Max(int max) {
        this.max = max;
    }
    @Override
    public boolean test(Integer target) {
        return target <= max;
    }
}
