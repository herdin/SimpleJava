package com.harm.unit.pattern.validate.custom001;

import java.util.function.Predicate;

public class NotNull implements Predicate<String> {
    @Override
    public boolean test(String target) {
        return null != target;
    }
}
