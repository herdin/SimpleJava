package com.harm.unit.algorithm.leetcode.medium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * O(n) 이 도저히 안나와서 살짝 솔루션을 보았다 ㅠㅠ
 *  */
public class Problems238ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems238ProductOfArrayExceptSelf.class);
        class Data {
            final int[] nums;
            final int[] expects;

            public Data(int[] nums, int[] expects) {
                this.nums = nums;
                this.expects = expects;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "nums=" + Arrays.toString(nums) +
                        ", expects=" + Arrays.toString(expects) +
                        '}';
            }
        }
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data(new int[]{1,2,3,4}, new int[]{24,12,8,6}));
        for(Data data : dataList) {
            logger.debug("data {}, solution {}", data.toString(), Arrays.toString(new Problems238ProductOfArrayExceptSelf.Solution().productExceptSelf(data.nums)));
        }

    }
    static class Solution {
        public int[] productExceptSelf(int[] nums) {
            int[] outputs = new int[nums.length];
            if(nums.length == 1) {
                return outputs;
            }

            int[] leftProduct = new int[nums.length];
            leftProduct[0] = 1;
            int[] rightProduct = new int[nums.length];
            rightProduct[rightProduct.length-1] = 1;
            for(int i=1; i<nums.length; i++) {
                int ri = nums.length-1-i;
                leftProduct[i] = leftProduct[i-1]*nums[i-1];
                rightProduct[ri] = rightProduct[ri+1]*nums[ri+1];
            }
            for(int i=0; i<nums.length; i++) {
                outputs[i] = leftProduct[i]*rightProduct[i];
            }
            return outputs;
        }
    }

}