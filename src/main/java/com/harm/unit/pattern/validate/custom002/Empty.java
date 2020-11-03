package com.harm.unit.pattern.validate.custom002;

import java.util.function.Predicate;

public class Empty implements ValidateStrategy<String> {
    @Override
    public boolean test(String target) {
        return "".equals(target);
    }

}
