package com.harm.unit.pattern.validate.custom002;

public class Min implements ValidateStrategy<Integer> {
    private final int min;
    public Min(int min) {
        this.min = min;
    }
    @Override
    public boolean test(Integer target) {
        return min <= target;
    }
}
