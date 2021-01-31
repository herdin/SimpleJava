package com.harm.unit.algorithm.leetcode.easy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 끝
 * */
public class Problems07ReverseInteger {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems07ReverseInteger.class);
        int input = 1534236469;
        input = 123;
        logger.debug("input {}, output {}", input, new Solution().reverse(input));

        logger.debug("int min {}, int max {}, {}", Integer.MIN_VALUE, Integer.MAX_VALUE, Long.parseLong("9646324351"));
    }

    static class Solution {
        Logger logger = LoggerFactory.getLogger(Solution.class);
        public int reverse(int x) {
            boolean isNegative = (x < 0);
            x = (isNegative)? -1*x: x;

            String collect;
//        LinkedList<String> stack = new LinkedList<>();
//        Arrays.stream(("" + x).split("")).forEach(item -> stack.push(item));
//        String collect = stack.stream().collect(Collectors.joining());
            String[] strArr = ("" + x).split("");
            StringBuffer sb = new StringBuffer();
            for(int i=strArr.length-1; i>=0; i--) {
                sb.append(strArr[i]);
            }
            collect = sb.toString();

            int output = 0;
            try {
                output = Integer.parseInt((isNegative)? "-" + collect: collect);
            } catch(NumberFormatException e) {
            }
            return output;
        }
    }//Solution

    static class FastestSolution {
        public int reverse(int x) {
            int res = 0;

            while(x != 0) {
                if(Math.abs(res) > Integer.MAX_VALUE / 10) return 0;
                res = res * 10 + x % 10;
                x = x / 10;
            }
            return res;
        }
    }//FastestSolution
}