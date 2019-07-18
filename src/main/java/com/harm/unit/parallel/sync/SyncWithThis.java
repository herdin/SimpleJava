package com.harm.unit.parallel.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncWithThis extends SyncTarget {
    private Logger logger = LoggerFactory.getLogger(SyncWithThis.class);
    public SyncWithThis(boolean methodFlag) {
        super.methodFlag = methodFlag;
    }
    @Override
    public void manipulate() {
        if(super.methodFlag)
            this.manipulateWithNormalMethod();
        else
            this.manipulateWithStaticMethod();
    }

    private void manipulateWithNormalMethod() {
        synchronized (this) {
            this.logger.debug("{} : manipulateWithNormalMethod : {}", this.hashCode(), ++SyncTarget.data);
        }
    }
    private synchronized void manipulateWithStaticMethod() {
        this.logger.debug("{} : manipulateWithStaticMethod : {}", this.hashCode(), ++SyncTarget.data);
    }
}
