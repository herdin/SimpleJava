package com.harm.unit.algorithm.leetcode.easy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.IntStream;
/** Easy
 * 끝
 * */
public class Problems01TwoSum {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems01TwoSum.class);
//        int[] nums = {2,7,11,15};
//        int target = 9;
        int[] nums = {3, 2, 4};
        int target = 6;
        logger.debug("nums {}, target {}, solution {}", Arrays.toString(nums), target, Arrays.toString(new Solution().twoSum(nums, target)));
    }
    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            IntStream.range(0, nums.length).parallel().forEach(index -> map.put(nums[index], index));
            for(int i=0; i<nums.length; i++) {
                int num = nums[i];
                if(map.containsKey(target-num)) {
                    int otherIndex = map.get(target-num);
                    if(i == otherIndex) continue;
                    return new int[]{i, otherIndex};
                }
            }
            return new int[]{};
        }
    }
    static class SolutionOld {
        Logger logger = LoggerFactory.getLogger(Problems01TwoSum.SolutionOld.class);
        public int[] twoSum(int[] nums, int target) {
            int [] result = null;
            HashMap<Integer, Integer> map = new HashMap<>();

            for(int i=0; i<nums.length; i++) {
                int rest = target - nums[i];
                if(map.get(rest) != null) {
                    result = new int[]{i, map.get(rest)};
                    logger.debug("result {}, {}", result[0], result[1]);
                    return result;
                } else {
                    map.put(nums[i], i);
                }
            }
            return result;
        }
    }
}
