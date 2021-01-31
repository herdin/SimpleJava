package com.harm.unit.algorithm.leetcode.medium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 오전 11:49 화요일 2021-01-26
 * 오후 16:11 화요일 2021-01-26
 *
 *  */
public class Problems91DecodeWays {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems91DecodeWays.class);
        class Data {
            final String s;
            final int expect;

            public Data(String s, int expect) {
                this.s = s;
                this.expect = expect;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "s='" + s + '\'' +
                        ", expect=" + expect +
                        '}';
            }
        }
        List<Data> dataList = new ArrayList<>();
//        dataList.add(new Data("12", 2));
//        dataList.add(new Data("226", 3));
//        dataList.add(new Data("0", 0));
//        dataList.add(new Data("1", 1));
//        dataList.add(new Data("111111111111111111111111111111111111111111111", 1));
        dataList.add(new Data("123456789", 1));
        for(Data data : dataList) {
            logger.debug("data {}, solution {}", data.toString(), new Problems91DecodeWays.Solution().numDecodings(data.s));
        }

    }

    static class Solution {
//        Logger logger = LoggerFactory.getLogger(Problems91DecodeWays.Solution.class);
        public int numDecodings(String s) {
            int answer = 0;
            final int c = 64;
            Map<String, String> decodingMap = new HashMap<>();
            Map<String, Integer> memoryMap = new HashMap<>();
            IntStream.rangeClosed(1, 26).forEach(i -> decodingMap.put("" + i, "" +(char)(i+c)));
            answer += getNumOfDecodings(s, decodingMap, memoryMap);
            return answer;
        }

        int getNumOfDecodings(String s, Map<String, String> decodingMap, Map<String, Integer> memoryMap) {
//            logger.debug("-> {}", s);
            if(memoryMap.containsKey(s)) {
                return memoryMap.get(s);
            }
            int answer = 0;
            if(s.length() <= 0) return answer;
            if(s.length() == 1) {
                if(decodingMap.containsKey(s)) {
                    answer++;
                }
            } else if(s.length() == 2) {
                if(decodingMap.containsKey(s.substring(0, 1)) && decodingMap.containsKey(s.substring(1, 2))) {
                    answer++;
                }
                if(decodingMap.containsKey(s)) {
                    answer++;
                }
            } else {
                if(decodingMap.containsKey(s.substring(0, 1))) {
                    answer += getNumOfDecodings(s.substring(1), decodingMap, memoryMap);
                }
                if(decodingMap.containsKey(s.substring(0, 2))) {
                    answer += getNumOfDecodings(s.substring(2), decodingMap, memoryMap);
                }
            }
//            logger.debug("-> {} -> {}", s, answer);
            memoryMap.put(s, answer);
            return answer;
        }
    }
}