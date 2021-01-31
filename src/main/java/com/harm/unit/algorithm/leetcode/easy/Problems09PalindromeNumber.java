package com.harm.unit.algorithm.leetcode.easy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 끝
 * */
public class Problems09PalindromeNumber {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems09PalindromeNumber.class);
        int number = 10022201;
        logger.debug("1/10 = {}", 1/10);
        logger.debug("is palindrome ? {} -> {}", number, new Solution().isPalindrome(number));
    }
    static class Solution {
        Logger logger = LoggerFactory.getLogger(Solution.class);
        public boolean isPalindrome(int x) {
            if(x < 0) return false;
            String xStr = Integer.toString(Math.abs(x));
            int xLength = xStr.length();
            if(xLength < 2) return true;
            while(xStr.length() > 1) {
                logger.debug("x -> {}", xStr);
                /*
                123 3/2 = 1
                1234 4/2 = 2
                */
                if(!xStr.substring(0, 1).equals(xStr.substring(xStr.length()-1))) {
                    return false;
                }
                xStr = xStr.substring(1, xStr.length()-1);
            }
            return true;
        }
    }//Solution

    static class FastestSolution {
        public boolean isPalindrome(int x) {
            if(x<0){
                return false;
            }
            int copy =x;
            int digit,rev=0;
            while(x>0){
                digit=x%10;
                rev=rev*10+digit;
                x=x/10;
            }

            if(rev==copy){
                return true;
            }
            else{
                return false;
            }
        }
    }//FastestSolution
}