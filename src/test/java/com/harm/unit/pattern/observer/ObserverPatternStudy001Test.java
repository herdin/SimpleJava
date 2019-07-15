package com.harm.unit.pattern.observer;

import com.harm.unit.DefaultUnitHandler;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObserverPatternStudy001Test {
    private Logger logger = LoggerFactory.getLogger(ObserverPatternStudy001Test.class);

    @Test
    public void test() {
        Boolean result = (Boolean) DefaultUnitHandler.start(new DefaultUnitHandler(new ObserverPatternStudy001()), new Integer[] {1, 100, 30});
        Assert.assertFalse(result);
    }
}
