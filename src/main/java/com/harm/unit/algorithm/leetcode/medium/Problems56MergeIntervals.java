package com.harm.unit.algorithm.leetcode.medium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 오후 14:45 화요일 2021-01-26
 * 오후 16:04 화요일 2021-01-26
 *  */
public class Problems56MergeIntervals {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems56MergeIntervals.class);
        class Data {
            final int[][] intervals;
            final int[][] expects;

            public Data(int[][] intervals, int[][] expects) {
                this.intervals = intervals;
                this.expects = expects;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "intervals={" + Arrays.stream(intervals).map(interval -> Arrays.toString(interval)).collect(Collectors.joining(", ")) +
                        "}, expects={" + Arrays.stream(expects).map(expect -> Arrays.toString(expect)).collect(Collectors.joining(", ")) +
                        "}}";
            }
        }
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data(new int[][]{{1,3},{2,6},{8,10},{15,18}}, new int[][]{{1,6},{8,10},{15,18}}));
        dataList.add(new Data(new int[][]{{1,4},{4,5}}, new int[][]{{1,5}}));
        dataList.add(new Data(new int[][]{{1,4},{0,1}}, new int[][]{{0,4}}));
        dataList.add(new Data(new int[][]{{1,4},{0,4}}, new int[][]{{0,4}}));
        for(Data data : dataList) {
            logger.debug("data {}, solution {}", data.toString(), Arrays.stream(new Problems56MergeIntervals.Solution().merge(data.intervals)).map(solution -> Arrays.toString(solution)).collect(Collectors.joining(", ")));
        }

    }
    static class Solution {
        public int[][] merge(int[][] intervals) {
            int[][] copyOfIntervals = Arrays.copyOf(intervals, intervals.length);
            Arrays.sort(copyOfIntervals, (o1, o2) -> {
                if(o1[0] < o2[0]) {
                    return -1;
                } else if(o1[0] == o2[0]) {
                    return 0;
                } else {
                    return 1;
                }
            });
            final int INDEX_START = 0;
            final int INDEX_END = 1;
            int currentStart = -1;
            int currentEnd = -1;
            class Interval {
                int start;
                int end;

                public Interval(int start, int end) {
                    this.start = start;
                    this.end = end;
                }
            }
            List<Interval> intervalList = new ArrayList<>();
            for(int i=0; i<copyOfIntervals.length; i++) {
                if(currentStart == -1) {
                    currentStart = copyOfIntervals[i][INDEX_START];
                    currentEnd = copyOfIntervals[i][INDEX_END];
                    continue;
                }
                if(currentEnd >= copyOfIntervals[i][INDEX_START]) {
                    if(currentEnd < copyOfIntervals[i][INDEX_END]) {
                        currentEnd = copyOfIntervals[i][INDEX_END];
                    }
                } else {
                    intervalList.add(new Interval(currentStart, currentEnd));
                    currentStart = copyOfIntervals[i][INDEX_START];
                    currentEnd = copyOfIntervals[i][INDEX_END];
                }
            }
            if(intervalList.size() == 0 || (intervalList.size() > 0 && intervalList.get(intervalList.size()-1).start != currentStart)) {
                intervalList.add(new Interval(currentStart, currentEnd));
            }

            int[][] results = new int[intervalList.size()][];
            for(int i=0; i<intervalList.size(); i++) {
                results[i] = new int[2];
                results[i][0] = intervalList.get(i).start;
                results[i][1] = intervalList.get(i).end;
            }

            return results;
        }
    }
}