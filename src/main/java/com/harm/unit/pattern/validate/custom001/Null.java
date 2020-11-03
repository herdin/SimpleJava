package com.harm.unit.pattern.validate.custom001;

import java.util.function.Predicate;

public class Null implements Predicate<String> {
    @Override
    public boolean test(String target) {
        return null == target;
    }
}
