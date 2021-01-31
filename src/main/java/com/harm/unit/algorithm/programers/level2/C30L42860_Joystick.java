package com.harm.unit.algorithm.programers.level2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 오후 20:34 토요일 2021-01-30
 * */
public class C30L42860_Joystick {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(C30L42860_Joystick.class);
        class Data {
            final String name;
            final int expect;

            public Data(String name, int expect) {
                this.name = name;
                this.expect = expect;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "name='" + name + '\'' +
                        ", expect=" + expect +
                        '}';
            }
        }
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data("JEROEN", 56));
        dataList.add(new Data("JAN", 23));
        dataList.add(new Data("JAZ", 11));
        dataList.add(new Data("BBBAAB", 9));
        dataList.add(new Data("BBAABAAAAB ", 12));

        for(Data data : dataList) {
            logger.debug("data -> {}, solution -> {}", data, new C30L42860_Joystick.Solution().solution(data.name));
        }
    }

    static class Solution {
        final char INIT_CHAR = 'A';
        public int solution(String name) {
            char[] targetSlot = name.toCharArray();
            char[] workSlot = new char[name.length()];
            IntStream.range(0, name.length()).parallel().forEach(index -> workSlot[index] = INIT_CHAR);
            Info info = new Info();

            while(true) {
                if(isValidForAction(targetSlot, workSlot)) chageSlot(info, targetSlot, workSlot);
                else break;
                if(isValidForAction(targetSlot, workSlot)) changeIndex(info, targetSlot, workSlot);
                else break;
            }
            return info.changeCount;
        }

        void chageSlot(Info info, char[] targetSlot, char[] workSlot) {
            final char LAST_CHAR = 'Z';
            int moveForwardCount = targetSlot[info.currentIndex] - INIT_CHAR;
            int moveBackwardCount = LAST_CHAR - targetSlot[info.currentIndex] + 1;
            int changeCount = Math.min(moveForwardCount, moveBackwardCount);
            workSlot[info.currentIndex] = targetSlot[info.currentIndex];
            info.changeCount += changeCount;
        }
        void changeIndex(Info info, char[] targetSlot, char[] workSlot) {
            boolean isPosIndexWin = true;
            int posIndex = info.currentIndex;
            int preIndex = info.currentIndex;
            int changeCount = 0;
            for(int i=1; i<targetSlot.length; i++) {
                posIndex++;
                posIndex = (posIndex >= targetSlot.length)? 0:posIndex;
                preIndex--;
                preIndex = (preIndex < 0)? targetSlot.length-1:preIndex;
                changeCount++;
                if(targetSlot[posIndex] != workSlot[posIndex]) {
                    isPosIndexWin = true;
                    break;
                } else if(targetSlot[preIndex] != workSlot[preIndex]) {
                    isPosIndexWin = false;
                    break;
                }
            }
            info.currentIndex = (isPosIndexWin)? posIndex:preIndex;
            info.changeCount += changeCount;
        }
        boolean isValidForAction(char[] targetSlot, char[] workSlot) {
            return IntStream.range(0, targetSlot.length).parallel().filter(index -> targetSlot[index] != workSlot[index]).findAny().isPresent();
        }
    }
    static class Info {
        int currentIndex = 0;
        int changeCount = 0;
    }
}