package com.harm.unit.util.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @DisplayName("맵을 이용한 for each")
    public void map_for_each() {
        map.forEach((key, value) -> logger.debug("key {}, value {}", key, value));
    }

    @Test
    @DisplayName("Map.entrySet 을 이용하여 sort 할 수 있다.")
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
    @DisplayName("키가 없다면, 기본값을 반환")
    public void map_get_or_default() {
        assertEquals(999, map.getOrDefault("not-exists-key", 999));
        logger.debug("check map {}", map);
    }

    @Test
    @DisplayName("키가 없으면, 키의 길이를 세팅하고 반환")
    public void map_compute_if_absent() {
        String notExistsKey = "not-exists-key";
        assertEquals(14, map.computeIfAbsent(notExistsKey, String::length));
        assertEquals(14, map.get(notExistsKey));
        logger.debug("check map {}", map);
    }

    @Test
    @DisplayName("")
    public void map_compute_if_present() {

    }
}
