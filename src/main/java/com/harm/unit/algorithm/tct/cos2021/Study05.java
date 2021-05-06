package com.harm.unit.algorithm.tct.cos2021;

public class Study05 {
    public static int solution(int number1, int number2, int summation) {
        int answer = 0;

        if (number1 > number2) {
            int temp = number1;
            number1 = number2;
            number2 = number1;
        }

        for (int i = 0; i <= summation / number1; i++) {
            if ((summation - number1 * i) % number2 == 0) {
//                answer = i + (summation - number1 * i) / number2 - 1;
                return i + (summation - number1 * i) / number2 - 1;
            }
            continue;
        }

        return answer;
    }

    public static void main(String[] args) {
        int number1 = 2;
        int number2 = 4;
        int summation = 8;
        int ret = solution(number1, number2, summation); //1
        System.out.println("solution 메소드의 반환 값은 " + ret + " 입니다.");
    }
}







