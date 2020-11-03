package com.harm.unit.algorithm.programers.level1;

import java.util.Stack;

/**
 * 오전 10:22 목요일 2020-05-07
 * 오전 10:51 목요일 2020-05-07
 */
public class C30L64061_Crain {
    public static void main(String[] args) {
        int[][] borad = {
            {0,0,0,0,0},
            {0,0,1,0,3},
            {0,2,5,0,1},
            {4,2,4,4,2},
            {3,5,1,3,1}
        };
        int[] moves = {1,5,3,5,1,2,1,4};
        System.out.println("solution -> " + new Solution().solution(borad, moves));
    }

    static class Solution {
        public int solution(int[][] board, int[] moves) {
            int answer = 0;
            Stack<Integer> bucket = new Stack<>();

            for(int move : moves) {
                int moveIndex = move-1;
                System.out.println("===============================================");
                System.out.println("incoming move -> " + move + " -> " + moveIndex);
                for(int boardFloorReverse=0; boardFloorReverse<board.length; boardFloorReverse++) {
                    System.out.println("========================");
                    System.out.println("boardFloorReverse -> " + boardFloorReverse);
                    System.out.println("board[boardFloorReverse][moveIndex] -> " +board[boardFloorReverse][moveIndex]);
                    System.out.println("pre bucket -> " + bucket.toString());
                    if(board[board.length-1][moveIndex] == 0) {
                        System.out.println("lain is empty skip");
                        continue;
                    }
                    if(board[boardFloorReverse][moveIndex] != 0) {
                        if(bucket.isEmpty() || bucket.peek() != board[boardFloorReverse][moveIndex]) {
                            bucket.push(board[boardFloorReverse][moveIndex]);
                        } else {
                            bucket.pop();
                            answer = answer + 2;
                            System.out.println("pop! -> " + answer);
                        }
                        board[boardFloorReverse][moveIndex] = 0;
                        System.out.println("pos bucket -> " + bucket.toString());
                        break;
                    }
                }
            }


            return answer;
        }
    }
}
