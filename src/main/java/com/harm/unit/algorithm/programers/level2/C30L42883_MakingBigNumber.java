package com.harm.unit.algorithm.programers.level2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * 끝
 * 스택에 있는 수보다 현재 수가 더 크면 스택에서 빼내고, 현재 수를 스택에 넣는 방식이 인기가 좋다.
 * for(int i=0; i<number.length(); i++) {
 *     char digit = number.charAt(i);
 *     while(!stack.isEmpty() && stack.peek() < digit && k-- > 0) {
 *         stack.pop();
 *     }
 *     stack.push(digit);
 * }
 */
public class C30L42883_MakingBigNumber {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(C30L42883_MakingBigNumber.class);
        class Data {
            final String number;
            final int k;
            final String expects;

            public Data(String number, int k, String expects) {
                this.number = number;
                this.k = k;
                this.expects = expects;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "number='" + number + '\'' +
                        ", k=" + k +
                        ", expects='" + expects + '\'' +
                        '}';
            }
        }
        ArrayList<Data> dataList = new ArrayList<>();
        dataList.add(new Data("1924", 2, "94"));
        dataList.add(new Data("1231234", 3, "3234"));
        dataList.add(new Data("4177252841", 4, "775841"));
        for(Data data : dataList) {
            logger.debug("data -> {}, result -> {}", data, new Solution().solution(data.number, data.k));
        }
    }
    /**
     * 무식하게 풀어보자.
     * 모든 index 에서 빼
     * */
    static class Solution {
//        Logger logger = LoggerFactory.getLogger(C30L42883_MakingBigNumber.Solution.class);
        public String solution(String number, int k) {
            char[] digits = new char[10];
            IntStream.range(0, digits.length).forEach(i -> digits[i] = (char)((48+9)-i));
            StringBuffer result = new StringBuffer();
            String workNumber = number;
            int targetNumberLength = number.length()-k;
            while(result.length() < number.length()-k) {
                int startIndex = getStartIndex(workNumber, digits, targetNumberLength);
                result.append(""+workNumber.charAt(startIndex));
                workNumber = workNumber.substring(startIndex+1);
                targetNumberLength--;
//                logger.debug("start index -> {}, result -> {}", startIndex, result.toString());
            }
            return result.toString();
        }
        int getStartIndex(String number, char[] digits, int length) {
            for(int i=digits.length; i>=0; i--) {
                int maxIndex = getMaxIndex(number, digits, i); //4 -> 0 1 2 3 4 5 6 7 8 9 10 11 12, 13-4 = 9
                int numberCandidatesLength = number.length()-maxIndex;
                if(numberCandidatesLength >= length) {
                    return maxIndex;
                }
            }
            return 0;
        }
        int getMaxIndex(String number, char[] digits, int maxUnder) {
            for(int i=digits.length-maxUnder; i<digits.length; i++) {
                int maxIndex = number.indexOf(digits[i]);
                if(maxIndex >= 0) return maxIndex;
            }
            return 0;
        }
    }
}


