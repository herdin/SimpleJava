package com.harm.unit.algorithm.leetcode.easy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Easy
 * 끝
 *  */
public class Problems953VerifyingAnAlienDictionary {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems953VerifyingAnAlienDictionary.class);
        class Data {
            final int[] prices;
            final int expect;

            public Data(int[] prices, int expect) {
                this.prices = prices;
                this.expect = expect;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "prices=" + Arrays.toString(prices) +
                        ", expect=" + expect +
                        '}';
            }
        }
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data(new int[]{7,1,5,3,6,4}, 5));
        dataList.add(new Data(new int[]{7,6,4,3,1}, 0));
        for(Data data : dataList) {
            logger.debug("data {}, solution {}", data.toString(), new Problems953VerifyingAnAlienDictionary.Solution().maxProfit(data.prices));
        }

    }
    static class Solution {
        public int maxProfit(int[] prices) {
            int maxProfit = 0;
            int minPriceIndex = -1;
            for(int i=0; i<prices.length; i++) {
                if(minPriceIndex == -1) {
                    minPriceIndex = i;
                    continue;
                }
                minPriceIndex = (prices[i] < prices[minPriceIndex])? i:minPriceIndex;
                maxProfit = (prices[i] - prices[minPriceIndex] > maxProfit)? prices[i] - prices[minPriceIndex]:maxProfit;
            }
            return maxProfit;
        }
    }
}