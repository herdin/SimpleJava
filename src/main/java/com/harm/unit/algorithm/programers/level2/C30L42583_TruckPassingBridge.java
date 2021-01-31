package com.harm.unit.algorithm.programers.level2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class C30L42583_TruckPassingBridge {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(C30L42583_TruckPassingBridge.class);
        ArrayList<Data> dataList = new ArrayList<>();
        dataList.add(new Data(2, 10, new int[]{7, 4, 5, 6}, 8));
        dataList.add(new Data(100, 100, new int[]{10}, 101));
        dataList.add(new Data(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10}, 110));

        for(Data data : dataList) {
            logger.debug("data -> {}, result -> {}", data, new Solution().solution(data.bridge_length, data.weight, data.truck_weights));
        }
    }
    static class Data {
        int bridge_length;
        int weight;
        int[] truck_weights;
        int seconds;
        public Data(int bridge_length, int weight, int[] truck_weights, int seconds) {
            this.bridge_length = bridge_length;
            this.weight = weight;
            this.truck_weights = truck_weights;
            this.seconds = seconds;
        }
        @Override
        public String toString() {
            return "Data{" +
                    "bridge_length=" + bridge_length +
                    ", weight=" + weight +
                    ", truck_weights=" + Arrays.toString(truck_weights) +
                    '}';
        }
    }
    static class Solution {
//        public Logger logger = LoggerFactory.getLogger(C30L42583_TruckPassingBridge.Solution.class);
        public int solution(int bridgeLength, int maxBridgeWeight, int[] truckWeights) {
            class TruckPassingInfo {
                int weight;
                int inSecond;
                public TruckPassingInfo(int weight, int inSecond) {
                    this.weight = weight;
                    this.inSecond = inSecond;
                }
                @Override
                public String toString() {
                    return "TruckPassingInfo{" +
                            "weight=" + weight +
                            ", inSecond=" + inSecond +
                            '}';
                }
            }
            Queue<TruckPassingInfo> bridgeQueue = new LinkedList<TruckPassingInfo>();
            int totalWeightOnBridge = 0;
            int second = 0;
            int truckIndex = 0;

            while(truckIndex<truckWeights.length || !bridgeQueue.isEmpty()) {
                second++;
                if(!bridgeQueue.isEmpty()) {
                    TruckPassingInfo tpi = bridgeQueue.peek();
                    if(second - tpi.inSecond >= bridgeLength) {
                        tpi = bridgeQueue.poll();
                        totalWeightOnBridge -= tpi.weight;
                    }
                }

//                logger.debug("second {} index {} bride weight ({}/{})", second, truckIndex, totalWeightOnBridge, maxBridgeWeight);
                if(truckIndex >= truckWeights.length) continue;

                int truckWeight = truckWeights[truckIndex];
                if(maxBridgeWeight-totalWeightOnBridge >= truckWeight) {
                    bridgeQueue.add(new TruckPassingInfo(truckWeight, second));
                    totalWeightOnBridge += truckWeight;
                    truckIndex++;
                }
            }
            return second;
        }
    }
}


