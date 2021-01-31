package com.harm.unit.algorithm.programers.level1;

import com.harm.unit.algorithm.programers.C30L99999_TemplateSolution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;

/** Greedy */
public class C30L42862_GymSuit {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(C30L42862_GymSuit.class);
        class Data {
            final int n;
            final int[] lost;
            final int[] reserve;
            final int solution;
            public Data(int n, int[] lost, int[] reserve, int solution) {
                this.n = n;
                this.lost = lost;
                this.reserve = reserve;
                this.solution = solution;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "n=" + n +
                        ", lost=" + Arrays.toString(lost) +
                        ", reserve=" + Arrays.toString(reserve) +
                        ", solution=" + solution +
                        '}';
            }
        }
        ArrayList<Data> datas = new ArrayList<>();
        datas.add(new Data(5, new int[]{2, 4}, new int[]{1, 3, 5}, 5));
        datas.add(new Data(5, new int[]{2, 4}, new int[]{3}, 4));
        datas.add(new Data(3, new int[]{3}, new int[]{1}, 2));
        datas.add(new Data(3, new int[]{1, 2}, new int[]{2, 3}, 2));
        for(Data data : datas) {
            logger.debug("data {}, solution {}", data, new C30L42862_GymSuit.Solution().solution(data.n, data.lost, data.reserve));
        }
    }
    static class Solution {
        Logger logger = LoggerFactory.getLogger(C30L42862_GymSuit.Solution.class);
        public int solution(int n, int[] lost, int[] reserve) {
            int[] lostArr = Arrays.copyOf(lost, lost.length);
            int[] reserveArr = Arrays.copyOf(reserve, reserve.length);
            int answer = n-lostArr.length;
            for(int i=0; i<reserveArr.length; i++) {
                for (int j = 0; j<lostArr.length; j++) {
                    if(reserveArr[i] == lostArr[j]) {
                        reserveArr[i] = -1;
                        lostArr[j] = -1;
                        answer++;
                        break;
                    }
                }
            }
            logger.debug("lost {}", Arrays.toString(lostArr));
            logger.debug("reserve {}", Arrays.toString(reserveArr));
            for(int i=0; i<reserveArr.length; i++) {
                if(reserveArr[i] == -1) continue;
                for(int j=0; j<lostArr.length; j++) {
                    if(lostArr[j] == -1) continue;
                    if(reserveArr[i]+1 == lostArr[j] || reserveArr[i]-1 == lostArr[j]) {
                        reserveArr[i] = -1;
                        lostArr[j] = -1;
                        answer++;
                        break;
                    }

                }
            }
            logger.debug("lost {}", Arrays.toString(lostArr));
            logger.debug("reserve {}", Arrays.toString(reserveArr));
            return answer;
        }
    }
}
