package com.harm.unit.algorithm.programers.level2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SkillCheckL234051_TruckPassingBridge {
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(SkillCheckL234051_TruckPassingBridge.class);
        class Data {
            final int bridge_length;
            final int weight;
            final int[] truck_weights;
            final int expectSecond;
            public Data(int bridge_length, int weight, int[] truck_weights, int expectSecond) {
                this.bridge_length = bridge_length;
                this.weight = weight;
                this.truck_weights = truck_weights;
                this.expectSecond = expectSecond;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "bridge_length=" + bridge_length +
                        ", weight=" + weight +
                        ", truck_weights=" + Arrays.toString(truck_weights) +
                        ", expectSecond=" + expectSecond +
                        '}';
            }
        }
        ArrayList<Data> datas = new ArrayList<>();
        datas.add(new Data(2, 10, new int[]{7,4,5,6}, 8));
        datas.add(new Data(100, 100, new int[]{10}, 101));
        datas.add(new Data(100, 100, new int[]{10,10,10,10,10,10,10,10,10,10}, 110));

        datas.stream().forEach(data -> {
            logger.debug("data -> {}, result -> {}", data, new Solution().solution(data.bridge_length, data.weight, data.truck_weights));
        });
    }
    static class Solution {
//        Logger logger = LoggerFactory.getLogger(SkillCheckL234051_TruckPassingBridge.Solution.class);
        public int solution(int bridgeLength, int weight, int[] truckWeights) {
            class PassingTruckInfo {
                int weight;
                int inSecond;
                public PassingTruckInfo(int weight, int inSecond) {
                    this.weight = weight;
                    this.inSecond = inSecond;
                }

                @Override
                public String toString() {
                    return "PassingTruckInfo{" +
                            "weight=" + weight +
                            ", inSecond=" + inSecond +
                            '}';
                }
            }
            Queue<PassingTruckInfo> bridge = new LinkedList<>();
            int totalPassingSeconds = 0;
            int totalWeightOnBridge = 0;

            for(int i=0; i<truckWeights.length || !bridge.isEmpty();) {
//                logger.debug("second {}, bridge {}", totalPassingSeconds, bridge);
                if(!bridge.isEmpty()) {
                    PassingTruckInfo pti = bridge.peek();
                    if(totalPassingSeconds - pti.inSecond >= bridgeLength) {
                        pti = bridge.poll();
                        totalWeightOnBridge -= pti.weight;
                    }
                }
                if(i<truckWeights.length) {
                    int nextTruckWeight = truckWeights[i];
                    if(weight - totalWeightOnBridge >= nextTruckWeight) {
                        i++;
                        bridge.add(new PassingTruckInfo(nextTruckWeight, totalPassingSeconds));
                        totalWeightOnBridge += nextTruckWeight;
                    }
                }
                totalPassingSeconds++;
            }
            return totalPassingSeconds;
        }
    }
}