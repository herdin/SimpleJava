package com.harm.unit.algorithm.programers.level2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class C30L42839_FindPrimeNumber {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(C30L42839_FindPrimeNumber.class);
        class Data {
            final String numbers;
            final int expect;
            public Data(String numbers, int expect) {
                this.numbers = numbers;
                this.expect = expect;
            }
            @Override
            public String toString() {
                return "Data{" +
                        "numbers='" + numbers + '\'' +
                        ", expect=" + expect +
                        '}';
            }
        }
        ArrayList<Data> datas = new ArrayList<>();
        datas.add(new Data("17", 3));
        datas.add(new Data("011", 2));
        for(Data data : datas) {
            logger.debug("data {}, solution {}", data, new Solution().solution(data.numbers));
        }
    }
    //오전 09:35 일요일 2021-01-17
    static class Solution {
//        Logger logger = LoggerFactory.getLogger(C30L42839_FindPrimeNumber.Solution.class);
        public int solution(String numbers) {
            int[] intNumbers = Arrays.stream(numbers.split("")).mapToInt(Integer::parseInt).toArray();
            Arrays.sort(intNumbers);
            Set<Integer> makeNumberSet = new HashSet<>();

            makeNumberStr(makeNumberSet, intNumbers, intNumbers.length, "");
//            logger.debug("make number set -> {}", makeNumberSet);
            List<Integer> makeNumberList = makeNumberSet.stream().map(Integer::valueOf).sorted().collect(Collectors.toList());
            int answer = 0;
            for(int makeNumber : makeNumberList) {
                if(isPrimeNumber(makeNumber)) {
                    answer++;
                }
            }
            return answer;
        }
        void makeNumberStr(Set<Integer> makeNumberSet, int[] intNumbers, int makeNumberStrLength, String result) {
            if(result.length() <= makeNumberStrLength) {
                for(int i=0; i<intNumbers.length; i++) {
                    String newResult = result + intNumbers[i];
                    makeNumberSet.add(Integer.parseInt(newResult));
                    int[] restIntNumbers = new int[intNumbers.length-1];
                    int restIndex = 0;
                    for(int j=0; j<intNumbers.length; j++) {
                        if(i==j) continue;
                        restIntNumbers[restIndex++] = intNumbers[j];
                    }
                    makeNumberStr(makeNumberSet, restIntNumbers, makeNumberStrLength, newResult);
                }
            }
        }
        boolean isPrimeNumber(int n) {
            if(n < 2) return false;
            boolean isPrime = IntStream.range(2, n).noneMatch(i -> n%i == 0);
            return isPrime;
        }
    }
}
