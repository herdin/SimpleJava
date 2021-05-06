package com.harm.unit.lang.stream.spliterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {
    Logger logger = LoggerFactory.getLogger(WordCounterSpliterator.class);
    final String string;
    final int stringLength;
    final int splitWordLength;
    int currentIndex = 0;

    public WordCounterSpliterator(String string, int splitWordLength) {
        this.string = string;
        stringLength = string.length();
        this.splitWordLength = splitWordLength;
        logger.debug("constructor(), {}", this);
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        logger.debug("tryAdvance(), {}", this);
        action.accept(string.charAt(currentIndex++));
        return currentIndex < stringLength;
    }

    @Override
    public Spliterator<Character> trySplit() {
        logger.debug("trySplit(), {}", this);
        int currentSize = stringLength - currentIndex;
        if(currentSize < splitWordLength) {
            return null;
        }
        for(int splitPos = currentSize/2 + currentIndex; splitPos < stringLength; splitPos++) {
            if(Character.isWhitespace(string.charAt(splitPos))) {
                Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentIndex, splitPos), splitWordLength);
                currentIndex = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        logger.debug("estimateSize(), {}", this);
        return stringLength - currentIndex;
    }

    @Override
    public int characteristics() {
        logger.debug("characteristics(), {}", this);
        return ORDERED + SIZED + NONNULL + IMMUTABLE;
    }

    @Override
    public String toString() {
        return "WordCounterSpliterator{" +
                "string='" + "..." + '\'' +
                ", stringLength=" + stringLength +
                ", splitWordLength=" + splitWordLength +
                ", currentIndex=" + currentIndex +
                '}';
    }
}
