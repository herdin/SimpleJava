package com.harm.unit.lang.stream;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class StreamStudy002 implements Unit {
    private final Logger logger = LoggerFactory.getLogger(StreamStudy002.class);
    List<Transaction> transactions = null;
    @Override
    public Object execute(Object[] obj) throws Exception {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );

        case1();
        case2();
        case3();
        case4();
        case5();
        case6();
        case7();
        case8();
        return null;
    }

    /**
     * 1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오른차순으로 정리하시오
     */
    private void case1() {
        logger.debug("1 -> ");
        transactions.stream().filter(t -> t.getYear() == 2011).sorted((t1, t2) -> t1.getValue()>t2.getValue()? 1:t1.getValue()==t2.getValue()? 0:-1).forEach(t -> logger.debug("-> {}", t));

    }

    /**
     * 2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오
     */
    private void case2() {
        List<String> cities = transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(Collectors.toList());
        logger.debug("2 -> {}", cities);
    }
    /**
     * 3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오
     */
    private void case3() {
        List<String> names = transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).map(t -> t.getTrader().getName()).distinct().sorted().collect(Collectors.toList());
        logger.debug("3 -> {}", names);
    }
    /**
     * 4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오
     */
    private void case4() {
        List<String> h =  transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().collect(Collectors.toList());
        logger.debug("4 -> {}", h);
    }
    /**
     * 5. 밀라노에 거래자가 있는가?
     */
    private void case5() {
        Optional<Transaction> mtrader = transactions.stream().filter(t -> t.getTrader().getCity().equals("Milan")).findAny();
        logger.debug("5 -> {}", mtrader.isPresent());
    }
    /**
     * 6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오
     */
    private void case6() {
        logger.debug("6 -> ");
        transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).forEach(t -> logger.debug("-> {}", t));
    }
    /**
     * 7. 전체 트랜잭션 중 최댓값은 얼마인가
     */
    private void case7() {
        OptionalInt min = transactions.stream().mapToInt(Transaction::getValue).min();
        logger.debug("7 -> {}", min.orElse(-1));
    }
    /**
     * 8. 전체 트랜잭션 중 최솟값은 얼마인가
     */
    private void case8() {
        OptionalInt max = transactions.stream().mapToInt(Transaction::getValue).max();
        logger.debug("8 -> {}", max.orElse(-1));
    }

    static class Trader {
        private final String name;
        private final String city;

        public Trader(String name, String city) {
            this.name = name;
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public String getCity() {
            return city;
        }

        @Override
        public String toString() {
            return "Trader{" +
                    "name='" + name + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
    }

    static class Transaction {
        private final Trader trader;
        private final int year;
        private final int value;

        public Transaction(Trader trader, int year, int value) {
            this.trader = trader;
            this.year = year;
            this.value = value;
        }

        public Trader getTrader() {
            return trader;
        }

        public int getYear() {
            return year;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "trader=" + trader +
                    ", year=" + year +
                    ", value=" + value +
                    '}';
        }
    }
}
