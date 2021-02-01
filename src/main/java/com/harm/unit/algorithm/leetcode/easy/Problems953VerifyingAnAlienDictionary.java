package com.harm.unit.algorithm.leetcode.easy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/** Easy
 * 끝
 *  */
public class Problems953VerifyingAnAlienDictionary {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems953VerifyingAnAlienDictionary.class);
        class Data {
            final String[] words;
            final String order;
            final boolean expect;

            public Data(String[] words, String order, boolean expect) {
                this.words = words;
                this.order = order;
                this.expect = expect;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "words=" + Arrays.toString(words) +
                        ", order='" + order + '\'' +
                        ", expect=" + expect +
                        '}';
            }
        }
        List<Data> dataList = new ArrayList<>();
//        dataList.add(new Data(new String[]{"hello","leetcode"}, "hlabcdefgijkmnopqrstuvwxyz", true));
//        dataList.add(new Data(new String[]{"word","world","row"}, "worldabcefghijkmnpqstuvxyz", false));
//        dataList.add(new Data(new String[]{"apple","app"}, "abcdefghijklmnopqrstuvwxyz", false));
//        dataList.add(new Data(new String[]{"kuvp","q"}, "ngxlkthsjuoqcpavbfdermiywz", true));
        dataList.add(new Data(new String[]{"hello","hello"}, "abcdefghijklmnopqrstuvwxyz", true));


        for(Data data : dataList) {
            logger.debug("data {}, solution {}", data, new Problems953VerifyingAnAlienDictionary.Solution().isAlienSorted(data.words, data.order));
        }

    }
    static class Solution {
        public boolean isAlienSorted(String[] words, String order) {
            Map<String, Integer> orderMap = new HashMap<>();
            Arrays.stream(order.split("")).parallel().forEach(str -> orderMap.put(str, order.indexOf(str)));
            for(int i=0; i<words.length-1; i++) {
                String current = words[i];
                String next = words[i+1];
                if(compareTwoAlienWords(current, next, orderMap) > 0) {
                    return false;
                }
            }
            return true;
        }
        /**
         * one >> another -> -1
         * one == another -> 0
         * another >> one -> 1
         * */
        int compareTwoAlienWords(String one, String another, Map<String, Integer> orderMap) {
            int length = Math.min(one.length(), another.length());
            for(int i=0; i<length; i++) {
                int oneOrder = orderMap.get(""+one.charAt(i));
                int anotherOrder = orderMap.get(""+another.charAt(i));
                if(oneOrder < anotherOrder) return -1;
                else if(oneOrder > anotherOrder) return 1;
                else continue;
            }
            if(length == one.length() && length == another.length()) return 0;
            else if(length == one.length()) return -1;
            else return 1;
        }
    }
}