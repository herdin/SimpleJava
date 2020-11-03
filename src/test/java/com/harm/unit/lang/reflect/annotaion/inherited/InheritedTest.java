package com.harm.unit.lang.reflect.annotaion.inherited;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

public class InheritedTest {
    private Logger logger = LoggerFactory.getLogger(InheritedTest.class);
    @Test
    public void test() {
        @InheritedItem({"hello", "world"})
        class InheritedTarget { }
        class InheritedTargetChild extends InheritedTarget { }

        Annotation[] annotations = InheritedTargetChild.class.getAnnotations();
        logger.debug( "annotations.length -> {}", annotations.length);
        for(Annotation annotation : annotations) {
            logger.debug("{}", annotation);
        }

        InheritedItem inheritedItem = InheritedTargetChild.class.getAnnotation(InheritedItem.class);
        for(String val : inheritedItem.value()) {
            logger.debug("inheritedItem.value() -> {}", val);
        }
    }
}
