package com.harm.unit.algorithm.leetcode.medium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 오후 14:55 일요일 2021-01-24
 * 오후 15:40 일요일 2021-01-24
 * 끝
 *  */
public class Problems200NumberOfIslands {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems200NumberOfIslands.class);
        class Data {
            final char[][] grid;
            final int expect;

            public Data(char[][] grid, int expect) {
                this.grid = grid;
                this.expect = expect;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "grid={" + Arrays.stream(grid).map(gridLine -> Arrays.toString(gridLine)).collect(Collectors.joining(", ")) +
                        "}, expect=" + expect +
                        '}';
            }
        }
        List<Data> dataList = new ArrayList<>();
//        dataList.add(new Data(new char[][]{
//                {'1','1','1','1','0'},
//                {'1','1','0','1','0'},
//                {'1','1','0','0','0'},
//                {'0','0','0','0','0'}
//        }, 1));
        dataList.add(new Data(new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        }, 3));

        for(Data data : dataList) {
            logger.debug("data {}, solution {}", data.toString(), new Problems200NumberOfIslands.Solution().numIslands(data.grid));
        }

    }
    static class Solution {
//        Logger logger = LoggerFactory.getLogger(Problems200NumberOfIslands.Solution.class);
//        void loggingGridVisited(char[][] grid, boolean[][] visited) {
//            logger.debug("grid -> \n{}", Arrays.stream(grid).map(gridLine -> Arrays.toString(gridLine)).collect(Collectors.joining(",\n")));
//            logger.debug("visited -> \n{}", Arrays.stream(visited).map(visitedLine -> Arrays.toString(visitedLine)).collect(Collectors.joining(",\n")));
//        }
        enum DIRECTION {
            UP(-1, 0),
            DOWN(+1, 0),
            LEFT(0, -1),
            RIGHT(0, +1),
            ;
            int x;
            int y;
            DIRECTION(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
        public int numIslands(char[][] grid) {
            boolean[][] visited = new boolean[grid.length][];
            for(int i=0; i<grid.length; i++) {
                visited[i] = new boolean[grid[i].length];
            }
//            loggingGridVisited(grid, visited);
            int numberofIslands = 0;
            for(int i=0; i<grid.length; i++) {
                for(int j=0; j<grid[i].length; j++) {
                    if(!visited[i][j] && grid[i][j] == '1') {
                        numberofIslands++;
                        dfs(grid, visited, i, j);
                    }
                }
            }
//            loggingGridVisited(grid, visited);
            return numberofIslands;
        }
        void dfs(char[][] grid, boolean[][] visited, int i, int j) {
            if(visited[i][j]) return;
            visited[i][j] = true;
            for(DIRECTION direction : DIRECTION.values()) {
                int newI = i + direction.x;
                int newJ = j + direction.y;
                if(isPositionValid(grid, newI, newJ) && !visited[newI][newJ] && grid[newI][newJ] == '1') {
                    dfs(grid, visited, newI, newJ);
                }
            }
        }
        boolean isPositionValid(char[][] grid, int i, int j) {
            boolean validity = true;
            if(i < 0 || j < 0) {
                validity = false;
            } else if(grid.length <= i || grid[i].length <= j) {
                validity = false;
            }
            return validity;
        }
    }
}