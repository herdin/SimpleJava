package com.harm.unit.algorithm.programers.level3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.BiFunction;

public class C30L42895_ExpressionWithN {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(C30L42895_ExpressionWithN.class);
        int[][] problemSets = {{5, 12, 4}, {5, 31168, -1}, {2, 11, 3}, {5, 5, 1}};
        for(int[] problemSet : problemSets) {
            logger.debug("problem set {}, solution {}", Arrays.toString(problemSet), new Solution().solution(problemSet[0], problemSet[1]));
        }
    }
    static class Solution {
        enum MAKE_NUMBER {
            ADD((source, number) -> number + source),
            MINUS((source, number) -> number - source),
            MINUS_REVERSE((source, number) -> source - number),
            MULTIFLY((source, number) -> number * source),
            DIVIDE((source, number) -> number / ((source == 0)? 1:source)),
            DIVIDE_REVERSE((source, number) -> source / ((number == 0)? 1:number)),
            ;
            public static int CONCAT(int source, int times) {
                StringBuffer sb = new StringBuffer();
                for(int i=0; i<times; i++) {
                    sb.append(source);
                }
                return Integer.parseInt(sb.toString());
            }
            BiFunction<Integer, Integer, Integer> biFunction;
            MAKE_NUMBER(BiFunction<Integer, Integer, Integer> biFunction) {
                this.biFunction = biFunction;
            }
            public int getNumber(int source, int number) {
                return biFunction.apply(source, number);
            }

        }
        public int solution(int N, int number) {
            Map<Integer, Set<Integer>> results = new HashMap<>();
            Logger logger = LoggerFactory.getLogger(C30L42895_ExpressionWithN.Solution.class);
            for(int usingCount=1; usingCount<9; usingCount++) {
                int concat = MAKE_NUMBER.CONCAT(N, usingCount);
                if(concat == number) return usingCount;
                putNumberToResults(results, usingCount, concat);

                int resultsSize = results.size();
                for(int usedCount=1; usedCount<resultsSize; usedCount++) {
                    Set<Integer> madeNumberList = results.get(usedCount);
                    for(int otherUsedCount=1; otherUsedCount<resultsSize; otherUsedCount++) {
                        Set<Integer> otherMadeNumberList = results.get(otherUsedCount);
                        for(int madeNumber : madeNumberList) {
                            for(int otherMadeNumber : otherMadeNumberList) {
                                for(MAKE_NUMBER makeNumber : MAKE_NUMBER.values()) {
                                    int newNumber = makeNumber.getNumber(otherMadeNumber, madeNumber);
                                    int foundUsingCount = usedCount+otherUsedCount;
                                    if(newNumber == number) return (foundUsingCount > 8)? -1: foundUsingCount;
                                    putNumberToResults(results, foundUsingCount, newNumber);
                                }
                            }
                        }
                    }
                }
                logger.debug("current using count {}", usingCount);
                results.keySet().stream().forEach(key -> logger.debug("current result -> {} -> {}", key, results.get(key)));
            }
            return -1;
        }
        void putNumberToResults(Map<Integer, Set<Integer>> results, int usingCount, int madeNumber) {
            if(!results.containsKey(usingCount)) {
                results.put(usingCount, new HashSet<>());
            }
            results.get(usingCount).add(madeNumber);
        }
    }
}
