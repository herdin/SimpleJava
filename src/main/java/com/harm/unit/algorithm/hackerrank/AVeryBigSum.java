package com.harm.unit.algorithm.hackerrank;

import com.harm.unit.algorithm.leetcode.medium.Problems02AddTwoNumbers2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class AVeryBigSum {
    public static Logger logger = LoggerFactory.getLogger(Problems02AddTwoNumbers2.class);
    public static void main(String[] args) {
        class Data {
            final long[] ar;
            final long expect;

            public Data(long[] ar, long expect) {
                this.ar = ar;
                this.expect = expect;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "ar=" + Arrays.toString(ar) +
                        ", expect=" + expect +
                        '}';
            }
        }
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data(new long[]{1000000001L, 1000000002L, 1000000003L, 1000000004L, 1000000005L}, 5000000015L));
        for(Data data : dataList) {
            logger.debug("data {}, solution {}", data, new AVeryBigSum.Solution().aVeryBigSum(data.ar));
        }

    }

    static class Solution {
        // Complete the aVeryBigSum function below.
        public long aVeryBigSum(long[] ar) {
            return Arrays.stream(ar).parallel().sum();
        }
    }
}
