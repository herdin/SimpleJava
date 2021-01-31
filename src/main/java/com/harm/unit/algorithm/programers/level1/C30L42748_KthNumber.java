package com.harm.unit.algorithm.programers.level1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/*
정렬
* */
public class C30L42748_KthNumber {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(C30L42748_KthNumber.class);
        class Data {
            final int[] array;
            final int[][] commands;
            public Data(int[] array, int[][] commands) {
                this.array = array;
                this.commands = commands;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "array=" + Arrays.toString(array) +
                        ", commands=[" + Arrays.stream(commands).map(command -> Arrays.toString(command)).collect(Collectors.joining(", ")) + "]" +
                        '}';
            }
        }
        ArrayList<Data> datas = new ArrayList<>();
        datas.add(new Data(new int[]{1, 5, 2, 6, 3, 7, 4}, new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}}));
        for(Data data : datas) {
            logger.debug("data {}, solution {}", data, new Solution().solution(data.array, data.commands));
        }
    }

    static class Solution {
        public int[] solution(int[] array, int[][] commands) {
            return Arrays.stream(commands).mapToInt(command -> {
                int[] copied = Arrays.copyOfRange(array, command[0]-1, command[1]);
                Arrays.sort(copied);
                return copied[command[2]-1];
            }).toArray();
        }
    }
}