package com.harm.unit.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertEquals(Money.dollar(6), Money.dollar(6));
        Assertions.assertNotEquals(Money.dollar(5), Money.dollar(6));
        assertEquals(Money.franc(5), Money.franc(5));
        assertEquals(Money.franc(6), Money.franc(6));
        Assertions.assertNotEquals(Money.franc(5), Money.franc(6));

        Assertions.assertNotEquals(Money.dollar(5), Money.franc(5));
    }

    @Test
    public void 화폐_테스트() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

}