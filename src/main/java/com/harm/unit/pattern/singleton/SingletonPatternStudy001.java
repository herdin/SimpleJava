package com.harm.unit.pattern.singleton;

import com.harm.unit.Unit;
import com.harm.unit.UnitRunner;
import com.harm.unit.pattern.observer.ObserverPatternStudy001;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingletonPatternStudy001 implements Unit {
    private Logger logger = LoggerFactory.getLogger(SingletonPatternStudy001.class);

    @Override
    public Object execute(Object[] obj) throws Exception {
        return null;
    }

    public static void main(String[] args) {
        UnitRunner.start(new SingletonPatternStudy001());
    }
}
