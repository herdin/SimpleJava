package com.harm.unit.parallel.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SyncTarget {
    public enum PARAMS {THREAD_CNT, PROCESS_CNT, CLASS, IS_PROTOTYPE, CALL_NORMAL_METHOD}
    private static Logger logger = LoggerFactory.getLogger(SyncTarget.class);
    protected boolean methodFlag = false;
    protected static long data = 0;
    public SyncTarget(Boolean methodFlag) {
        this.methodFlag = methodFlag;
    }
    public abstract void manipulate();
    public static long getData() {
        return SyncTarget.data;
    }
    public static void resetData() {
        SyncTarget.data = 0L;
        SyncTarget.logger.debug("DATA CLEAR.");
    }
}
