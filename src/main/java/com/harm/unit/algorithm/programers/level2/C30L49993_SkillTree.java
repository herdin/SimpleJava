package com.harm.unit.algorithm.programers.level2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class C30L49993_SkillTree {
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(C30L42584_StockPrice.class);
        String skill = "CBD";
//        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"}; //2
        String[] skill_trees = {"AEF", "ZJW"}; //2
        logger.debug("skill -> {}", skill);
        Arrays.stream(skill_trees).forEach(s -> logger.debug("skill_trees -> {}", s));
        logger.debug("result -> {}", new Solution().solution(skill, skill_trees));
    }
    static class Solution {
//        Logger logger = LoggerFactory.getLogger(Solution.class);
        AtomicInteger atomicAnswer = new AtomicInteger(0);
        public int solution(String preSkill, String[] skill_trees) {
            Arrays.stream(skill_trees)
                    .parallel()
                    .forEach(skill_tree -> {
                        isSkillPossible(preSkill, skill_tree);
                    });
            int answer = skill_trees.length - atomicAnswer.get();
            return answer;
        }
        void isSkillPossible(String preSkill, String targetSkill) {
//            logger.debug("pre skill -> {}, skill tree -> {}", preSkill, targetSkill);
            String[] skills = targetSkill.split("");
            int preSkillIndex = 0;
            for(String skill : skills) {
                if(preSkill.indexOf(skill) >= 0) {
                    if(preSkill.substring(preSkillIndex, preSkillIndex+1).equals(skill)) {
                        preSkillIndex++;
                    } else {
                        atomicAnswer.incrementAndGet();
                        break;
                    }
                }
            }
        }
    }
}