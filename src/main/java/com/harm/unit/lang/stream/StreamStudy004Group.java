package com.harm.unit.lang.stream;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.harm.unit.lang.stream.StreamStudy003Reduce.Dish;

public class StreamStudy004Group implements Unit {
    private final Logger logger = LoggerFactory.getLogger(StreamStudy004Group.class);
    @Override
    public Object execute(Object[] obj) throws Exception {
        ArrayList<Dish> menu = new ArrayList<>();
        menu.add(new Dish("치킨", 1800, Dish.TYPE.MEAT));
        menu.add(new Dish("스테이크", 1300, Dish.TYPE.MEAT));
        menu.add(new Dish("햄버거", 800, Dish.TYPE.MEAT));
        menu.add(new Dish("공기밥", 300, Dish.TYPE.OTHER));
        Map<Dish.TYPE, List<Dish>> map = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        logger.debug("group by type : {}", map);
        return null;
    }


    public static void main(String[] args) throws Exception {
//        UnitRunner.start(new StreamStudy003());
        new StreamStudy004Group().execute(null);
    }
}
