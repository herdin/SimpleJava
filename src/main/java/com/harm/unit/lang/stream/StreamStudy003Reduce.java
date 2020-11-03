package com.harm.unit.lang.stream;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class StreamStudy003Reduce implements Unit {
    private final Logger logger = LoggerFactory.getLogger(StreamStudy003Reduce.class);
    @Override
    public Object execute(Object[] obj) throws Exception {
        ArrayList<Dish> menu = new ArrayList<>();
        menu.add(new Dish("치킨", 1800, Dish.TYPE.MEAT));
        menu.add(new Dish("스테이크", 1300, Dish.TYPE.MEAT));
        menu.add(new Dish("햄버거", 800, Dish.TYPE.MEAT));
        menu.add(new Dish("공기밥", 300, Dish.TYPE.OTHER));

        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> maxCaloriesDish = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        logger.debug("most calorieDish : {}", maxCaloriesDish.orElse(new Dish("no", 0,  Dish.TYPE.OTHER)));

        int caloriesSum = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        logger.debug("sum calories : {}", caloriesSum);

        caloriesSum = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
        logger.debug("sum calories with reduce : {}", caloriesSum);

        caloriesSum = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        logger.debug("sum calories with another reduce : {}", caloriesSum);

        /**
         * collect 와 reduce 를 같은 용도로 사용하게 되면,
         * collect method 는 도출하려는 결과를 누적하는 컨테이너를 바꾸도록 설계된 method 인 반면
         * reduce method 는 두 값을 하나로 도출하는 불변형 연산이라는 점에서 의미론적인 문제가 있음.
         *
         * 또, 여러 thread 가 동시에 같은 데이터 구조체를 고치면 리스트 자체가 망가지므로 reduce 연산을
         * 병렬로 수행할 수 없다는 것도 문제가 된다. 이 문제를 해결하기 위해서는 매번 새로운 리스트를 할당해야하고,
         * 객체를 할당하느라 성능이 저하되므로 실용적인 문제도 발생한다.
         */

        IntSummaryStatistics menuStatistic = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        logger.debug("statistic calories : {}", menuStatistic);

        Optional<Dish> minimumDish = menu.stream().collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories()? d2:d1));
        logger.debug("minimum calories : {}", minimumDish.get());

        String menuNames = menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        logger.debug("dish name joining : {}", menuNames);

        menuNames = menu.stream().map(Dish::getName).collect(Collectors.reducing((d1, d2) -> d1 + d2)).get();
        logger.debug("dish name joining with reduce: {}", menuNames);


        return null;
    }


    public static class Dish {
        public enum TYPE {
            FISH, MEAT, OTHER
        }
        String name;
        int calories;
        TYPE type;

        public Dish(String name, int calories, TYPE type) {
            this.name = name;
            this.calories = calories;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public String setName(String name) {
            this.name = name;
            return null;
        }

        public int getCalories() {
            return calories;
        }

        public void setCalories(int calories) {
            this.calories = calories;
        }

        public TYPE getType() {
            return type;
        }

        public void setType(TYPE type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Dish{" +
                    "name='" + name + '\'' +
                    ", calories=" + calories +
                    ", type=" + type +
                    '}';
        }
    }
    public static void main(String[] args) throws Exception {
//        UnitRunner.start(new StreamStudy003());
        new StreamStudy003Reduce().execute(null);
    }
}
