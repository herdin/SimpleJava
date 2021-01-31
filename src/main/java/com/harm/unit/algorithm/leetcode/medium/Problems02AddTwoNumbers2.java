package com.harm.unit.algorithm.leetcode.medium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 * 끝
 * 숫자로 변환해서 더한다음 %10, /10 으로 1의자리부터 노드 만드는방식
 * 이전 풀이가 훨씬 낫다. 이건 너무 느리다.
 */
public class Problems02AddTwoNumbers2 {
    public static Logger logger = LoggerFactory.getLogger(Problems02AddTwoNumbers2.class);
    public static void main(String[] args) {

        List<Data> dataList = new ArrayList<>();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode expect = new ListNode(7);
        expect.next = new ListNode(0);
        expect.next.next = new ListNode(8);
//        dataList.add(new Data(l1, l2, expect));
        l1 = new ListNode(9);
        l2 = new ListNode(1);
        ListNode next = l2;
        next.next = new ListNode(9); next = next.next;
        next.next = new ListNode(9); next = next.next;
        next.next = new ListNode(9); next = next.next;
        next.next = new ListNode(9); next = next.next;
        next.next = new ListNode(9); next = next.next;
        next.next = new ListNode(9); next = next.next;
        next.next = new ListNode(9); next = next.next;
        next.next = new ListNode(9); next = next.next;
        next.next = new ListNode(9); next = next.next;
        expect = new ListNode(0);
        next = expect;
        next.next = new ListNode(0); next = next.next;
        next.next = new ListNode(0); next = next.next;
        next.next = new ListNode(0); next = next.next;
        next.next = new ListNode(0); next = next.next;
        next.next = new ListNode(0); next = next.next;
        next.next = new ListNode(0); next = next.next;
        next.next = new ListNode(0); next = next.next;
        next.next = new ListNode(0); next = next.next;
        next.next = new ListNode(0); next = next.next;
        next.next = new ListNode(1); next = next.next;
//        dataList.add(new Data(l1, l2, expect));
        l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        dataList.add(new Data(l1, l2, expect));
//[9]
//[1,9,9,9,9,9,9,9,9,9]
// 0,0,0,0,0,0,0,0,0,0,1
        for(Data data : dataList) {
            logger.debug("data {}, solution {}", data, new Problems02AddTwoNumbers2.Solution().addTwoNumbers(data.l1, data.l2));
        }

    }
    static class Data {
        ListNode l1;
        ListNode l2;
        ListNode expect;

        public Data(ListNode l1, ListNode l2, ListNode expect) {
            this.l1 = l1;
            this.l2 = l2;
            this.expect = expect;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "l1=" + l1.toString() +
                    ", l2=" + l2.toString() +
                    ", expect=" + expect.toString() +
                    '}';
        }
    }
     //Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

         @Override
         public String toString() {
            final String ARROW = " -> ";
            StringBuffer sb = new StringBuffer();
            sb
            .append(val)
            .append(ARROW);
            ListNode next = this.next;
            while(next != null) {
                sb
                .append(next.val)
                .append(ARROW);
                next = next.next;
            }
            return sb.toString();
         }
     }
    static class Solution {
//        public static Logger logger = LoggerFactory.getLogger(Problems02AddTwoNumbers2.Solution.class);
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            BigDecimal sum = getIntegerFromListNode(l1).add(getIntegerFromListNode(l2));
            if(sum.equals(BigDecimal.ZERO)) {
                return new ListNode(0);
            }
            BigDecimal ten = new BigDecimal(10);
            BigDecimal digit = sum.remainder(ten);
            BigDecimal rest = sum.divide(ten).setScale(0, RoundingMode.FLOOR);
            ListNode listNode = new ListNode(digit.intValue());
            ListNode root = listNode;
            while(rest.compareTo(BigDecimal.ZERO) > 0) {
                digit = rest.remainder(ten);
                rest = rest.divide(ten).setScale(0, RoundingMode.FLOOR);
                listNode.next = new ListNode(digit.intValue());
                listNode = listNode.next;
            }
            return root;
        }
        BigDecimal getIntegerFromListNode(ListNode listNode) {
            StringBuffer sb = new StringBuffer();
            ListNode next = listNode;
            while(next != null) {
                sb.append(next.val);
                next = next.next;
            }
            if(sb.length() == 0) {
                return BigDecimal.ZERO;
            } else {
                return new BigDecimal(sb.reverse().toString());
            }
        }
    }

}
