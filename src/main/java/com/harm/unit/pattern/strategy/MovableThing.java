package com.harm.unit.pattern.strategy;

public class MovableThing {
    final String name;
    final MovingStrategy movingStrategy;

    public MovableThing(String name, MovingStrategy movingStrategy) {
        this.name = name;
        this.movingStrategy = movingStrategy;
    }

    public void moveThing() {
        movingStrategy.move();
    }
}
