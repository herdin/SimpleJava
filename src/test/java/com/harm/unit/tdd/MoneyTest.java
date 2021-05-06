package com.harm.unit.tdd;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyTest {

    @Test
    /** 곱셈을 테스트한다  제바알 */
    public void 달러_곱셈_테스트() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));

        five = Money.franc(5);
        assertEquals(Money.franc(10), five.times(2));
        assertEquals(Money.franc(15), five.times(3));
    }

    @Test
    public void equals() {
        달러_곱셈_테스트   ();
        assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        assertTrue(Money.dollar(6).equals(Money.dollar(6)));
        assertFalse(Money.dollar(5).equals(Money.dollar(6)));
        assertTrue(Money.franc(5).equals(Money.franc(5)));
        assertTrue(Money.franc(6).equals(Money.franc(6)));
        assertFalse(Money.franc(5).equals(Money.franc(6)));

        assertFalse(Money.dollar(5).equals(Money.franc(5)));
    }

    @Test
    public void 화폐_테스트() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

}