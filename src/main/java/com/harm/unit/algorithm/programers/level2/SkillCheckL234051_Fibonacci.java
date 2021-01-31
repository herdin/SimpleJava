package com.harm.unit.algorithm.programers.level2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;

public class SkillCheckL234051_Fibonacci {
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(SkillCheckL234051_Fibonacci.class);
        class Data {
            int input;
            int output;
            public Data(int input, int output) {
                this.input = input;
                this.output = output;
            }
            @Override
            public String toString() {
                return "Data{" +
                        "input=" + input +
                        ", output=" + output +
                        '}';
            }
        }
        ArrayList<Data> datas = new ArrayList<>();
        datas.add(new Data(2, 1));
        datas.add(new Data(3, 2));
        datas.add(new Data(5, 5));
        datas.add(new Data(100000, -1));

        datas.stream().forEach(data -> {
            logger.debug("data -> {}, result -> {}", data, new Solution().solution(data.input));
        });
    }
    static class Solution {
        public int solution(int n) {
            BigDecimal pprev = BigDecimal.ZERO;
            BigDecimal prev = BigDecimal.ONE;
            BigDecimal fibonacci = null;
            for(long i=2; i<=n; i++) {
                fibonacci = pprev.add(prev);
                pprev = prev;
                prev = fibonacci;
            }
            return fibonacci.remainder(BigDecimal.valueOf(1234567L)).intValue();
        }
    }
}