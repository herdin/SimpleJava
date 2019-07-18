package com.harm.unit.parallel.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncWithClass extends SyncTarget {
    private static Logger logger = LoggerFactory.getLogger(SyncWithClass.class);
    public SyncWithClass(boolean methodFlag) {
        super.methodFlag = methodFlag;
    }
    @Override
    public void manipulate() {
        if(super.methodFlag)
            this.manipulateWithNormalMethod();
        else
            SyncWithClass.manipulateWithStaticMethod();
    }
    private void manipulateWithNormalMethod() {
        synchronized (SyncWithClass.class) {
            SyncWithClass.logger.debug("{} : manipulateWithNormalMethod : {}", this.hashCode(), ++SyncTarget.data);
        }
    }
    private static synchronized void manipulateWithStaticMethod() {
        SyncWithClass.logger.debug("{} : manipulateWithStaticMethod : {}", "STATIC METHOD NO HASH", ++SyncTarget.data);
    }
}
