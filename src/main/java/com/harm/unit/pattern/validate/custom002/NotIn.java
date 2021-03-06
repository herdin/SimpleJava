package com.harm.unit.pattern.validate.custom002;

public class NotIn<T> implements ValidateStrategy<T> {
    private final T[] values;
    public NotIn(T...values) {
        this.values = values;
    }
    @Override
    public boolean test(T target) {
        boolean validateResult = true;
        for(T value : values) {
            if(value.equals(target)) {
                validateResult = false;
                break;
            }
        }
        return validateResult;
    }
}
