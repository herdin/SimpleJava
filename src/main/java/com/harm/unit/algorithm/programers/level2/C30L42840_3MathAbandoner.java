package com.harm.unit.algorithm.programers.level2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.IntStream;

public class C30L42840_3MathAbandoner {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(C30L42840_3MathAbandoner.class);
        int[][] answers = {{1,2,3,4,5}, {1,3,2,4,2}};
        for(int[] answer : answers) {
            logger.debug("answer -> {}, result -> {}", answer, new Solution().solution(answer));
            Solution.ABANDONER_PATTERN.resetAll();
        }
    }
    static class Solution {
//        Logger logger = LoggerFactory.getLogger(C30L42840_3MathAbandoner.Solution.class);
        public int[] solution(int[] answers) {
            int[] scores = new int[ABANDONER_PATTERN.values().length];
            for(int answer : answers) {
                for(ABANDONER_PATTERN abandonerPattern : ABANDONER_PATTERN.values()) {
                    scores[abandonerPattern.abandonerIndex()] += (answer == abandonerPattern.nextAnswer())? 1:0;
//                    logger.debug("answer {}, abandoner pattern {}, score {}", answer, abandonerPattern, Arrays.toString(scores));
                }
            }
//            logger.debug("scores {}", scores);
            int max = Arrays.stream(scores).max().orElse(0);
            int[] answer = IntStream.range(0, scores.length).filter(index -> max == scores[index]).map(index -> index+1).sorted().toArray();
            return answer;
        }
        enum ABANDONER_PATTERN {
            ABANDONER_1(0, new int[]{1, 2, 3, 4, 5}),
            ABANDONER_2(1, new int[]{2, 1, 2, 3, 2, 4, 2, 5}),
            ABANDONER_3(2, new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5}),
            ;
            final int abandonerIndex;
            final int[] pattern;
            int patternIndex;
            ABANDONER_PATTERN(int abandonerIndex, int[] pattern) {
                this.abandonerIndex = abandonerIndex;
                this.pattern = pattern;
                this.patternIndex = -1;
            }
            public int abandonerIndex() { return abandonerIndex; }
            public int nextAnswer() {
                patternIndex++;
                if(patternIndex >= pattern.length) {
                    patternIndex = 0;
                }
                return pattern[patternIndex];
            }

            @Override
            public String toString() {
                return "ABANDONER_PATTERN{" +
                        "abandonerIndex=" + abandonerIndex +
                        ", pattern=" + Arrays.toString(pattern) +
                        ", patternIndex=" + patternIndex +
                        '}';
            }
            public void reset() {
                patternIndex = 0;
            }
            public static void resetAll() {
                for(ABANDONER_PATTERN abandonerPattern : ABANDONER_PATTERN.values()) {
                    abandonerPattern.reset();
                }
            }
        }
    }
}