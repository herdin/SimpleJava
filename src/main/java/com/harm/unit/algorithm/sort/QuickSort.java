package com.harm.unit.algorithm.sort;

import com.sun.xml.bind.v2.model.annotation.Quick;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.IntStream;

public class QuickSort {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(QuickSort.class);
        int arrLength = 10;
        int[] arr = new int[arrLength];
        IntStream.range(0, arrLength).forEach(i -> arr[i] = (int) (Math.random()*arrLength*10));
        logger.debug("before arr -> {}", Arrays.toString(arr));

//        QuickSort.sort(arr, 0, arr.length-1);
        QuickSort.sort(new int[]{1, 5}, 0, 1);


        logger.debug("after arr  -> {}", Arrays.toString(arr));
    }

    public static void sort(int[] arr, int left, int right) {
        if(left >= right) return;
        int start = left;
        int end = right;
        int pivot = arr[(left+right)/2];
        while(start < end) {
            while(start < end && arr[end] > pivot) end--;
            while(start < end && arr[start] < pivot) start++;
            QuickSort.swap(arr, start, end);
        }
        QuickSort.sort(arr, left, start-1);
        QuickSort.sort(arr, start+1, right);
    }

    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
