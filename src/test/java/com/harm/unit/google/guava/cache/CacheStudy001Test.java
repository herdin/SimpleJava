package com.harm.unit.google.guava.cache;

import com.harm.unit.DefaultUnitHandler;
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
        paramMap.put("repoLoadSec", 2L);
        paramMap.put("cacheSize", 10L);
        paramMap.put("expiSec", 60L);
        paramMap.put("testSize", 11L);
        paramMap.put("testLoop", 100L);
        paramMap.put("testSec", 1L);


        Long result = (Long)DefaultUnitHandler.start(new DefaultUnitHandler(new CacheStudy001()), new Object[]{paramMap});

        this.logger.debug("RESULT : {}", result);
        org.junit.Assert.assertThat(result>70, is(true));
    }

}
