package com.harm.unit.pattern.validate.custom001;

import java.util.function.Predicate;

public class In<T> implements Predicate<T> {
    private final T[] values;
    public In(T...values) {
        this.values = values;
    }
    @Override
    public boolean test(T target) {
        boolean validateResult = false;
        for(T value : values) {
            if(value.equals(target)) {
                validateResult = true;
                break;
            }
        }
        return validateResult;
    }
}
