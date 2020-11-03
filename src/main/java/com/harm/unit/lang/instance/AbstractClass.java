package com.harm.unit.lang.instance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractClass {

    private Logger logger = LoggerFactory.getLogger(AbstractClass.class);
    private static Map<String, String> config = new HashMap<>();

    public void doSomething() {
        logger.debug("");
        print(config);
        process(config);
    }
    abstract void print(Map config);
    abstract void process(Map config);
}
