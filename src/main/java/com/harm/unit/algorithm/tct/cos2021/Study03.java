package com.harm.unit.algorithm.tct.cos2021;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Study03 {
    public static int solution(int[][] cookings, int n)
    {
        int answer = 0;

        Arrays.sort(cookings, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[1], o1[1]);
            }
        });

        Logger logger = LoggerFactory.getLogger(Study03.class);
        logger.debug("arr -> {}", Arrays.stream(cookings).map(cooking -> Arrays.toString(cooking)).collect(Collectors.joining(", ")));


        for(int[] e : cookings)
        {
            int stock = e[0];
            int price = e[1];
            int count = Math.min(n, stock);
            n -= count;

//            answer += price * n;
            answer += price * count;
        }

        return answer;
    }

    public static void main(String[] args)
    {
        int[][] cookings = {{4, 3000}, {3, 4000}};
        int n = 5;
        int ret = solution(cookings, n);
        System.out.println("solution 메소드의 반환 값은 "+ ret + " 입니다.");
    }
}







