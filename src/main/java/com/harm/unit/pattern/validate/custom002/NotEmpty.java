package com.harm.unit.pattern.validate.custom002;

public class NotEmpty implements ValidateStrategy<String> {
    @Override
    public boolean test(String target) {
        return !"".equals(target);
    }
}
