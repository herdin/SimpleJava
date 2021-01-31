package com.harm.unit.lettcode;

import com.harm.unit.UnitRunner;
import com.harm.unit.algorithm.leetcode.easy.Problems01TwoSum;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;

public class Problems01TwoSumTest {
    private Logger logger = LoggerFactory.getLogger(Problems01TwoSumTest.class);
    @Test
    public void test() {
        int[][] nums = {
            {1, 2, 3, 7, 11, 15},
            {2, 7, 11, 15},
        };
        int[] targets = {
            9,
            9
        };

        int[][] answers = {
            {1, 3},
            {0, 1},
        };

        boolean success = true;
        for(int i=0; i<targets.length; i++) {
            int [] result = (int[])UnitRunner.start(new Problems01TwoSum(), new Object[]{nums[i], targets[i]});
            Arrays.sort(result);
            this.logger.debug("TEST CASE {}, ANSWER {}, RESULT {}", i, answers[i], result);
            success = success && (Arrays.equals(answers[i], result));
        }


        org.junit.Assert.assertThat(success, is(true));
    }
}
