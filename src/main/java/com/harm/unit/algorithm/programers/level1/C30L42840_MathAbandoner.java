package com.harm.unit.algorithm.programers.level1;

import java.awt.desktop.AboutHandler;
import java.util.Arrays;

public class C30L42840_MathAbandoner {
    public static void main(String[] args) {
//        int[] answers = {1,2,3,4,5};
        int[] answers = {1,3,2,4,2};
        new Solution().solution(answers);
    }

    static class Solution {
        public int[] solution(int[] answers) {
            int[] answer = {};

            Abandoner[] abandoners = new Abandoner[3];
            abandoners[0] = new Abandoner(new int[]{1, 2, 3, 4, 5});
            abandoners[1] = new Abandoner(new int[]{2, 1, 2, 3, 2, 4, 2, 5});
            abandoners[2] = new Abandoner(new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5});

            long a0 = Arrays.stream(answers).filter(a -> a == abandoners[0].getNextAnswer()).count();
            long a1 = Arrays.stream(answers).filter(a -> a == abandoners[1].getNextAnswer()).count();
            long a2 = Arrays.stream(answers).filter(a -> a == abandoners[2].getNextAnswer()).count();

            System.out.println(a0 + "/" + a1 + "/" +a2);



            return answer;
        }

        static class Abandoner {
            private int index = 0;
            private int[] rule;
            public Abandoner(int[] rule) {
                this.rule = rule;
            }
            public int getNextAnswer() {
                if(index >= rule.length) {
                    index = 0;
                }
                return rule[index++];
            }
        }
    }
}

