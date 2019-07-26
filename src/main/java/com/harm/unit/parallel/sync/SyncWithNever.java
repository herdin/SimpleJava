package com.harm.unit.parallel.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncWithNever extends SyncTarget {
    private Logger logger = LoggerFactory.getLogger(SyncWithNever.class);

    public SyncWithNever(Boolean methodFlag) {
        super(methodFlag);
    }

    @Override
    public void manipulate() {
        this.logger.debug("{} : manipulate : {}", this.hashCode(), ++SyncTarget.data);
    }
}
