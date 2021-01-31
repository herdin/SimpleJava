package com.harm.unit.algorithm.hackerrank;

import com.harm.unit.algorithm.leetcode.medium.Problems02AddTwoNumbers2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiagonalDifference {
    public static Logger logger = LoggerFactory.getLogger(Problems02AddTwoNumbers2.class);
    public static void main(String[] args) {
        class Data {
            List<List<Integer>> arr;
            int expect;

            public Data(List<List<Integer>> arr, int expect) {
                this.arr = arr;
                this.expect = expect;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "arr={" + arr.stream().map(ar -> ar.toString()).collect(Collectors.joining(", ")) +
                        "}, expect=" + expect +
                        '}';
            }
        }
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data(List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(9, 8, 9)), 2));
        for(Data data : dataList) {
            logger.debug("data {}, solution {}", data, new DiagonalDifference.Solution().diagonalDifference(data.arr));
        }

    }

    static class Solution {
        public int diagonalDifference(List<List<Integer>> arr) {
            int first = 0;
            int firstIndex = arr.get(0).size();
            int second = 0;
            int secondIndex = 0;
            for(int i=0; i<arr.size(); i++) {
                first += arr.get(i).get(firstIndex-1);
                second += arr.get(i).get(i);
                firstIndex--;
            }
            return Math.abs(first-second);
        }
    }

}
