package com.harm.unit.lettcode;

import com.harm.unit.UnitRunner;
import com.harm.unit.leetcode.Problems02AddTwoNumbers;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.Is.is;

public class Problems02AddTwoNumbersTest {
    private Logger logger = LoggerFactory.getLogger(Problems02AddTwoNumbersTest.class);
    @Test
    public void test() {

        Problems02AddTwoNumbers.ListNode l11 = new Problems02AddTwoNumbers.ListNode(2);
        Problems02AddTwoNumbers.ListNode l12 = new Problems02AddTwoNumbers.ListNode(4);
        Problems02AddTwoNumbers.ListNode l13 = new Problems02AddTwoNumbers.ListNode(3);
        l11.next = l12;
        l12.next = l13;
        Problems02AddTwoNumbers.ListNode l21 = new Problems02AddTwoNumbers.ListNode(5);
        Problems02AddTwoNumbers.ListNode l22 = new Problems02AddTwoNumbers.ListNode(6);
        Problems02AddTwoNumbers.ListNode l23 = new Problems02AddTwoNumbers.ListNode(4);
        l21.next = l22;
        l22.next = l23;
        Problems02AddTwoNumbers.ListNode result = (Problems02AddTwoNumbers.ListNode)UnitRunner.start(new Problems02AddTwoNumbers(), new Object[]{ l11, l21 });

        this.logger.debug("RESULT {}", result.toString());
        org.junit.Assert.assertThat(result.toString(), is("708"));
    }
}

