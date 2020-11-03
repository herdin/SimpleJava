package com.harm.unit.lang.reflect.annotaion.repeatable;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

public class RepeatableTest {
    private Logger logger = LoggerFactory.getLogger(RepeatableTest.class);

    @Test
    public void repeatableTest01() {
        @RepeatableItem(REPEATABLE_VALUE.REPEATABLE_ONE)
        @RepeatableItem(REPEATABLE_VALUE.REPEATABLE_TWO)
        @RepeatableItem(REPEATABLE_VALUE.REPEATABLE_THREE)
        class RepeatableTestTarget01 { }

        Annotation[] annotations = RepeatableTestTarget01.class.getAnnotationsByType(RepeatableItem.class);
        logger.debug("{}", annotations.length);
        for(Annotation annotation : annotations) {
            logger.debug("{}", annotation);
        }

        Assert.assertTrue(annotations.length == 3);

    }

    @Test
    public void repeatableTest02() {
        @RepeatableItem(REPEATABLE_VALUE.REPEATABLE_ONE)
        @RepeatableItem(REPEATABLE_VALUE.REPEATABLE_THREE)
        class RepeatableTestTarget02 { }

        Annotation[] annotations = RepeatableTestTarget02.class.getAnnotations();
        logger.debug("{}", annotations.length);
        for(Annotation annotation : annotations) {
            logger.debug("{}", annotation);
        }

        Assert.assertTrue(annotations.length == 1); //RepeatableGroup 1


        RepeatableGroup repeatableGroup = RepeatableTestTarget02.class.getAnnotation(RepeatableGroup.class);

        for(RepeatableItem repeatableItem : repeatableGroup.value()) {
            logger.debug("{}", repeatableItem);
        }

        Assert.assertTrue(repeatableGroup.value().length == 2); //repeatableGroup values 2

    }

    @Test
    public void repeatableTest03() {
        @RepeatableItem(REPEATABLE_VALUE.REPEATABLE_THREE)
        class RepeatableTestTarget03 { }

        RepeatableGroup repeatableGroup = RepeatableTestTarget03.class.getAnnotation(RepeatableGroup.class);

        logger.debug("{}", repeatableGroup);
        Assert.assertTrue(repeatableGroup == null);

        RepeatableItem[] repeatableItems = RepeatableTestTarget03.class.getAnnotationsByType(RepeatableItem.class);

        logger.debug("{}", repeatableItems);
        for(RepeatableItem repeatableItem : repeatableItems) {
            logger.debug("{}", repeatableItem);
        }

        Assert.assertTrue(repeatableItems.length == 1);
    }

}
