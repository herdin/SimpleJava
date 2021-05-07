package com.harm.unit.util.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class MapControlStudyTest {

    Logger logger = LoggerFactory.getLogger(MapControlStudyTest.class);

    Map<String, Integer> map;
    @BeforeEach
    public void initMap() {
        map = new HashMap<>();
        IntStream.range(0, 10).forEach(index -> {
            map.put("key-" + index, index);
        });
    }

    @Test
    @DisplayName("")
    public void map_for_each() {
        map.forEach((key, value) -> logger.debug("key {}, value {}", key, value));
    }

    @Test
    public void map_sort() {
        map.entrySet()
        .stream()
        .sorted(Map.Entry.<String, Integer>comparingByKey().reversed())
//        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
        .forEach(stringIntegerEntry -> {
            logger.debug("key {}, value {}", stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
        });
    }

    @Test
    public void map_get_or_default() {

    }
}
