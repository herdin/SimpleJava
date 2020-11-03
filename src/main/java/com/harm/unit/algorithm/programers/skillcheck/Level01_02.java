package com.harm.unit.algorithm.programers.skillcheck;

import java.util.Arrays;

public class Level01_02 {
    public static void main(String[] args) {
//        String dartResult = "01230";
//        String dartResult = "1S2D*3T";	//37	11 * 2 + 22 * 2 + 33
        String dartResult = "1D2S#10S";	//9	12 + 21 * (-1) + 101
//        String dartResult = "1D2S0T";	//3	12 + 21 + 03
//        String dartResult = "1S*2T*3S";	//23	11 * 2 * 2 + 23 * 2 + 31
//        String dartResult = "1D#2S*3S";	//5	12 * (-1) * 2 + 21 * 2 + 31
//        String dartResult = "1T2D3D#";	//-4	13 + 22 + 32 * (-1)
//        String dartResult = "1D2S3T*";	//59	12 + 21 * 2 + 33 * 2
        System.out.println("result - > " + new Solution().solution(dartResult));
    }
    static class Solution {
        private boolean debug = false;
        private void logging(String msg) {
            if(debug) {
                System.out.println(msg);
            }
        }
        public int solution(String dartResult) {
            int answer = 0;

            int[] scores = new int[dartResult.length()/2];
            int lastScoreIndex = 0;
            int scoreIndex = 0;

            char[] dartResultArr = dartResult.toCharArray();
            StringBuffer sb = new StringBuffer();

            for(int i=0; i<dartResultArr.length; i++) {
                char dartResultChar = dartResultArr[i];
                logging("input : " + (char)dartResultChar);

                if('0' <= dartResultChar && dartResultChar <= '9') {
                    sb.append(dartResultChar);
                } else if('S' == dartResultChar || 'D' == dartResultChar || 'T' == dartResultChar) {
                    int score = Integer.parseInt(sb.toString());
                    logging("\tparse score : " + score);
                    sb.setLength(0);
                    switch(dartResultChar) {
                        case 'S':
                            break;
                        case 'D':
                            score = (int) Math.pow(score, 2);
                            break;
                        case 'T':
                            score = (int) Math.pow(score, 3);
                            break;
                        default:
                            throw new IllegalArgumentException("not applicable score type");
                    }
                    logging("\tscore : " + score);
                    lastScoreIndex = scoreIndex;
                    scores[scoreIndex++] = score;
                } else if('*' == dartResultChar || '#' == dartResultChar) {
                    switch(dartResultChar) {
                        case '*':
                            scores[lastScoreIndex] = scores[lastScoreIndex]*2;
                            if(lastScoreIndex > 0) {
                                scores[lastScoreIndex-1] = scores[lastScoreIndex-1]*2;
                            }
                            break;
                        case '#':
                            scores[lastScoreIndex] = scores[lastScoreIndex]*-1;
                            break;
                        default:
                            throw new IllegalArgumentException("not applicable price type");
                    }
                    logging("\tafter price apply : " + scores[lastScoreIndex]);
                }
            }

            if(debug) {
                for(int score : scores) {
                    logging("" + score);
                }
            }

            return Arrays.stream(scores).sum();
        }
    }
}
