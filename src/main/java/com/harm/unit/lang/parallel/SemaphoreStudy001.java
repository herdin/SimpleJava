package com.harm.unit.lang.parallel;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

public class SemaphoreStudy001 implements Unit {
    private Logger logger = LoggerFactory.getLogger(SemaphoreStudy001.class);
    public static Semaphore semaphore = new Semaphore(3);
    @Override
    public Object execute(Object[] obj) throws Exception {
        logger.debug("init permit -> {}", SemaphoreStudy001.semaphore.availablePermits());

        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new SemaphoreWorker("Thread-" + i));
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        return null;
    }
    public static class SemaphoreWorker implements Runnable {
        private Logger logger = LoggerFactory.getLogger(SemaphoreWorker.class);
        private String name;
        public SemaphoreWorker(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            logger.debug("{} is run.", name);
            for(int i=0; i<100; i++) {
                logger.debug("{} current availablePermits -> {}", name, SemaphoreStudy001.semaphore.availablePermits());
                try {
                    SemaphoreStudy001.semaphore.acquire();
                    logger.debug("{} acquire", name);
                    logger.debug("{} current availablePermits -> {}", name, SemaphoreStudy001.semaphore.availablePermits());
                    for(int j=0; j<10; j++) {
                        Thread.sleep(1000L);
                        logger.debug("{} working -> {}", name, j);
                        logger.debug("{} current availablePermits -> {}", name, SemaphoreStudy001.semaphore.availablePermits());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    logger.debug("{} release", name);
                    logger.debug("{} current availablePermits -> {}", name, SemaphoreStudy001.semaphore.availablePermits());logger.debug("current availablePermits -> {}", SemaphoreStudy001.semaphore.availablePermits());
                    SemaphoreStudy001.semaphore.release();
                }
            }
        }
    }
}
