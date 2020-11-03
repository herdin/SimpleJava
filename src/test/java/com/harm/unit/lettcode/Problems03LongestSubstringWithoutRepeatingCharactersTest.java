package com.harm.unit.lettcode;

import com.harm.unit.UnitRunner;
import com.harm.unit.algorithm.leetcode.Problems03LongestSubstringWithoutRepeatingCharacters;
import org.junit.Test;

public class Problems03LongestSubstringWithoutRepeatingCharactersTest {

    @Test
    public void test() {

        String[] inputs = {
            "abcabcbb",
            "bbbbb",
            "pwwkew",
        };

        int[] answers = {
            3,
            1,
            3
        };

        boolean success = true;
        for(int i=0; i<answers.length; i++) {
            int result = (int)UnitRunner.start(new Problems03LongestSubstringWithoutRepeatingCharacters(), new Object[]{inputs[i]});
            success = success && (result == answers[i]);
        }

        org.junit.Assert.assertThat(success, org.hamcrest.core.Is.is(true));
    }
}
