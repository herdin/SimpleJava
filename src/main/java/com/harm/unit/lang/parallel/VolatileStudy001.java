package com.harm.unit.lang.parallel;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VolatileStudy001 implements Unit {
    private Logger logger = LoggerFactory.getLogger(VolatileStudy001.class);

    @Override
    public Object execute(Object[] obj) throws Exception {
        SharedObject so = new SharedObject();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new VolatileRunner(i, so));
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        logger.debug("last -> {}", so.count);


        return null;
    }

    static class SharedObject {
        public int count = 0;
    }

    static class VolatileRunner implements Runnable {
        private Logger logger = LoggerFactory.getLogger(VolatileRunner.class);
        final int type;
        final SharedObject so;

        public VolatileRunner(int type, SharedObject so) {
            this.type = type;
            this.so = so;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                if (type == 0) {
                    logger.debug("{} read -> {}", hashCode(), so.count);
                } else {
                    logger.debug("{} read and write -> {}", hashCode(), so.count++);
                }
            }
        }
    }
}
