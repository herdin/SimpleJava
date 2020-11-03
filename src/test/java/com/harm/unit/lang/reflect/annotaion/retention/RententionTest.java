package com.harm.unit.lang.reflect.annotaion.retention;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

public class RententionTest {
    private Logger logger = LoggerFactory.getLogger(RententionTest.class);
    @Test
    public void rententionClasstest() {
        @RetentionClass
        class RetentionClassTarget {}

        RetentionClassTarget retentionClassTarget = new RetentionClassTarget();

        Annotation[] annotations = retentionClassTarget.getClass().getAnnotations();
        for(Annotation annotation : annotations) {
            logger.debug("{}", annotations);
        }

        Assert.assertTrue(annotations.length == 0);
    }

    @Test
    public void retentionSourceTest() {

        @RetentionSource
        class RetentionSourceTarget {}
        RetentionSourceTarget retentionSourceTarget = new RetentionSourceTarget();
        Annotation[] annotations = retentionSourceTarget.getClass().getAnnotations();
        for(Annotation annotation : annotations) {
            logger.debug("{}", annotations);
        }

        Assert.assertTrue(annotations.length == 0);
    }
    @Test
    public void retentionRuntimeTest() {
        @RetentionRuntime
        class RetentionRuntimeTarget {}
        RetentionRuntimeTarget retentionRuntimeTarget = new RetentionRuntimeTarget();
        Annotation[] annotations = retentionRuntimeTarget.getClass().getAnnotations();
        for(Annotation annotation : annotations) {
            logger.debug("{}", annotations);
        }

        Assert.assertTrue(annotations.length == 1);
    }
}
