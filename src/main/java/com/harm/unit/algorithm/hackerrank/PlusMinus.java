package com.harm.unit.algorithm.hackerrank;

import com.harm.unit.algorithm.leetcode.medium.Problems02AddTwoNumbers2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PlusMinus {
    public static Logger logger = LoggerFactory.getLogger(Problems02AddTwoNumbers2.class);
    public static void main(String[] args) {
        class Data {
            final int[] arr;

            public Data(int[] arr) {
                this.arr = arr;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "arr=" + Arrays.toString(arr) +
                        '}';
            }
        }
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data(new int[]{1, 1, 0, -1, -1}));
        dataList.add(new Data(new int[]{-4, 3, -9, 0, 4, 1}));
        for(Data data : dataList) {
            logger.debug("data {}", data.arr);
            new PlusMinus.Solution().plusMinus(data.arr);
            logger.debug("solution {}", data.arr);
        }

    }

    static class Solution {
        void plusMinus(int[] arr) {
            AtomicInteger positiveCnt = new AtomicInteger();
            AtomicInteger negativeCnt = new AtomicInteger();
            AtomicInteger zeroCnt = new AtomicInteger();
            Arrays.stream(arr).parallel().forEach(number -> {
                if(number > 0) positiveCnt.incrementAndGet();
                else if(number < 0) negativeCnt.incrementAndGet();
                else zeroCnt.incrementAndGet();
            });
            BigDecimal denominator = new BigDecimal(arr.length);
            BigDecimal numerator = new BigDecimal(positiveCnt.get()).setScale(6);
            System.out.println(numerator.divide(denominator, RoundingMode.FLOOR).setScale(6, RoundingMode.FLOOR).toString());

            numerator = new BigDecimal(negativeCnt.get()).setScale(6);
            System.out.println(numerator.divide(denominator, RoundingMode.FLOOR).setScale(6, RoundingMode.FLOOR).toString());

            numerator = new BigDecimal(zeroCnt.get()).setScale(6);
            System.out.println(numerator.divide(denominator, RoundingMode.FLOOR).setScale(6, RoundingMode.FLOOR).toString());
        }
    }

}
