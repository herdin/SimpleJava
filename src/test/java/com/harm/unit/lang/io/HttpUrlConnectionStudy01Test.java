package com.harm.unit.lang.io;

import com.harm.unit.DefaultUnitHandler;
import com.harm.unit.UnitRunner;
import com.harm.unit.lang.io.network.HttpUrlConnectionStudy01;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUrlConnectionStudy01Test {
    private Logger logger = LoggerFactory.getLogger(HttpUrlConnectionStudy01Test.class);

    @Test
    public void test() {
        Boolean result = (Boolean) UnitRunner.start(new DefaultUnitHandler(new HttpUrlConnectionStudy01()), new String[] {"https://www.google.com"});
        this.logger.debug("RESULT : {}", result);
        org.junit.jupiter.api.Assertions.assertEquals(result, true);
    }
}
