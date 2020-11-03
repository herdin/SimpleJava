package com.harm.unit.lang.parallel.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncWithClass extends SyncTarget {
    private static Logger logger = LoggerFactory.getLogger(SyncWithClass.class);

    public SyncWithClass(Boolean methodFlag) {
        super(methodFlag);
    }

    @Override
    public void manipulate() {
        if(super.methodFlag)
            this.manipulateWithNormalMethod();
        else
            SyncWithClass.manipulateWithSyncronizedMethod();
    }
    private void manipulateWithNormalMethod() {
        synchronized (SyncWithClass.class) {
            SyncWithClass.logger.debug("{} : manipulateWithNormalMethod : {}", this.hashCode(), ++SyncTarget.data);
        }
    }
    private static synchronized void manipulateWithSyncronizedMethod() {
        SyncWithClass.logger.debug("{} : manipulateWithSyncronizedMethod : {}", "STATIC METHOD NO HASH", ++SyncTarget.data);
    }
}
