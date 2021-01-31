package com.harm.unit.algorithm.programers.level2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/*
[초5경시] 대각선이 지나는 격자점의 개수 -청주 금천동(금천광장) 수학학원 ★오르비수학★
https://m.blog.naver.com/orbis1020/220664563768
*/
public class C30L62048_FineSquare {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(C30L62048_FineSquare.class);
        int[][] whs = {{8, 12}, {12, 8}, {Integer.MAX_VALUE-1, 20}};
        for(int[] wh : whs) {
            logger.debug("w {}, h {}, result -> {}", wh[0], wh[1], new Solution().solution(wh[0], wh[1]));
        }
    }
    static class Solution {
//        Logger logger = LoggerFactory.getLogger(C30L62048_FineSquare.Solution.class);
        public long solution(int w, int h) {
            return (long)w*h - (w + h - gcd(w, h));
        }
        private int gcd(int a, int b) {
            int big=0, small=0;
            if(a > b) {
                big = a;
                small = b;
            } else {
                big = b;
                small = a;
            }

            int mod = big%small;
            return (mod == 0)? small:gcd(small, mod);
        }
        public long solutionB(int w, int h) {
            BigDecimal inclination = BigDecimal.valueOf(h).divide(BigDecimal.valueOf(w), 5, RoundingMode.FLOOR);
            BigDecimal width = BigDecimal.valueOf(w);
            BigDecimal height = BigDecimal.valueOf(h);
            BigDecimal sum = BigDecimal.ZERO;
            long count = 0;
            while(sum.compareTo(height) <= 0) {
                boolean flag = sum.setScale(0, RoundingMode.FLOOR).compareTo(sum) != 0;
                if(flag) {
                    count++;
                } else if(count > 0) {
                    break;
                }
                sum = sum.add(inclination);
            }
//            logger.debug("sum {}, count {}", sum, count);
            BigDecimal heightDivivedBySum = height.divide(sum);
            BigDecimal pointCount = heightDivivedBySum.multiply(BigDecimal.valueOf(count));

            return width.multiply(height).subtract(height.add(pointCount)).intValue();
        }
    }
}