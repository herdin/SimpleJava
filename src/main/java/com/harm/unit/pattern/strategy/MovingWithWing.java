package com.harm.unit.pattern.strategy;

public class MovingWithWing implements MovingStrategy {
    @Override
    public void move() {
        System.out.println("날아간당");
    }
}
