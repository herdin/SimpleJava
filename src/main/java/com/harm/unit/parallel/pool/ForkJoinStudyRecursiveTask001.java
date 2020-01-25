package com.harm.unit.parallel.pool;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinStudyRecursiveTask001 implements Unit {
    private Logger logger = LoggerFactory.getLogger(ForkJoinStudyRecursiveTask001.class);
    @Override
    public Object execute(Object[] obj) throws Exception {
        int threadNumber = Runtime.getRuntime().availableProcessors();
        this.logger.debug("AVAILABLE PROCESSORS {}", threadNumber);

        int[] numbers = new int[10000];
        for(int i=0; i<numbers.length; i++) {
            numbers[i] = i;
        }

        LocalDateTime startTime = LocalDateTime.now();
        long sum = 0;
        for(int i=0; i<numbers.length; i++) {
            sum += numbers[i];
        }
        LocalDateTime endTime = LocalDateTime.now();
        this.logger.debug("RESULT {} - TAKES {}", sum, Duration.between(startTime, endTime));


        ForkJoinPool forkJoinPool = new ForkJoinPool(threadNumber);
        startTime = LocalDateTime.now();
        long result = forkJoinPool.invoke(new Sum(numbers, 0, numbers.length));
        endTime = LocalDateTime.now();
        this.logger.debug("RESULT {} - TAKES {}", result, Duration.between(startTime, endTime));

        return null;
    }

    static class Sum extends RecursiveTask<Long> {
        final int low;
        final int high;
        final int[] array;

        public Sum(int[] array, int low, int high) {
            this.array = array;
            this.low = low;
            this.high = high;
        }
        @Override
        protected Long compute() {
            if(high - low <= 10) {
                long sum = 0;

                for(int i = low; i < high; ++i)
                    sum += array[i];
                return sum;
            } else {
                int mid = low + (high - low) / 2;
                Sum left  = new Sum(array, low, mid);
                Sum right = new Sum(array, mid, high);
                left.fork();
                long rightResult = right.compute();
                long leftResult  = left.join();
                return leftResult + rightResult;
            }
        }
    }
}
