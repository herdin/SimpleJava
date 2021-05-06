package com.harm.unit.algorithm.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BubbleSort {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(BubbleSort.class);
        int arrLength = 10;
        int[] arr = new int[arrLength];
        IntStream.range(0, arrLength).forEach(i -> arr[i] = (int) (Math.random()*arrLength*10));
        logger.debug("before arr -> {}", Arrays.toString(arr));

        /**
         * array 길이가 10 이라면,
         * 내부 루프가
         * 처음에는 8, 9 쌍이 마지막(length-2, length-1 index 까지) -> i=arr.length-1
         * 마지막에는 0, 1 쌍을 비교해야하기 때문에 -> i>0, j<i
         * */
        for(int i=arr.length-1; i>0; i--) {
            for(int j=0; j<i; j++) {
                logger.debug("{}, {}", j, j+1);
                if(arr[j] > arr[j+1]) {
                    BubbleSort.swap(arr, j, j+1);
                }
            }
        }

        logger.debug("after arr  -> {}", Arrays.toString(arr));
    }

    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
