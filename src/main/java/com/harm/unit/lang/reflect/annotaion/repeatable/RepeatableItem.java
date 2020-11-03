package com.harm.unit.lang.reflect.annotaion.repeatable;

import java.lang.annotation.*;

@Repeatable(value = RepeatableGroup.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RepeatableItem {
    REPEATABLE_VALUE value();
}
