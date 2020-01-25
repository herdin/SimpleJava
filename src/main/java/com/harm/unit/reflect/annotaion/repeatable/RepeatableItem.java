package com.harm.unit.reflect.annotaion.repeatable;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Repeatable(value = RepeatableGroup.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatableItem {
    REPEATABLE_VALUE value();
}
