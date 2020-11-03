package com.harm.unit.lang.reflect.annotaion.inherited;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface InheritedItem {
    String name() default "";
    String[] value() default {};
}
