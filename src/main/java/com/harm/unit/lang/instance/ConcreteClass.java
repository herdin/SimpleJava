package com.harm.unit.lang.instance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ConcreteClass extends AbstractClass {
    private Logger logger = LoggerFactory.getLogger(ConcreteClass.class);

    private String value = "from concrete value";
    @Override
    void print(Map config) {
        logger.debug("");
    }

    @Override
    void process(Map config) {
        logger.debug("");
        logger.debug("value -> {}", value);
        logger.debug("config -> {}", config.get(value));

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        logger.debug("result -> {}", list.stream().filter((i) -> 5<i).count());
    }
}
