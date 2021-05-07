package com.harm.unit.pattern.observer;

import com.harm.unit.UnitRunner;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObserverPatternStudy001Test {
    private Logger logger = LoggerFactory.getLogger(ObserverPatternStudy001Test.class);

    @Test
    public void test() {
        Boolean result = (Boolean) UnitRunner.start(new ObserverPatternStudy001(), new Integer[] {10, 100, 30});
        org.junit.jupiter.api.Assertions.assertFalse(result);
    }
}
