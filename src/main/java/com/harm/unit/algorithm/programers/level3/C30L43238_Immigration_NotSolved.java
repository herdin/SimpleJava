package com.harm.unit.algorithm.programers.level3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
이분탐색, 미해결
* */
public class C30L43238_Immigration_NotSolved {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(C30L43238_Immigration_NotSolved.class);
        int[][] datas = {
            {6}, {7, 10}, {28},
        };
        for(int i=0; i<datas.length;) {
            int n = datas[i++][0];
            int[] times = datas[i++];
            int expect = datas[i++][0];
            logger.debug("n {}, times {}, expect {}, solution {}", n, Arrays.toString(times), expect, new Solution().solution(n, times));
        }
    }
    //오전 11:20 토요일 2021-01-16

    static class Solution {
//        Logger logger = LoggerFactory.getLogger(C30L43238_Immigration.Solution.class);
        public long solution(int n, int[] times) {
            /*
            n <= 1,000,000,000
            1 <= times[x] <= 1,000,000,000
            1 <= times.length <= 100,000
            */
            long maxSpentTime = 1_000_000_000L*times.length;
            long mid = maxSpentTime/2;
            long left = 0, right = maxSpentTime;
            Set<Long> answers = new HashSet<>();
            while(true) {

//                long sum = IntStream.of(times).parallel().mapToLong(time -> div/time).sum();
                long sum = 0L;
                for(int time : times) {
                    sum += mid/time;
                    if(sum > n) break;
                }

//                logger.debug("left {}, righ {}, mid {}, sum {}", left, right, mid, sum);
                if (sum < n) {
                    left = mid;
                } else {
                    right = mid;
                }
//                if(sum == n) answers.add(mid);
                if(right - left == 1) break;
                mid = (left + right)/2;
            }
            return mid; //answers.stream().min(Comparator.comparing(aLong -> aLong)).get();
        }
    }

    static class SolutionOld {
        Logger logger = LoggerFactory.getLogger(C30L43238_Immigration_NotSolved.Solution.class);
        public long solution(int n, int[] times) {
            int peopleCount = n;
            int[] sortedTimes = Arrays.stream(times).sorted().toArray();
            int[] spendTimes = Arrays.copyOf(sortedTimes, sortedTimes.length);

            long spentTimes = 0;
            while(peopleCount > 0) {
                int minWaitTime = spendTimes[0];
                logger.debug("min wait time {}", minWaitTime);

                for(int i=0; i<spendTimes.length; i++) {
                    if(peopleCount > 0) {
                        spendTimes[i] -= minWaitTime;
                        if(spendTimes[i] == 0) {
                            spendTimes[i] = sortedTimes[i];
                            peopleCount--;
                        }
                    }
                }

                int targetIndex = 0;
                for(int i=1; i<spendTimes.length; i++) {
                    if(spendTimes[targetIndex] > spendTimes[i]) {
                        int targetTime = spendTimes[targetIndex];
                        spendTimes[targetIndex] = spendTimes[i];
                        spendTimes[i] = targetTime;

                        targetTime = sortedTimes[targetIndex];
                        sortedTimes[targetIndex] = sortedTimes[i];
                        sortedTimes[i] = targetTime;

                        targetIndex = i;
                    } else {
                        break;
                    }
                }

                logger.debug("people count {}, spend time {}", peopleCount, spendTimes);
                spentTimes += minWaitTime;
            }

            return spentTimes;
        }
    }
}