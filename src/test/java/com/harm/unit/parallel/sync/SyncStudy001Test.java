package com.harm.unit.parallel.sync;

import com.harm.unit.UnitRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class SyncStudy001Test {
//    private Logger logger = LoggerFactory.getLogger(SyncStudy001Test.class);
    private ArrayList<Object> param = new ArrayList<>();
    @Before
    public void beforeEachTest() {
        SyncTarget.resetData();
        param.clear();
        for(int i=0; i<SyncTarget.PARAMS.values().length; i++) {
            param.add(new Object());
        }
    }

    @Test
    public void test_SyncWithThis_prototype_normalMethod() {
        this.param.set(SyncTarget.PARAMS.THREAD_CNT.ordinal(), 10L);
        param.set(SyncTarget.PARAMS.PROCESS_CNT.ordinal(), 100L);
        param.set(SyncTarget.PARAMS.CLASS.ordinal(), SyncWithThis.class);
        param.set(SyncTarget.PARAMS.IS_PROTOTYPE.ordinal(), true);
        param.set(SyncTarget.PARAMS.CALL_NORMAL_METHOD.ordinal(), true);

        //sync with this && prototype instance make unsyncronized result
        Assert.assertThat(UnitRunner.start(new SyncStudy001(), param.toArray()), not(1000L));
    }

    @Test
    public void test_SyncWithThis_prototype_syncronizedMethod() {
        this.param.set(SyncTarget.PARAMS.THREAD_CNT.ordinal(), 10L);
        param.set(SyncTarget.PARAMS.PROCESS_CNT.ordinal(), 100L);
        param.set(SyncTarget.PARAMS.CLASS.ordinal(), SyncWithThis.class);
        param.set(SyncTarget.PARAMS.IS_PROTOTYPE.ordinal(), true);
        param.set(SyncTarget.PARAMS.CALL_NORMAL_METHOD.ordinal(), false);

        //sync with this && singleton instance make syncronized result
        Assert.assertThat(UnitRunner.start(new SyncStudy001(), param.toArray()), not(1000L));
    }

    @Test
    public void test_SyncWithThis_singleton_normalMethod() {
        this.param.set(SyncTarget.PARAMS.THREAD_CNT.ordinal(), 10L);
        param.set(SyncTarget.PARAMS.PROCESS_CNT.ordinal(), 100L);
        param.set(SyncTarget.PARAMS.CLASS.ordinal(), SyncWithThis.class);
        param.set(SyncTarget.PARAMS.IS_PROTOTYPE.ordinal(), false);
        param.set(SyncTarget.PARAMS.CALL_NORMAL_METHOD.ordinal(), true);

        Assert.assertThat(UnitRunner.start(new SyncStudy001(), param.toArray()), is(1000L));
    }

    @Test
    public void test_SyncWithThis_singleton_syncronizedMethod() {
        this.param.set(SyncTarget.PARAMS.THREAD_CNT.ordinal(), 10L);
        param.set(SyncTarget.PARAMS.PROCESS_CNT.ordinal(), 100L);
        param.set(SyncTarget.PARAMS.CLASS.ordinal(), SyncWithThis.class);
        param.set(SyncTarget.PARAMS.IS_PROTOTYPE.ordinal(), false);
        param.set(SyncTarget.PARAMS.CALL_NORMAL_METHOD.ordinal(), false);

        Assert.assertThat(UnitRunner.start(new SyncStudy001(), param.toArray()), is(1000L));
    }

    @Test
    public void test_SyncWithClass_prototype_normalMethod() {
        this.param.set(SyncTarget.PARAMS.THREAD_CNT.ordinal(), 10L);
        param.set(SyncTarget.PARAMS.PROCESS_CNT.ordinal(), 100L);
        param.set(SyncTarget.PARAMS.CLASS.ordinal(), SyncWithClass.class);
        param.set(SyncTarget.PARAMS.IS_PROTOTYPE.ordinal(), true);
        param.set(SyncTarget.PARAMS.CALL_NORMAL_METHOD.ordinal(), true);

        Assert.assertThat(UnitRunner.start(new SyncStudy001(), param.toArray()), is(1000L));
    }

    @Test
    public void test_SyncWithClass_prototype_syncronizedMethod() {
        this.param.set(SyncTarget.PARAMS.THREAD_CNT.ordinal(), 10L);
        param.set(SyncTarget.PARAMS.PROCESS_CNT.ordinal(), 100L);
        param.set(SyncTarget.PARAMS.CLASS.ordinal(), SyncWithClass.class);
        param.set(SyncTarget.PARAMS.IS_PROTOTYPE.ordinal(), true);
        param.set(SyncTarget.PARAMS.CALL_NORMAL_METHOD.ordinal(), false);

        Assert.assertThat(UnitRunner.start(new SyncStudy001(), param.toArray()), is(1000L));
    }

    @Test
    public void test_SyncWithClass_singleton_normalMethod() {
        this.param.set(SyncTarget.PARAMS.THREAD_CNT.ordinal(), 10L);
        param.set(SyncTarget.PARAMS.PROCESS_CNT.ordinal(), 100L);
        param.set(SyncTarget.PARAMS.CLASS.ordinal(), SyncWithClass.class);
        param.set(SyncTarget.PARAMS.IS_PROTOTYPE.ordinal(), false);
        param.set(SyncTarget.PARAMS.CALL_NORMAL_METHOD.ordinal(), true);

        Assert.assertThat(UnitRunner.start(new SyncStudy001(), param.toArray()), is(1000L));
    }

    @Test
    public void test_SyncWithClass_singleton_syncronizedMethod() {
        this.param.set(SyncTarget.PARAMS.THREAD_CNT.ordinal(), 10L);
        param.set(SyncTarget.PARAMS.PROCESS_CNT.ordinal(), 100L);
        param.set(SyncTarget.PARAMS.CLASS.ordinal(), SyncWithClass.class);
        param.set(SyncTarget.PARAMS.IS_PROTOTYPE.ordinal(), false);
        param.set(SyncTarget.PARAMS.CALL_NORMAL_METHOD.ordinal(), false);

        Assert.assertThat(UnitRunner.start(new SyncStudy001(), param.toArray()), is(1000L));
    }

    @Test
    public void test_SyncWithNever() {
        this.param.set(SyncTarget.PARAMS.THREAD_CNT.ordinal(), 10L);
        param.set(SyncTarget.PARAMS.PROCESS_CNT.ordinal(), 100L);
        param.set(SyncTarget.PARAMS.CLASS.ordinal(), SyncWithNever.class);
        param.set(SyncTarget.PARAMS.IS_PROTOTYPE.ordinal(), Boolean.TRUE);
        param.set(SyncTarget.PARAMS.CALL_NORMAL_METHOD.ordinal(), Boolean.TRUE);

        Assert.assertThat(UnitRunner.start(new SyncStudy001(), param.toArray()), not(1000L));
    }

}
