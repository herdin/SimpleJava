package com.harm.unit.pattern.strategy;

public class MovingWithWheel implements MovingStrategy {
    @Override
    public void move() {
        System.out.println("굴러간당");
    }
}
