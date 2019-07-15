package com.harm.unit.google.guava.cache;

import com.harm.unit.DefaultUnitHandler;
import com.harm.unit.xml.UnitRunner;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;

public class CacheStudy001Test {
    private Logger logger = LoggerFactory.getLogger(CacheStudy001Test.class);

    @Test
    public void test() {
        Map<String, Long> paramMap = new HashMap<>();
        paramMap.put("repoSize", 100L);
        paramMap.put("repoLoadMillisec", 10L);

        paramMap.put("cacheSize", 10L);
        paramMap.put("expiMilisec", 1000L);

        paramMap.put("testRequestDurMillisec", 10L);
        paramMap.put("testSize", 11L);
        paramMap.put("testLoop", 100L);
        paramMap.put("testMillisec", 5L);


        Long result = (Long) UnitRunner.start(new DefaultUnitHandler(new CacheStudy001()), new Object[]{paramMap});

        this.logger.debug("RESULT : {}", result);
        org.junit.Assert.assertThat(result>70, is(true));
    }

}
