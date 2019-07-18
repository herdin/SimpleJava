package com.harm.unit.io;

import com.harm.unit.DefaultUnitHandler;
import com.harm.unit.UnitRunner;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.Is.is;

public class HttpUrlConnectionStudy01Test {
    private Logger logger = LoggerFactory.getLogger(HttpUrlConnectionStudy01Test.class);

    @Test
    public void test() {
        Boolean result = (Boolean) UnitRunner.start(new DefaultUnitHandler(new HttpUrlConnectionStudy01()), new String[] {"https://www.google.com"});
        this.logger.debug("RESULT : {}", result);
        org.junit.Assert.assertThat(result, is(true));
    }
}
