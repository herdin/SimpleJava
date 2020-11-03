package com.harm.unit.algorithm.tct.hamhuman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TctStudy02 {
    public static Logger logger = LoggerFactory.getLogger(TctStudy02.class);

    public static void main(String[] args) {
        int n = 5;
        int jump = 4;
        logger.debug("N {}, Jump {} last {}", n, jump, new TctStudy02().solution(n, jump));
    }
    public Location solution(int n, int jump) {
        int[] squareLine = new int[n*n];
        int writeCount = 0;
        int index = 0;
        while(writeCount < n*n) {
            logger.debug("current index {} write count {}", index, writeCount);
            squareLine[index] = ++writeCount;
            if(writeCount < n*n) {
                index = getNext(squareLine, index, jump);
            }
        }
        logger.debug("-> {}", squareLine);

        /*
0,0 0,1 0,2 0,3 0,4

1,4 2,4 3,4 4,4 x+
4,3 4,2 4,1 4,0 y-
3,0 2,0 1,0 x-
1,1 1,2 1,3 y+
2,3 3,3
3,2 3,1
2,1
2,2
         */

        int[][] square = new int[n][n];
        for(int i=0; i<square[0].length; i++) {
            square[0][i] = squareLine[i];
        }
        printSquare(square);

        index = n;
        Location current = new Location(0, n-1);
        int apply = 1;
        int value = 0;
        for(int i=n-1; i>0; i--) {
            for(int j=0; j<i; j++) {
                current.setX(current.getX() + apply);
                value = squareLine[index++];
                if(value == n*n) {
                    current.convertHumanReadable();
                    return current;
                }
                square[current.getX()][current.getY()] = value;
                printSquare(square);
            }

            apply = apply*-1;

            for(int j=0; j<i; j++) {
                current.setY(current.getY() + apply);
                value = squareLine[index++];
                if(value == n*n) {
                    current.convertHumanReadable();
                    return current;
                }
                square[current.getX()][current.getY()] = value;
                printSquare(square);
            }
        }
        printSquare(square);

        return new Location(0, 0);
    }
    public int getNext(int[] squareLine, int currentIndex, int jump) {
        int nextIndex = currentIndex;
        int jumpCount = 0;
        while(jumpCount < jump) {
            logger.debug("calc next, next index {} jump count/jump limit {}/{}", nextIndex, jumpCount, jump);
            nextIndex++;
            if(nextIndex >= squareLine.length) {
                nextIndex = 0;
            }
            logger.debug("square line next index value {}", squareLine[nextIndex]);
            if(squareLine[nextIndex] == 0) {
                jumpCount++;
            }
        }
        return nextIndex;
    }
    public void printSquare(int[][] square) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("print square -> \n");
        for(int[] line : square) {
            for(int item : line) {
                stringBuffer.append("" + item + " ");
            }
            stringBuffer.append("\n");
        }
        logger.debug("\n{}", stringBuffer.toString());
    }
    public static class Location {
        int x;
        int y;
        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public void convertHumanReadable() { x++; y++; }
        public void setX(int x) { this.x = x; }
        public void setY(int y) { this.y = y; }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        @Override
        public String toString() {
            return "Location{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
