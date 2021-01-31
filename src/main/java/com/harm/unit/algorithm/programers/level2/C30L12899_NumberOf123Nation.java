package com.harm.unit.algorithm.programers.level2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

public class C30L12899_NumberOf123Nation {
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(C30L12899_NumberOf123Nation.class);
        IntStream.rangeClosed(1, 10).forEach(value -> {
            logger.debug("value -> {}, result -> {}", value, new Solution().solution(value));
        });
    }
    static class Solution {
        public String solution(int n) {
            String answer = "";
            int rest = n%3;
            int div = n/3;
            boolean perfectDiv = (n/3.0) == (n/3);
            if(perfectDiv) div--;

            if(div <= 0) {
                return getLast(rest);
            } else {
                return solution(div) + getLast(rest);
            }
        }
        public String getLast(int n) {
            switch(n) {
                case 1: return "1";
                case 2: return "2";
                case 0: return "4";
                default: throw new IllegalArgumentException("not applicable number -> " + n);
            }
        }
    }
}