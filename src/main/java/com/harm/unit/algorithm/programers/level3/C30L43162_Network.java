package com.harm.unit.algorithm.programers.level3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class C30L43162_Network {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(C30L43162_Network.class);
        class Data {
            final int n;
            final int[][] computers;
            final int expect;
            public Data(int n, int[][] computers, int expect) {
                this.n = n;
                this.computers = computers;
                this.expect = expect;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "n=" + n +
                        ", computers=[" + Arrays.stream(computers).map(computer -> Arrays.toString(computer)).collect(Collectors.joining()) + "]"+
                        ", expect=" + expect +
                        '}';
            }
        }
        ArrayList<Data> dataList = new ArrayList<>();
        dataList.add(new Data(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}, 2));
        dataList.add(new Data(3, new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}, 1));
        dataList.add(new Data(6, new int[][]{{1, 0, 1, 1, 0, 0}, {0, 1, 0, 0, 1, 1}, {1, 0, 1, 1, 1, 1}, {1, 0, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1}}, 1));

        for(Data data : dataList) {
            logger.debug("data {}, solution {}", data, new C30L43162_Network.Solution().solution(data.n, data.computers));
        }

    }
    static class Solution {
//        Logger logger = LoggerFactory.getLogger(C30L43162_Network.Solution.class);
        public int solution(int n, int[][] computers) {
            ArrayList<Set<Integer>> networks = new ArrayList<>();
            for(int from=0; from<computers.length; from++) {
                for(int to=0; to<computers[from].length; to++) {
//                    logger.debug("from {} to {} - {}", from, to, computers[from][to]);
                    if(from == to) continue;
                    if(computers[from][to] == 1) {
                        boolean existsNetwork = false;
                        for(Set<Integer> network : networks) {
                            if(network.contains(from) || network.contains(to)) {
                                existsNetwork = true;
                                network.addAll(Set.of(from, to));
                                break;
                            }
                        }
                        if(!existsNetwork) {
                            Set<Integer> newNetwork = new HashSet<>();
                            newNetwork.addAll(Set.of(from, to));
                            networks.add(newNetwork);
                        } else {
                            boolean find = false;
                            int addIndex = -1;
                            int removeIndex = -1;
                            for(int i=0; i<networks.size(); i++) {
                                Set<Integer> firstNetwork = networks.get(i);
                                for(int j=0; j<networks.size(); j++) {
                                    if(i==j) continue;
                                    Set<Integer> secondNetwork = networks.get(j);
                                    for(int firstComputer : firstNetwork) {
                                        for(int secondComputer : secondNetwork) {
                                            if(firstComputer == secondComputer) {
                                                find = true;
                                                addIndex = i;
                                                removeIndex = j;
                                                break;
                                            }
                                        }
                                        if(find) break;
                                    }
                                    if(find) break;
                                }
                            }
                            if(find) {
                                networks.get(addIndex).addAll(networks.get(removeIndex));
                                networks.remove(removeIndex);
                            }

                        }
                    }
//                    logger.debug("networks -> {}", networks.stream().map(set -> "{" + set.stream().map(String::valueOf).collect(Collectors.joining(",")) + "}").collect(Collectors.joining()));
                }
            }
            return n - networks.stream().parallel().map(set -> set.size()-1).mapToInt(Integer::intValue).sum();
        }
    }
}
