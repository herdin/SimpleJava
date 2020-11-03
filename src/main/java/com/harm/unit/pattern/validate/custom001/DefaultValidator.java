package com.harm.unit.pattern.validate.custom001;

import java.util.function.Predicate;

public class DefaultValidator<T> implements Predicate<T> {
    private final Predicate<T>[] validateStrategies;
    public DefaultValidator(Predicate<T>... validateStrategies) {
        this.validateStrategies = validateStrategies;
    }
    @Override
    public boolean test(T target) {
        boolean validateResult = true;
        for(Predicate<T> validateStrategy : validateStrategies) {
            if(!validateStrategy.test(target)) {
                validateResult = false;
                break;
            }
        }
        return validateResult;
    }
}
