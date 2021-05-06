package com.harm.unit.tdd;

@Deprecated
public class Franc extends Money {

    public Franc(int amount, String currency) {
        super(amount, currency);
    }

    public Money times(int i) { return new Franc(amount*i, currency); }

}
