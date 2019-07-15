package com.harm.unit.pattern.observer;

import com.harm.unit.DefaultUnitHandler;
import com.harm.unit.xml.UnitRunner;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObserverPatternStudy001Test {
    private Logger logger = LoggerFactory.getLogger(ObserverPatternStudy001Test.class);

    @Test
    public void test() {
        Boolean result = (Boolean) UnitRunner.start(new DefaultUnitHandler(new ObserverPatternStudy001()), new Integer[] {10, 100, 30});
        Assert.assertFalse(result);
    }
}
