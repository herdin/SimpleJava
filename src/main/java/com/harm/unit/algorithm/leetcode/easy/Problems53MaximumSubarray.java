package com.harm.unit.algorithm.leetcode.easy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.IntStream;

/**
 *
 * */
public class Problems53MaximumSubarray {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems53MaximumSubarray.class);
        class Data {
            final int[] nums;
            final int expect;

            public Data(int[] nums, int expect) {
                this.nums = nums;
                this.expect = expect;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "nums=" + Arrays.toString(nums) +
                        ", expect=" + expect +
                        '}';
            }
        }
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data(new int[]{-2,1,-3,4,-1,2,1,-5,4}, 6));
        for(Data data : dataList) {
            logger.debug("data {}, solution {}", data.toString(), new Problems53MaximumSubarray.Solution().maxSubArray(data.nums));
        }
    }
    static class Solution {
        public int maxSubArray(int[] nums) {
            int currentSum = nums[0];
            int maxSum = nums[0];
            for(int i=1; i<nums.length; i++) {
                currentSum = Math.max(nums[i], currentSum + nums[i]);
                maxSum = Math.max(currentSum, maxSum);
            }
            return maxSum;
        }
    }
    /**
     * 무식하게 풀어 보았다. 성공은했지만 의미는 크게 없다. 성능도 구리고..
     * */
    static class Solution1 {
        public int maxSubArray(int[] nums) {
            int[] sums = new int[nums.length];
            IntStream.range(0, sums.length).parallel().forEach(i -> sums[i] = Integer.MIN_VALUE);
            int maxSum = Integer.MIN_VALUE;
            for(int i=0; i<nums.length; i++) {
                int sum = 0;
                for(int j=i; j<nums.length; j++) {
                    sum += nums[j];
                    sums[i] = Math.max(sums[i], sum);
                }
                maxSum = Math.max(sums[i], maxSum);
            }
            return maxSum;
        }
    }
}