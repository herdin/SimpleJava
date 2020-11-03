package com.harm.unit.algorithm.tct.mock2020;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * java a - https://business.programmers.co.kr/tryouts/start_trial?tryout_id=12839
 * ead -    https://business.programmers.co.kr/tryouts/start_trial?tryout_id=12840
 * 2020 TCT 프로그래머스 개편 모의테스트 문제 1
 * 문제 설명
 * 길이가 n인 배열에 1부터 n까지 숫자가 중복 없이 한 번씩 들어 있는지를 확인하려고 합니다.
 * 1부터 n까지 숫자가 중복 없이 한 번씩 들어 있는 경우 true를, 아닌 경우 false를 반환하도록 함수 solution을 완성해주세요.
 *
 * 제한사항
 * 배열의 길이는 10만 이하입니다.
 * 배열의 원소는 0 이상 10만 이하인 정수입니다.
 */
public class IntArrayDistinct implements Unit {
    private static Logger logger = LoggerFactory.getLogger(IntArrayDistinct.class);
    @Override
    public Object execute(Object[] obj) throws Exception {

        return null;
    }

    public static void main(String[] args) {
        boolean answer = false;
        int[] arr = {1, 2, 3, 3, 4, 5};

        IntArrayDistinct solutions = new IntArrayDistinct();

//        1번 정확성 O 효율성 X
        answer = solutions.version1(arr);
        logger.debug("version1 -> {}", answer);

//        2번 정확성 O 효율성 X
        answer = solutions.version2(arr);
        logger.debug("version2 -> {}", answer);

//        3번 정확성 O 효율성 O
         answer = solutions.version3(arr);
        logger.debug("version3 -> {}", answer);
    }
    public boolean version1(int[] arr) {
        int targetSum = Arrays.stream(arr).parallel().distinct().sum();
        int normalSum = IntStream.rangeClosed(1, arr.length).parallel().sum();
        System.out.println("target sum -> " + targetSum);
        System.out.println("normal sum -> " + normalSum);
        return targetSum == normalSum;
    }
    public boolean version2(int[] arr) {
        int result = Arrays.stream(arr).distinct().sorted().reduce(0, (a, b) -> {
            logger.debug("{}, {}", a, b);
            if((b-1) == a)
                return b;
            else
                return a;
        });
        return arr.length == result;
    }
    public boolean version3(int[] arr) {
        boolean answer = true;

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");
        int[] flag = new int[arr.length];
        for(int i=0; i<arr.length; i++) {
            int arrVal = arr[i]-1;
            if(arrVal >= arr.length || flag[arrVal] == 1) {
                answer = false;
                break;
            } else {
                flag[arrVal] = 1;
            }
        }
        for(int i=0; i<flag.length && answer; i++) {
            if(flag[i] == 0) {
                answer = false;
                break;
            }
        }
        return answer;
    }
}
