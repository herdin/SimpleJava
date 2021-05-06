package com.harm.unit.algorithm.leetcode.hard;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 오후 16:07 일요일 2021-01-24
 *
 * 시간초과가난다 ㅠㅠ
 *  */
public class Problems829ConsecutiveNumbersSum {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems829ConsecutiveNumbersSum.class);
        class Data {
            final int N;
            final int expect;

            public Data(int n, int expect) {
                N = n;
                this.expect = expect;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "N=" + N +
                        ", expect=" + expect +
                        '}';
            }
        }
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data(5, 2));
        dataList.add(new Data(9, 3));
        dataList.add(new Data(15, 4));
        dataList.add(new Data(333764327, -1));
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        for(Data data : dataList) {
//            stopwatch.start();
//            logger.debug("data {}, solution old {}, time taken {}", data.toString(), new Problems829ConsecutiveNumbersSum.SolutionOld2().consecutiveNumbersSum(data.N), stopwatch.stop().toString());
            stopwatch.start();
            logger.debug("data {}, solution {}, time taken {}", data.toString(), new Problems829ConsecutiveNumbersSum.Solution().consecutiveNumbersSum(data.N), stopwatch.stop().toString());
        }

    }

    static class Solution {
        public int consecutiveNumbersSum(int N) {
            int answer = 0;
            int tempSum = 1;
            int start = 1;
            int end = 1;
            while(end <= Math.sqrt(2*N)) {
                if(tempSum == N) {
                    answer++;
                    end++;
                    tempSum += end;
                } else if(tempSum < N) {
                    end++;
                    tempSum += end;
                } else { //N < tempSum
                    tempSum -= start;
                    start++;
                }
            }
            return answer+1;
        }
    }

    static class SolutionOld2 {
        public int consecutiveNumbersSum(int N) {
            int answer = 1;
            for(int i=1; i<=N/2+1; i++) {
                for(int j=i+1; j<=N/2+1; j++) {
                    int sum = consecutiveSum(i, j);
                    if(sum == N) {
                        answer++;
                    }
                }
            }
            return answer;
        }
        int consecutiveSum(int start, int end) {
            return (start+end)*(end-start+1)/2;
        }
    }

    static class SolutionOld1 {
        public int consecutiveNumbersSum(int N) {
            int answer = 1;
            for(int i=1; i<N; i++) {
                for(int j=i+1; j<N; j++) {
                    int sum = consecutiveSum(i, j);
                    if(sum == N) {
                        answer++;
                    }
                }
            }
            return answer;
        }
        int consecutiveSum(int start, int end) {
            return (start+end)*(end-start+1)/2;
        }
    }
}