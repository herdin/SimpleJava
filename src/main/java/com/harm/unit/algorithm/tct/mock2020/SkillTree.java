package com.harm.unit.algorithm.tct.mock2020;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class SkillTree implements Unit {
    private Logger logger = LoggerFactory.getLogger(SkillTree.class);
    @Override
    public Object execute(Object[] obj) throws Exception {

        return null;
    }

    public static void main(String[] args) {
        //https://business.programmers.co.kr/tryouts/12839/challenges/41760?language=java
        final String skillTree = "CBD";
        String[] skillTreesForValid = {"BACDE", "CBADF", "AECB", "BDA"};
        new SkillTree().validateSkill(skillTree, skillTreesForValid);
    }
    public int validateSkill(String skillTree, String[] skillTreesForValid) {
        long result = 0;
        result = Arrays.stream(skillTreesForValid).parallel()
                .filter(skills -> {
                    logger.debug("1 -> {}, {}", skills, skills.getClass().getSimpleName());
                    int ident = Arrays.stream(skills.split(""))
                            .filter(skill -> skillTree.indexOf(skill) >= 0)
                            .reduce(0, (pre, skill) -> {
                                logger.debug("1.1 in -> {}, {}", pre, skill);
                                if(pre == skillTree.indexOf(skill)) {
                                    pre++;
                                } else {
                                    pre = Integer.MAX_VALUE;
                                }
                                logger.debug("1.1 out -> {}, {}", pre, skill);
                                return pre;
                            }, (skill1, skill2) -> {
                                logger.debug("1.2 -> {}, {}", skill1, skill2);
                                return skill1+skill2;
                            });
                    logger.debug("1 -> {}", ident);
                    return ident != Integer.MAX_VALUE;
                })
            .count()
        ;
        logger.debug("result -> {}", result);
        return (int) result;
    }
}
