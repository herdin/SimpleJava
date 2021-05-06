package com.harm.unit.tdd;

@Deprecated
public class Dollar extends Money {

    public Dollar(int amount, String currency) { super(amount, currency); }

    public Money times(int i) { return new Money(amount*i, currency); }
}
