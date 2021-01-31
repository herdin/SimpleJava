package com.harm.unit.algorithm.leetcode.medium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 하긴했는데, 결과가 너무 구림..
 *  */
public class Problems5LongestPalindromicSubstring {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems5LongestPalindromicSubstring.class);
        class Data {
            final String s;
            final String expect;

            public Data(String s, String expect) {
                this.s = s;
                this.expect = expect;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "s='" + s + '\'' +
                        ", expect='" + expect + '\'' +
                        '}';
            }
        }
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data("babad", "bab"));
        dataList.add(new Data("cbbd", "bb"));
        dataList.add(new Data("a", "a"));
        dataList.add(new Data("ac", "a"));
        dataList.add(new Data("abb", "bb"));
        dataList.add(new Data("abcda", "a"));
        dataList.add(new Data("ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg", "a"));
        for(Data data : dataList) {
            long start = System.currentTimeMillis();
//            String palindrome = new Problems5LongestPalindromicSubstring.Solution().longestPalindrome(data.s);
            String palindrome = new Problems5LongestPalindromicSubstring.FastestSolution().longestPalindrome(data.s);
            long end = System.currentTimeMillis();
            logger.debug("data {}, solution {}, duration {}", data.toString(), palindrome, (end-start));

        }

    }
    static class Solution {
        public String longestPalindrome(String s) {
            if(s.length() == 1) return s;
            if(s.length() == 2) return (s.charAt(0) == s.charAt(1))? s:"" + s.charAt(0);
            int maxLength = 1;
            String maxPalindrome = s.substring(0, 1);
            for(int i=0; i<s.length(); i++) {
                String targetS = s.substring(i);
                for(int j=targetS.length(); j>1 && j>maxLength; j--) {
                    String checkS = targetS.substring(0, j);
                    if(isPalindrome(checkS)) {
                        maxLength = checkS.length();
                        maxPalindrome = checkS;
                        break;
                    }
                }
            }

            return maxPalindrome;
        }
        boolean isPalindrome(String s) {
            boolean palindrome = true;
            for(int i=0; i<s.length()/2; i++) {
                if(s.charAt(i) != s.charAt(s.length()-1-i)) {
                    palindrome = false;
                    break;
                }
            }
            return palindrome;
//            return new StringBuffer(s).reverse().toString().equals(s);
        }
    }

    static class FastestSolution {
        int start = 0, end = 0;
        public String longestPalindrome(String s) {
            if (s.length() < 2) {
                return s;
            }
            char[] c = s.toCharArray();
            longestPallindromeAt(c, 0);
            return s.substring(start, end + 1);
        }

        private void longestPallindromeAt(char[] c, int p) {
            int a = p;
            int b = p;
            int n = c.length;
            if (p == n - 1 || (n - p) < (end - start + 1)/2) {
                return;
            }
            while (b < n - 1 && c[b] == c[b + 1]) {
                b++;
            }
            p = b;
            while (a > 0 && b < n - 1 && c[a - 1] == c[b + 1]) {
                a--;
                b++;
            }
            if (b - a > end - start) {
                end = b;
                start = a;
            }
            longestPallindromeAt(c, p + 1);
        }
    }
}