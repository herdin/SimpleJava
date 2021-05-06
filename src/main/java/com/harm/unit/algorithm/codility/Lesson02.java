package com.harm.unit.algorithm.codility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Lesson02 {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Lesson02.class);
        int[] A = {4};//{3, 8, 9, 7, 6};
        int K = 3;
        logger.debug("n is {}, result is {}", Arrays.toString(A), Arrays.toString(new Solution().solution(A, K)));
    }

    static class Solution {
        public int[] solution(int[] A, int K) {
            int[] B = new int[A.length];
            for(int i=0; i<A.length; i++) {
                B[afterRotate(A.length, i, K)] = A[i];
            }
            return B;
        }
        private int afterRotate(int length, int currentIndex, int rotateTimes) {
            rotateTimes = rotateTimes%length;
            if(currentIndex+rotateTimes < length) {
                return currentIndex+rotateTimes;
            } else {
                return currentIndex+rotateTimes-length;
            }
        }
    }
}
/**
 * 1, 0, 3
 * 3-1=2
 *
 * 3 0 4
 * 1 2 3
 * 1-> 3 1 2
 * 2-> 2 3 1
 * 3-> 1 2 3
 * 4->
 *
 * */