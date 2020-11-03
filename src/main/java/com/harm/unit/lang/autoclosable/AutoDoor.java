package com.harm.unit.lang.autoclosable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoDoor implements AutoCloseable {
    Logger logger = LoggerFactory.getLogger(AutoDoor.class);
    public void doSomething() {
        logger.debug("do something!!!!");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void close() throws Exception {
        logger.debug("closed!!!!");
    }
}
