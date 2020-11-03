package com.harm.unit.pattern.validate.custom002;

public class NotNull<T> implements ValidateStrategy<T> {
    @Override
    public boolean test(T target) {
        return null != target;
    }
}
