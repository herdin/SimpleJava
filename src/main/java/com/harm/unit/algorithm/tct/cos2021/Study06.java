package com.harm.unit.algorithm.tct.cos2021;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Study06 {
    public static void func_a(ArrayList<ArrayList<Integer>> aList, ArrayList<ArrayList<Integer>> bList, int s, int c, int[][] p) {
        for (int i = 0; i < s + 1; i++) {
            aList.add(new ArrayList<Integer>());
            bList.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < c; i++) {
            int a = p[i][0];
            int b = p[i][1];

            bList.get(a).add(b);
            aList.get(b).add(a);
        }
    }

    public static int func_b(Queue<Integer> q, ArrayList<ArrayList<Integer>> arrList, boolean[] visit) {
        int rank = 0;

        while (!q.isEmpty()) {
            int here = q.poll();

            for (int i = 0; i < arrList.get(here).size(); i++) {
                int there = arrList.get(here).get(i);

                if (!visit[there]) {
                    rank++;
                    q.offer(there);
                    visit[there] = true;
                }
            }
        }

        return rank;
    }

    public static int func_c(ArrayList<ArrayList<Integer>> arrList, int count, int find) {
        Queue<Integer> q = new LinkedList<Integer>();
        boolean[] visit = new boolean[count + 1];
        int result = 0;

        q.offer(find);
        visit[find] = true;

//        result = func_ @ @ @(@@@) ;
        return result;
    }


    public static ArrayList<Integer> solution(int n, int m, int x, int[][] pair) {
        ArrayList<Integer> answer = new ArrayList<Integer>();

        ArrayList<ArrayList<Integer>> better = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> worse = new ArrayList<ArrayList<Integer>>();

//        func_ @ @ @(@@@) ;
//
//        int best = func_ @ @ @(@@@) +1;
//        int worst = n - func_ @ @ @(@@@) ;

//        answer.add(best);
//        answer.add(worst);

        return answer;
    }

    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
        int n1 = 5;
        int m1 = 4;
        int x1 = 1;
        int[][] pair1 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        ArrayList<Integer> ret1 = solution(n1, m1, x1, pair1);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret1 + " 입니다.");

        int n2 = 5;
        int m2 = 3;
        int x2 = 1;
        int[][] pair2 = {{2, 3}, {3, 4}, {4, 5}};
        ArrayList<Integer> ret2 = solution(n2, m2, x2, pair2);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret2 + " 입니다.");

        int n3 = 5;
        int m3 = 5;
        int x3 = 1;
        int[][] pair3 = {{1, 3}, {2, 3}, {3, 4}, {3, 5}, {4, 5}};
        ArrayList<Integer> ret3 = solution(n3, m3, x3, pair3);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret3 + " 입니다.");
    }
}







