package com.harm.unit.pattern.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StrategyStudy001 {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(StrategyStudy001.class);
        List<Integer> intList = IntStream.generate(() -> (int) (Math.random() * 100)).limit(100).boxed().collect(Collectors.toList());
        StringBuffer sb = new StringBuffer();
        sb.setLength(0);
        intList.stream().sequential().forEach(item -> sb.append(item).append(", "));
        logger.debug("list -> {}", sb.toString());
        intList.sort((o1, o2) -> (o1 == o2)? 0: ((o1 < o2)? 1: -1));


        MovableThing car = new MovableThing("car", new MovingWithWheel());
        MovableThing airplane = new MovableThing("airplane", new MovingWithWing());
        car.moveThing();
        airplane.moveThing();

    }
}
