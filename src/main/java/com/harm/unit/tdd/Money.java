package com.harm.unit.tdd;

public class Money {
    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public Money times(int i) {
        return new Money(i*amount, currency);
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Money) {
            Money money = (Money)obj;
            return amount == money.amount() && currency.equals(money.currency());
        } else {
            return super.equals(obj);
        }
    }
    public int amount() { return amount; }
    public String currency() { return currency; }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
