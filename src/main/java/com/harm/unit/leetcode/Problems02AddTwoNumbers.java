package com.harm.unit.leetcode;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/*
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
 */
public class Problems02AddTwoNumbers implements Unit {
    public static final Logger logger = LoggerFactory.getLogger(Problems02AddTwoNumbers.class);
    class ListNode {
        private final Logger logger = LoggerFactory.getLogger(ListNode.class);
        public int val;
        public ListNode next;
        public ListNode(int x) { val = x; }

        @Override
        public String toString() {
            ListNode cur = this;

            while(cur != null) {
                Problems02AddTwoNumbers.logger.debug("{}", cur.val);
                cur = cur.next;
            }
            return "";
        }
    }
    @Override
    public Object execute(Object[] obj) throws Exception {

        if(true)
            throw new Exception("HANDLE THIS!!!");

        ListNode l1 = null;
        ListNode lt = new ListNode(5);
        l1 = lt;


        ListNode l2 = null;
        lt = new ListNode(5);
        l2 = lt;

        ListNode t1 = l1;
        ListNode t2 = l2;
        ListNode t3 = new ListNode(0);
        ListNode result = t3;
        int rest = 0;
        while(t1 != null || t2 != null || rest == 1) {
            if(t1 == null) {
                t1 = new ListNode(0);
            }
            if(t2 == null) {
                t2 = new ListNode(0);
            }

            int sum = t1.val + t2.val + rest;
            if(sum >= 10) {
                t3.val = sum - 10;
                rest = 1;
            } else {
                t3.val = sum;
                rest = 0;
            }

            t1 = t1.next;
            t2 = t2.next;
            if(t1 != null || t2 != null || rest == 1) {
                t3.next = new ListNode(0);
                t3 = t3.next;
            }
        }

        return result;
    }
}
