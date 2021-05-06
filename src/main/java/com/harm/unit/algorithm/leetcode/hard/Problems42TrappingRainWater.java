package com.harm.unit.algorithm.leetcode.hard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 푸는중  ㅠㅠ
 * 결국 내가 푸는 방식으로 안되고
 * 솔루션을 보아버렷당...
 * 끝 ㅠㅠ
 *  */
public class Problems42TrappingRainWater {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems42TrappingRainWater.class);
        class Data {
            final int[] height;
            final int expect;
            public Data(int[] height, int expect) {
                this.height = height;
                this.expect = expect;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "height=" + Arrays.toString(height) +
                        ", expect=" + expect +
                        '}';
            }
        }
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}, 6));
        dataList.add(new Data(new int[]{4,2,0,3,2,5}, 9));
        dataList.add(new Data(new int[]{4,2,3}, 1));

        for(Data data : dataList) {
            logger.debug("data {}, solution {}", data.toString(), new Problems42TrappingRainWater.Solution().trap(data.height));
        }

    }
    static class Solution {
//        Logger logger = LoggerFactory.getLogger(Problems42TrappingRainWater.Solution.class);
        public int trap(int[] height) {
            int trappedWater = 0;
            if(height.length <= 2) {
                return trappedWater;
            }
//            logger.debug("height {}", Arrays.toString(height));
            int[] leftMax = new int[height.length];
            leftMax[0] = height[0];
            int[] rightMax = new int[height.length];
            int lastIndex = height.length-1;
            rightMax[lastIndex] = height[lastIndex];
            for(int i=0; i<height.length; i++) {
                if(i > 0) leftMax[i] = Math.max(height[i], leftMax[i - 1]);
                int ri = lastIndex-i;
                if(ri < lastIndex) rightMax[ri] = Math.max(height[ri], rightMax[ri + 1]);
            }
//            logger.debug("left max {}", Arrays.toString(leftMax));
//            logger.debug("right max {}", Arrays.toString(rightMax));

            for(int i=0; i<height.length; i++) {
                int minHeight = Math.min(leftMax[i], rightMax[i]);
                int trappedWaterCandidate = minHeight - height[i];
                if(trappedWaterCandidate > 0) trappedWater += trappedWaterCandidate;
            }
            return trappedWater;
        }
    }
    static class SolutionOld {
        Logger logger = LoggerFactory.getLogger(Problems42TrappingRainWater.SolutionOld.class);
        public int trap(int[] height) {
            int trappedWater = 0;
            Map<Integer, Integer> trappedDuratioin = new HashMap<>();
            int firstIndex = Arrays.stream(height).filter(h -> h > 0).findFirst().orElseGet(() -> -1);
            if(firstIndex == -1) {
                return trappedWater;
            }

            for(int from=firstIndex; from<height.length; from++) {
                if(height[from] <= height[from+1]) {
                    continue;
                }
                for(int to=from+1; to<height.length; to++) {
                    if(height[from] <= height[to]) {
                        trappedDuratioin.put(from, to);
                        from = to-1;
                        break;
                    }
                }
                logger.debug("from {}, trapped duration {}", from, trappedDuratioin);
            }
            trappedWater = trappedDuratioin.entrySet().stream().parallel().mapToInt(entry -> {
                int trappedWaterSplit = 0;
                int from = entry.getKey();
                int to = entry.getValue();
                int smallerIndex = (height[from] < height[to])? from:to;
                for(int i=from+1; i<to; i++) {
                    if(height[i] < height[smallerIndex]) {
                        trappedWaterSplit += height[smallerIndex] - height[i];
                    }
                }
                return trappedWaterSplit;
            }).sum();

            return trappedWater;
        }
    }
}