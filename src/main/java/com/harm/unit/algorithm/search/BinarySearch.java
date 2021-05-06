package com.harm.unit.algorithm.search;

import com.google.common.base.Stopwatch;
import com.harm.unit.Unit;
import com.harm.unit.UnitRunner;
import com.harm.unit.lang.stream.spliterator.SpliteratorStudy001;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BinarySearch implements Unit {
    Logger logger = LoggerFactory.getLogger(SpliteratorStudy001.class);
    public static void main(String[] args) {
        UnitRunner.start(new BinarySearch());
    }

    @Override
    public void execute() throws Exception {
        List<Integer> integerList = IntStream.range(0, 1_000_000).boxed().collect(Collectors.toList());
//        Collections.shuffle(integerList);
        logger.debug("list -> {}", integerList);
        int target = 900_000;
        Stopwatch stopwatch = Stopwatch.createStarted();
        logger.debug("binary search find -> {}", bsearch(integerList, target));
        logger.debug("take time -> {}", stopwatch.stop());

        stopwatch = Stopwatch.createStarted();
        logger.debug("normal search find -> {}", nsearch(integerList, target));
        logger.debug("take time -> {}", stopwatch.stop());
    }

    boolean bsearch(List<Integer> integerList, int target) {
        int lo = 0;
        int hi = integerList.size()-1;
        while(lo <= hi) {
            int mid = (lo+hi)/2;
            if(integerList.get(mid) == target) {
                return true;
            } else if(target < integerList.get(mid)){
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return false;
    }
    boolean nsearch(List<Integer> integerList, int target) {
        for(Integer integer : integerList) {
            if(target == integer) {
                return true;
            } else if(target < integer) {
                return false;
            }
        }
        return false;
    }
}
