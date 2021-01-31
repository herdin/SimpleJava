package com.harm.unit.algorithm.hackerrank;

import com.harm.unit.algorithm.leetcode.medium.Problems02AddTwoNumbers2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CompareTheTriplets {
    public static Logger logger = LoggerFactory.getLogger(Problems02AddTwoNumbers2.class);
    public static void main(String[] args) {
        class Data {
            List<Integer> a;
            List<Integer> b;
            List<Integer> expect;

            public Data(List<Integer> a, List<Integer> b, List<Integer> expect) {
                this.a = a;
                this.b = b;
                this.expect = expect;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "a=" + a +
                        ", b=" + b +
                        ", expect=" + expect +
                        '}';
            }
        }
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data(List.of(17, 28, 30), List.of(99, 16, 8), List.of(2, 1)));
        for(Data data : dataList) {
            logger.debug("data {}, solution {}", data, new CompareTheTriplets.Solution().compareTriplets(data.a, data.b));
        }

    }

    static class Solution {
        // Complete the compareTriplets function below.
        public List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
            List<Integer> results = new ArrayList<>();
            results.add(0);
            results.add(0);
            final int INDEX_A = 0;
            final int INDEX_B = 1;
            IntStream.range(0, a.size()).forEach(i -> {
                if(a.get(i) > b.get(i)) {
                    results.set(INDEX_A, results.get(INDEX_A)+1);
                } else if(a.get(i) != b.get(i))  {
                    results.set(INDEX_B, results.get(INDEX_B)+1);
                }
            });
            return results;
        }
    }

}
