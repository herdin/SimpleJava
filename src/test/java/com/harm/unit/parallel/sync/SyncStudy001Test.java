package com.harm.unit.parallel.sync;

import com.harm.unit.UnitRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class SyncStudy001Test {
    private Logger logger = LoggerFactory.getLogger(SyncStudy001Test.class);
    @Before
    public void beforeEachTest() {
        SyncTarget.resetData();
    }
    @Test
    public void test_SyncWithThis_normalMethod() {
        long data = (Long)UnitRunner.start(new SyncStudy001(), new Object[]{new Long[]{10L, 100L}, new SyncWithThis(true)});
        Assert.assertThat(data, is(1000L));
    }
    @Test
    public void test_SyncWithThis_staticMethod() {
        long data = (Long)UnitRunner.start(new SyncStudy001(), new Object[]{new Long[]{10L, 100L}, new SyncWithThis(false)});
        Assert.assertThat(data, is(1000L));
    }

    @Test
    public void test_SyncWithClass_normalMethod() {
        long data = (Long)UnitRunner.start(new SyncStudy001(), new Object[]{new Long[]{10L, 100L}, new SyncWithClass(true)});
        Assert.assertThat(data, is(1000L));
    }

    @Test
    public void test_SyncWithClass_staticMethod() {
        long data = (Long)UnitRunner.start(new SyncStudy001(), new Object[]{new Long[]{10L, 100L}, new SyncWithClass(false)});
        Assert.assertThat(data, is(1000L));
    }

    @Test
    public void test_SyncWithNever() {
        long data = (Long)UnitRunner.start(new SyncStudy001(), new Object[]{new Long[]{10L, 100L}, new SyncWithNever()});
        Assert.assertThat(data, not(1000L));
    }

}
