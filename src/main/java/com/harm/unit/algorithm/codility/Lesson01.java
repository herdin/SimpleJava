package com.harm.unit.algorithm.codility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lesson01 {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Lesson01.class);
        int n = 6291457;
        int result = new Solution().solution(n);
        logger.debug("int max {}, min {}", Integer.MAX_VALUE, Integer.MIN_VALUE);
        logger.debug("binary string {}", Integer.toBinaryString(n));
        logger.debug("n is {}, result is {}", n, result);
    }

    static class Solution {
        public int solution(int N) {
            char[] binaryCharArr = Integer.toBinaryString(N).toCharArray();
            int maxLength = 0;
            int tempLength = 0;
            boolean isStart = false;
            for(int i=0; i<binaryCharArr.length; i++) {
                char binaryChar = binaryCharArr[i];
                if(binaryChar == '1') {
                    if(isStart) {
                        if(maxLength < tempLength) {
                            maxLength = tempLength;
                        }
                        tempLength = 0;
                    } else {
                        isStart = true;
                    }
                } else {
                    if(isStart) {
                        tempLength++;
                    }
                }
            }
            return maxLength;
        }
    }
}
