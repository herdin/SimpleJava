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
    public static class ListNode {
        private final Logger logger = LoggerFactory.getLogger(ListNode.class);
        public int val;
        public ListNode next;
        public ListNode(int x) { val = x; }
        public void setVal(int x) { val = x; }

        @Override
        public String toString() {
            ListNode cur = this;
            StringBuffer sb = new StringBuffer();

            while(cur != null) {
                this.logger.debug("{}", cur.val);
                sb.append(cur.val);
                cur = cur.next;
            }
            return sb.toString();
        }
    }
    @Override
    public Object execute(Object[] obj) throws Exception {

        ListNode l1 = (Problems02AddTwoNumbers.ListNode)obj[0];
        ListNode l2 = (Problems02AddTwoNumbers.ListNode)obj[1];

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
