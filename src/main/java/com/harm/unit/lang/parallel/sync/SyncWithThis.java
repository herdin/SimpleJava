package com.harm.unit.lang.parallel.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncWithThis extends SyncTarget {
    private Logger logger = LoggerFactory.getLogger(SyncWithThis.class);
    public SyncWithThis(Boolean flag) {
        super(flag);
    }
    @Override
    public void manipulate() {
        if(super.methodFlag)
            this.manipulateWithNormalMethod();
        else
            this.manipulateWithSyncronizedMethod();
    }

    private void manipulateWithNormalMethod() {
        synchronized (this) {
            this.logger.debug("{} : manipulateWithNormalMethod : {}", this.hashCode(), ++SyncTarget.data);
        }
    }
    private synchronized void manipulateWithSyncronizedMethod() {
        this.logger.debug("{} : manipulateWithSyncronizedMethod : {}", this.hashCode(), ++SyncTarget.data);
    }
}
