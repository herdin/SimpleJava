package com.harm.unit.lang.stream;

import com.harm.unit.Unit;
import com.harm.unit.UnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamStudy001 implements Unit {
    private final Logger logger = LoggerFactory.getLogger(StreamStudy001.class);
    @Override
    public Object execute(Object[] obj) throws Exception {
        //List 생성
        List<Integer> list = Arrays.asList(3, 2, 5, 6, 1, 4);
        logger.debug("init list : {}", list);

        //Stream Builder 로 생성
        Stream<String> strStream = Stream.<String>builder()
                .add("a")
                .add("b")
                .add("c")
                .build();
        strStream.forEach(str -> logger.debug("make stream with builder -> {}", str));

        //generate() 로 생성
        //크기가 정해져있지 안기 때문에, limit() 으로 제한해야한다
        int index = 0;
        strStream = Stream.generate(() -> "gen" + index).limit(10);
        strStream.forEach(str -> logger.debug("make stream with generate() -> {}", str));

        Stream
            .iterate(new int[]{0, 1}, (t) -> new int[]{t[1], t[0]+t[1]})
            .limit(20)
            .forEach(t -> logger.debug("-> {}", t));

        //오름차순 정렬
        list.sort(Comparator.comparing(a -> a));
        logger.debug("after sort a -> a : {}", list);

        //
        list.sort(Comparator.comparing(a -> 10-a)); //.reversed() ?
        logger.debug("after sort a -> 10-a : {}", list);
//        list.stream().collect(Collectors.groupingBy())
        list.stream().forEach(a -> logger.debug("for each : {}", String.valueOf(a)));
        list.stream().filter(a -> a > 3).forEach((a) -> logger.debug("after filter a -> a>3 : {}", a));

        logger.debug("-> {}", Arrays.stream(list.toArray(), 0, 3).count());
        return null;
    }

    public static void main(String[] args) {
        UnitRunner.start(new StreamStudy001());
    }

    static class abs {
        String name;
        String id;

    }
}
