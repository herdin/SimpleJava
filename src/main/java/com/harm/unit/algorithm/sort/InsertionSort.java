package com.harm.unit.algorithm.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.IntStream;

public class InsertionSort {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(InsertionSort.class);
        int arrLength = 10;
        int[] arr = new int[arrLength];
        IntStream.range(0, arrLength).forEach(i -> arr[i] = (int) (Math.random()*arrLength*10));
        logger.debug("before arr -> {}", Arrays.toString(arr));
        for(int i=1; i<arrLength; i++) {
            int currentPivot = arr[i];
            int j=i-1;
            while(j>=0 && arr[j] > currentPivot) {
                arr[j+1] = arr[j];
                j--;
            }
//            for(; j>=0; j--) {
//                if(currentPivot < arr[j]) {
//                    arr[j+1] = arr[j];
//                } else {
//                    break;
//                }
//            }
            arr[j+1] = currentPivot;
        }

        logger.debug("after arr  -> {}", Arrays.toString(arr));
    }
}
