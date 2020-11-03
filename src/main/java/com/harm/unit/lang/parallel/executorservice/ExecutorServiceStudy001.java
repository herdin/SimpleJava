package com.harm.unit.lang.parallel.executorservice;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceStudy001 implements Unit {
    private Logger logger = LoggerFactory.getLogger(ExecutorServiceStudy001.class);
    @Override
    public Object execute(Object[] obj) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<String> future1 = executorService.submit(() -> {
            logger.debug("enter1");
            String result = "i'm result1";
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.debug("leave1");
            return result;
        });
        Future<String> future2 = executorService.submit(() -> {
            logger.debug("enter2");
            String result = "i'm result2";
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.debug("leave2");
            return result;
        });


        executorService.shutdown();
        logger.debug("future1 get -> {}", future1.get());
        logger.debug("future2 get -> {}", future2.get());

        logger.debug("executorService.isTerminated() -> {}", executorService.isTerminated());
        logger.debug("executorService.isShutdown() -> {}", executorService.isShutdown());

        return null;
    }

}
