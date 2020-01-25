package com.harm.unit.reflect.annotaion.repeatable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatableGroup {
    RepeatableItem[] value();
}
