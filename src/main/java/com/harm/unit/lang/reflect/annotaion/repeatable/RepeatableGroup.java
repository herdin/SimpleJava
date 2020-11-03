package com.harm.unit.lang.reflect.annotaion.repeatable;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RepeatableGroup {
    RepeatableItem[] value();
}
