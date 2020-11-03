package com.harm.unit.algorithm.programers.skillcheck;

public class Level01_01 {
    public static void main(String[] args) {
        String phone_number = "01033334444"; //"*******4444"
//        String phone_number = "027778888"; //"*****8888"
        System.out.println(new Solution().solution(phone_number));
    }
    static class Solution {
        public String solution(String phone_number) {
            StringBuffer sb = new StringBuffer();
            int length = phone_number.length();

            for(int i=0; i<length; i++) {
                if (i < length-4) {
                    sb.append("*");
                } else {
                   sb.append(phone_number.charAt(i));
                }
            }
            return sb.toString();
        }
    }
}
