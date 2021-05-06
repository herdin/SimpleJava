package com.harm.unit.algorithm.leetcode.medium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Stream;

/**
 */
public class Problems03LongestSubstringWithoutRepeatingCharacters2 {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems03LongestSubstringWithoutRepeatingCharacters2.class);
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
        dataList.add(new Data("abcabcbb", 3));
        dataList.add(new Data("bbbbb", 1));
        dataList.add(new Data("pwwkew", 3));
        dataList.add(new Data("", 0));
        for(Data data : dataList) {
            logger.debug("data {}, solution {}", data, new Problems03LongestSubstringWithoutRepeatingCharacters2.Solution().lengthOfLongestSubstring(data.s));
        }

    }
    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            int maxLength = 0;
            for(int i=0; i<s.length(); i++) {
                String subS = s.substring(i);
                if(subS.length() < maxLength) break;
                int length = longestLengthFromStart(subS);
                maxLength = Math.max(length, maxLength);
            }
            return maxLength;
        }
        int longestLengthFromStart(String s) {
            Set<Character> strSet = new HashSet<>();
            for(int i=0; i<s.length(); i++) {
                if(strSet.contains(s.charAt(i))) break;
                strSet.add(s.charAt(i));
            }
            return strSet.size();
        }
    }

}
