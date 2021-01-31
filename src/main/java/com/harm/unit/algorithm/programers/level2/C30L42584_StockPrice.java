package com.harm.unit.algorithm.programers.level2;

import com.harm.unit.pattern.validate.custom002.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.print.PrinterGraphics;
import java.util.*;

public class C30L42584_StockPrice {
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(C30L42584_StockPrice.class);
        int[] prices= {1, 2, 3, 2, 3};
        logger.debug("price -> {}", prices);
        logger.debug("result -> {}", new C30L42584_StockPrice.Solution().solution(prices));
    }

    static class Solution {
        public int[] solution(int[] prices) {
//        Logger logger = LoggerFactory.getLogger(C30L42584_StockPrice.class);
            class HowLongDoesItLast {
                int index = 0;
                int price = 0;
                int second = 0;

                @Override
                public String toString() {
                    return "HowLongDoesItLast{" +
                            "index=" + index +
                            ", price=" + price +
                            ", second=" + second +
                            '}';
                }
            }

            HashMap<Integer, HowLongDoesItLast> sourceMap = new HashMap<>();
            HashMap<Integer, HowLongDoesItLast> resultMap = new HashMap<>();
            for(int i=0; i<prices.length; i++) {
                final int currentIndex = i;
                final int currentPrice = prices[i];
                if(!sourceMap.containsKey(currentIndex) && !resultMap.containsKey(currentIndex)) {
                    HowLongDoesItLast hldil = new HowLongDoesItLast();
                    hldil.index = currentIndex;
                    hldil.price = currentPrice;
                    sourceMap.put(currentIndex, hldil);
                }
                if(i == 0) {
                    continue;
                }
                Iterator<Map.Entry<Integer, HowLongDoesItLast>> iter = sourceMap.entrySet().iterator();
                while(iter.hasNext()) {
                    HowLongDoesItLast hldil = iter.next().getValue();
                    if(hldil.index != currentIndex) {
                        hldil.second++;
                    }
                    if (hldil.price > currentPrice) {
                        iter.remove();
                        resultMap.put(hldil.index, hldil);
                    }
                }
            }


            sourceMap.forEach((integer, howLongDoesItLast) -> resultMap.put(integer, howLongDoesItLast));
//        sourceMap.forEach((integer, howLongDoesItLast) -> logger.debug("index={}, how long={}", integer, howLongDoesItLast));
            int[] answer = new int[prices.length];
            for(int i=0; i<answer.length; i++) {
                answer[i] = resultMap.get(i).second;
            }
            return answer;
        }
    }
}