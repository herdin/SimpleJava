package com.harm.unit.algorithm.tct.hamhuman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class TctStudy01 {
    private static Logger logger = LoggerFactory.getLogger(TctStudy01.class);
    public static void main(String[] args) {
        String[] cakes = {
            "AAADD",
            "ACCEE",
            "ACEEE",
            "EDBBB",
            "EDDBB"
        };
        int[] cut_rows = {1, 2, 4};
        int[] cut_columns = {2, 4};
        /*
        x = 0, 1, 2~3, 4~limit
        y = 0~1, 2~3, 4~limit
        */
        TctStudy01.logger.debug("max tastes is {}", new TctStudy01().solution(cakes, cut_rows, cut_columns));
    }

    public int solution(String[] cakes, int[] cut_rows, int[] cut_columns) {
        int result = -1;
        int[][] rowRangeArr = getRangeByCutArr(cut_rows, cakes[0].length()-1);
        int[][] colRangeArr = getRangeByCutArr(cut_columns, cakes.length-1);
        final int START = 0;
        final int END = 1;
        for(int i=0; i<rowRangeArr.length; i++) {
            for(int j=0; j<colRangeArr.length; j++) {
                int tastes = getTastes(cakes, new Point(rowRangeArr[i][START], colRangeArr[j][START]), new Point(rowRangeArr[i][END], colRangeArr[j][END]));
                result = (tastes > result)? tastes : result;
            }
        }
        return result;
    }

    private int[][] getRangeByCutArr(int[] cutLine, int last) {
        int[][] rangeArr = new int[cutLine.length+1][2];
        for(int i=0; i<cutLine.length; i++) {
            int start = (i==0)? 0:cutLine[i-1];
            rangeArr[i] = new int[]{start, cutLine[i]-1};
        }
        rangeArr[cutLine.length] = new int[]{cutLine[cutLine.length-1], last};
        return rangeArr;
    }

        private int getTastes(String[] cakes, Point start, Point end) {
        Set<String> tastes = new HashSet<>();
        for(int x=start.getX(); x<=end.getX(); x++) {
            for(int y=start.getY(); y<=end.getY(); y++) {
                tastes.add(cakes[x].substring(y, y+1));
            }
        }
        logger.debug("tastes are {} in range {} ~ {}, tastes count is {}", tastes, start, end, tastes.size());
        return tastes.size();
    }

    class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
