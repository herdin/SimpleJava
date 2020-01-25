package com.harm.unit.leetcode;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class Problems01TwoSum implements Unit {
    private final Logger logger = LoggerFactory.getLogger(Problems01TwoSum.class);
    @Override
    public Object execute(Object[] obj) throws Exception {
        int[] nums = (int[])obj[0];
        int target = (int)obj[1];

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
