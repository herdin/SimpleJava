package com.harm.unit.algorithm.tct.eadtest202005;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Test01 {
    private static Logger logger = LoggerFactory.getLogger(Test01.class);
    private static int areaCount = 0;
    public enum DIRECTION {
        UP(-1, 0),
        DOWN(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1),
        ;
        private int movex;
        private int movey;
        DIRECTION(int movex, int movey) {
            this.movex = movex;
            this.movey = movey;
        }
        public int movex() { return movex; }
        public int movey() { return movey; }
    }
    public static void main(String[] args) {
        int[][] area = {
                {1, 0, 1, 0, 1},
                {0, 0, 1, 0, 0},
                {1, 0, 1, 0, 1},
                {0, 0, 1, 0, 0},
                {1, 0, 1, 1, 1},
                {0, 0, 1, 0, 0},
                {1, 0, 1, 0, 1},
        };

        for(int[] line : area) {
            Test01.logger.debug("{}", line);
        }

        Set<String> visited = new HashSet<>();
        for(int i=0; i<area.length; i++) {
            for(int j=0; j<area[i].length; j++) {
                if(area[i][j] == 0) {
                    new Test01().dfs(i, j, area, visited, true);
                }
            }
        }
        Test01.logger.debug("area is {}", areaCount);
    }

    public void dfs(int x, int y, int[][] area, Set<String> visited, boolean fromOut) {
        final String currentPosition = "current(" + x + ", " + y + ")";
        Test01.logger.debug("{}, start, visited {}", currentPosition, visited);
        if(visited.contains(""+x+y)) {
            Test01.logger.debug("{}, already visited.", currentPosition);
            return;
        } else {
            Test01.logger.debug("{}, first visited.", currentPosition);
            visited.add(""+x+y);
            if(fromOut) {
                areaCount++;
            }
        }

        for(DIRECTION direction : DIRECTION.values()) {
            int nextx = x + direction.movex();
            int nexty = y + direction.movey();
            Test01.logger.debug("{}, next to {} -> ({}, {})", currentPosition, direction, nextx, nexty);
            if(0 <= nextx && nextx < area.length && 0 <= nexty && nexty < area[0].length && area[nextx][nexty] == 0) {
                dfs(nextx, nexty, area, visited, false);
            }
        }
        Test01.logger.debug("{}, done, visited {}", currentPosition, visited);
    }

}
