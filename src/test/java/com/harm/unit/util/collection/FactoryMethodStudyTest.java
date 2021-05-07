package com.harm.unit.util.collection;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 팩토리 메소드로 만들어진 컬렉션과 맵은 불변이기 때문에, 수정하려고하면 UnsupportedOperationException 발생.
 *
 * */
public class FactoryMethodStudyTest {
    Logger logger = LoggerFactory.getLogger(FactoryMethodStudyTest.class);

    @Test
    public void 리스트_팩토리_메소드_항목_추가() {
        /** 팩토리 메소드로 리스트 생성 */
        List<String> friends = List.of("joel", "paul", "tom");
        logger.debug("friends, {}", friends);

        org.junit.jupiter.api.Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            /** 팩토리 메소드로 만든 리스트에 아이템을 추가하면 UnsupportedOperationException 가 발생한다. */
            friends.add("can i join?");
        });
    }

    @Test
    public void 셋_팩토리_메소드_항목_추가() {
        /** 팩토리 메소드로 리스트 생성 */
        Set<String> friends = Set.of("joel", "paul", "tom");
        logger.debug("friends, {}", friends);

        org.junit.jupiter.api.Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            /** 팩토리 메소드로 만든 리스트에 아이템을 추가하면 UnsupportedOperationException 가 발생한다. */
            friends.add("can i join?");
        });
    }

    @Test
    public void 맵_팩토리() {
        /** 맵도 팩토리 메소드가 있다. */
        Map<String, Integer> friends = Map.of("joel", 10, "paul", 20, "tom", 30);
        logger.debug("friends {}", friends);

        /** entry 를 사용하여 만들 수 도 있다. */
        friends = Map.ofEntries(
                Map.entry("joel", 20),
                Map.entry("paul", 30),
                Map.entry("tom", 40)
        );
        logger.debug("friends {}", friends);
    }
}