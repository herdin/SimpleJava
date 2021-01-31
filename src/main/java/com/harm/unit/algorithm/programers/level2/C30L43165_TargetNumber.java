package com.harm.unit.algorithm.programers.level2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class C30L43165_TargetNumber {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(C30L43165_TargetNumber.class);
        int[][] datas = {
            {1, 1, 1, 1, 1}, {3}, {5},
        };
        for(int i=0; i<datas.length;) {
            int[] numbers = datas[i++];
            int target = datas[i++][0];
            int expect = datas[i++][0];
            logger.debug("numbers {}, target {}, expect {}, solution {}", Arrays.toString(numbers), target, expect, new Solution().solution(numbers, target));
        }
    }
    static class Solution {
//        Logger logger = LoggerFactory.getLogger(C30L43165_TargetNumber.Solution.class);
        ArrayList<Integer> sums = new ArrayList<>();
        public int solution(int[] numbers, int target) {
            int[] sign = new int[numbers.length];
            calculate(numbers, sign);
            return (int) sums.stream().map(Integer::intValue).filter(i -> target == i).count();
        }
        void calculate(int[] numbers, int[] sign) {
//            logger.debug("numbers {}, sign {}", Arrays.toString(numbers), Arrays.toString(sign));
//            logger.debug("sums {}", sums);
            int i;
            for(i=0; i<sign.length; i++) {
                if(sign[i] == 0) {
                    break;
                }
            }

            if(i!=sign.length) {
                int[] sign1 = Arrays.copyOf(sign, sign.length);
                sign1[i] = 1;
                calculate(numbers, sign1);
                int[] sign2 = Arrays.copyOf(sign, sign.length);
                sign2[i] = -1;
                calculate(numbers, sign2);
            } else {
                int sum = 0;
                for(int j=0; j<numbers.length; j++) {
                    sum += numbers[j]*sign[j];
                }
                sums.add(sum);
            }
        }

        int sum(int[] numbers) {
            return IntStream.of(numbers).sum();
        }
    }
}