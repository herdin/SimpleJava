package com.harm.unit.pattern.validate.custom002;

public class Max implements ValidateStrategy<Integer> {
    private final int max;
    public Max(int max) {
        this.max = max;
    }
    @Override
    public boolean test(Integer target) {
        return target <= max;
    }
}
