package com.harm.unit.algorithm.programers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class C30L99999_TemplateSolution {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(C30L99999_TemplateSolution.class);
        class Data {
        }
        ArrayList<Data> datas = new ArrayList<>();
        for(Data data : datas) {
            logger.debug("data {}, solution {}", data, new Solution().solution(null));
        }
    }
    static class Solution {
        Logger logger = LoggerFactory.getLogger(C30L99999_TemplateSolution.Solution.class);
        public boolean solution(String[] phone_book) {
            return true;
        }
    }
}
