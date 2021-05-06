package com.harm.unit.lang.stream.spliterator;

import com.google.common.base.Stopwatch;
import com.harm.unit.Unit;
import com.harm.unit.UnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Spliterator interface 학습을 위한 class
 * */
public class SpliteratorStudy001 implements Unit {
    Logger logger = LoggerFactory.getLogger(SpliteratorStudy001.class);
    public static void main(String[] args) {
        UnitRunner.start(new SpliteratorStudy001());
    }

    @Override
    public void execute() throws Exception {
        int max = 1_000_000;
        Stopwatch stopwatch = null;
        stopwatch = Stopwatch.createStarted();
        logger.debug("range {} ~ {} -> sum -> {}", 0, max, IntStream.rangeClosed(0, max).sum());
        stopwatch.stop();
        logger.debug("{}", stopwatch);
        logger.debug("range {} ~ {} -> sum -> {}", 0, max, IntStream.rangeClosed(0, max).parallel().sum());

        String SENTENCE =
            "they had their faces twisted toward their haunches " +
            "and found it necessary to walk backward, " +
            "because they could not see ahead of them. " +
            "and since he wanted so to see ahead, " +
            "he looks behind and walks a backward path."; //39
        Stream<Character> stream = null;
        stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        logger.debug("1st word counter -> {}", countWord(stream));
        stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt).parallel();
        logger.debug("2nd word counter with parallel -> {}", countWord(stream));
        stream = StreamSupport.stream(new WordCounterSpliterator(SENTENCE, 10), true);
        logger.debug("3rd word counter with custom spliterator + parallel -> {}", countWord(stream));
    }

    int countWord(Stream<Character> stream) {
        return stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine).counter;
    }

}
