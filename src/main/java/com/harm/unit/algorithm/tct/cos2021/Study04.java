package com.harm.unit.algorithm.tct.cos2021;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Study04 {
    public static boolean solution(int[] stores, int k) {
        boolean answer = true;

        for (int number = 0; number <= 1000; number++) {
            int previous = -k;
            int idx = 0;

            for (int store : stores) {
                if (store != number) {
                    idx++;
                    continue;
                }

//                if (idx - previous <= k) {
                if (idx - previous < k) {
                    answer = false;
                    return answer;
                }
                previous = idx++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] stores1 = {2, 3, 5, 4, 2};
        int k1 = 4;
        boolean ret1 = solution(stores1, k1);
        System.out.println("solution 메소드의 반환 값은 " + ret1 + " 입니다.");

        int[] stores2 = {1, 1, 5, 1, 6, 4};
        int k2 = 3;
        boolean ret2 = solution(stores2, k2);
        System.out.println("solution 메소드의 반환 값은 " + ret2 + " 입니다.");
    }
}







