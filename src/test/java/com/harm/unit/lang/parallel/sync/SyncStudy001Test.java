package com.harm.unit.lang.parallel.sync;

import com.harm.unit.UnitRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SyncStudy001Test {
//    private Logger logger = LoggerFactory.getLogger(SyncStudy001Test.class);
    private ArrayList<Object> param = new ArrayList<>();
    @BeforeEach
    public void beforeEachTest() {
        SyncTarget.resetData();
        param.clear();
        for(int i=0; i<SyncTarget.PARAMS.values().length; i++) {
            param.add(new Object());
        }
    }

    @Test
    public void syncWithThis_prototype_normalMethod() {
        param.set(SyncTarget.PARAMS.THREAD_CNT.ordinal(), 10L);
        param.set(SyncTarget.PARAMS.PROCESS_CNT.ordinal(), 100L);
        param.set(SyncTarget.PARAMS.CLASS.ordinal(), SyncWithThis.class);
        param.set(SyncTarget.PARAMS.IS_PROTOTYPE.ordinal(), true);
        param.set(SyncTarget.PARAMS.CALL_NORMAL_METHOD.ordinal(), true);

        //sync with this && prototype instance make unsynchronized result
        org.junit.jupiter.api.Assertions.assertNotEquals(UnitRunner.start(new SyncStudy001(), param.toArray()), 1000L);
    }

    @Test
    public void syncWithThis_prototype_synchronizedMethod() {
        this.param.set(SyncTarget.PARAMS.THREAD_CNT.ordinal(), 10L);
        param.set(SyncTarget.PARAMS.PROCESS_CNT.ordinal(), 100L);
        param.set(SyncTarget.PARAMS.CLASS.ordinal(), SyncWithThis.class);
        param.set(SyncTarget.PARAMS.IS_PROTOTYPE.ordinal(), true);
        param.set(SyncTarget.PARAMS.CALL_NORMAL_METHOD.ordinal(), false);

        //sync with this && singleton instance make unsynchronized result
        org.junit.jupiter.api.Assertions.assertNotEquals(UnitRunner.start(new SyncStudy001(), param.toArray()), 1000L);
    }

    @Test
    @DisplayName("singleton makes synchronized result")
    public void syncWithThis_singleton_normalMethod() {
        this.param.set(SyncTarget.PARAMS.THREAD_CNT.ordinal(), 10L);
        param.set(SyncTarget.PARAMS.PROCESS_CNT.ordinal(), 100L);
        param.set(SyncTarget.PARAMS.CLASS.ordinal(), SyncWithThis.class);
        param.set(SyncTarget.PARAMS.IS_PROTOTYPE.ordinal(), false);
        param.set(SyncTarget.PARAMS.CALL_NORMAL_METHOD.ordinal(), true);

        org.junit.jupiter.api.Assertions.assertEquals(UnitRunner.start(new SyncStudy001(), param.toArray()), 1000L);
    }

    @Test
    @DisplayName("singleton makes synchronized result")
    public void syncWithThis_singleton_synchronizedMethod() {
        this.param.set(SyncTarget.PARAMS.THREAD_CNT.ordinal(), 10L);
        param.set(SyncTarget.PARAMS.PROCESS_CNT.ordinal(), 100L);
        param.set(SyncTarget.PARAMS.CLASS.ordinal(), SyncWithThis.class);
        param.set(SyncTarget.PARAMS.IS_PROTOTYPE.ordinal(), false);
        param.set(SyncTarget.PARAMS.CALL_NORMAL_METHOD.ordinal(), false);

        org.junit.jupiter.api.Assertions.assertEquals(UnitRunner.start(new SyncStudy001(), param.toArray()), 1000L);
    }

    @Test
    public void syncWithClass_prototype_normalMethod() {
        this.param.set(SyncTarget.PARAMS.THREAD_CNT.ordinal(), 10L);
        param.set(SyncTarget.PARAMS.PROCESS_CNT.ordinal(), 100L);
        param.set(SyncTarget.PARAMS.CLASS.ordinal(), SyncWithClass.class);
        param.set(SyncTarget.PARAMS.IS_PROTOTYPE.ordinal(), true);
        param.set(SyncTarget.PARAMS.CALL_NORMAL_METHOD.ordinal(), true);

        org.junit.jupiter.api.Assertions.assertEquals(UnitRunner.start(new SyncStudy001(), param.toArray()), 1000L);
    }

    @Test
    public void syncWithClass_prototype_synchronizedMethod() {
        this.param.set(SyncTarget.PARAMS.THREAD_CNT.ordinal(), 10L);
        param.set(SyncTarget.PARAMS.PROCESS_CNT.ordinal(), 100L);
        param.set(SyncTarget.PARAMS.CLASS.ordinal(), SyncWithClass.class);
        param.set(SyncTarget.PARAMS.IS_PROTOTYPE.ordinal(), true);
        param.set(SyncTarget.PARAMS.CALL_NORMAL_METHOD.ordinal(), false);

        org.junit.jupiter.api.Assertions.assertEquals(UnitRunner.start(new SyncStudy001(), param.toArray()), 1000L);
    }

    @Test
    public void syncWithClass_singleton_normalMethod() {
        this.param.set(SyncTarget.PARAMS.THREAD_CNT.ordinal(), 10L);
        param.set(SyncTarget.PARAMS.PROCESS_CNT.ordinal(), 100L);
        param.set(SyncTarget.PARAMS.CLASS.ordinal(), SyncWithClass.class);
        param.set(SyncTarget.PARAMS.IS_PROTOTYPE.ordinal(), false);
        param.set(SyncTarget.PARAMS.CALL_NORMAL_METHOD.ordinal(), true);

        org.junit.jupiter.api.Assertions.assertEquals(UnitRunner.start(new SyncStudy001(), param.toArray()), 1000L);
    }

    @Test
    public void syncWithClass_singleton_synchronizedMethod() {
        this.param.set(SyncTarget.PARAMS.THREAD_CNT.ordinal(), 10L);
        param.set(SyncTarget.PARAMS.PROCESS_CNT.ordinal(), 100L);
        param.set(SyncTarget.PARAMS.CLASS.ordinal(), SyncWithClass.class);
        param.set(SyncTarget.PARAMS.IS_PROTOTYPE.ordinal(), false);
        param.set(SyncTarget.PARAMS.CALL_NORMAL_METHOD.ordinal(), false);

        org.junit.jupiter.api.Assertions.assertEquals(UnitRunner.start(new SyncStudy001(), param.toArray()), 1000L);
    }

    @Test
    public void syncWithNever() {
        this.param.set(SyncTarget.PARAMS.THREAD_CNT.ordinal(), 10L);
        param.set(SyncTarget.PARAMS.PROCESS_CNT.ordinal(), 100L);
        param.set(SyncTarget.PARAMS.CLASS.ordinal(), SyncWithNever.class);
        param.set(SyncTarget.PARAMS.IS_PROTOTYPE.ordinal(), Boolean.TRUE);
        param.set(SyncTarget.PARAMS.CALL_NORMAL_METHOD.ordinal(), Boolean.TRUE);

        org.junit.jupiter.api.Assertions.assertNotEquals(UnitRunner.start(new SyncStudy001(), param.toArray()), 1000L);
    }

}
