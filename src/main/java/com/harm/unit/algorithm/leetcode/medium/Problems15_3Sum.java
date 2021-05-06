package com.harm.unit.algorithm.leetcode.medium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 오후 14:45 화요일 2021-01-26
 * 오후 16:04 화요일 2021-01-26
 *  */
public class Problems15_3Sum {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems15_3Sum.class);
        class Data {
            final int[] nums;
            final List<List<Integer>> expects;

            public Data(int[] nums, List<List<Integer>> expects) {
                this.nums = nums;
                this.expects = expects;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "nums=" + Arrays.toString(nums) +
                        ", expects=" + expects +
                        '}';
            }
        }
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data(new int[]{-1,0,1,2,-1,-4}, List.of(List.of(-1,-1,2), List.of(-1,0,1))));
        for(Data data : dataList) {
            logger.debug("data {}, solution {}", data, new Problems15_3Sum.Solution().threeSum(data.nums).stream().map(list -> list.toString()).collect(Collectors.joining(", ")));
        }

    }
    static class Solution {
        //        Logger logger = LoggerFactory.getLogger(Problems15_3Sum.Solution.class);
        public List<List<Integer>> threeSum(int[] nums) {
            Map<Integer, Set<Integer>> numToIndex = new HashMap<>();
            IntStream.range(0, nums.length).forEach(index -> {
                if(!numToIndex.containsKey(nums[index])) {
                    numToIndex.put(nums[index], new HashSet<>());
                }
                numToIndex.get(nums[index]).add(index);
            });

            List<List<Integer>> tempResults = new ArrayList<>();
            Set<Integer> filterSet = new HashSet<>();
            for(int i=0; i<nums.length; i++) {
                int first = nums[i];
                int firstIndex = i;
                if(filterSet.contains(first)) continue;
                Set<Integer> seconds = getPossibleSecondNumberSet(nums, firstIndex, numToIndex);
                seconds.stream().forEach(second -> {
                    List<Integer> result = new ArrayList<>();
                    result.add(first);
                    result.add(second);
                    result.add(0-(first+second));
                    tempResults.add(result);
                });
            }
            Set<Set<Integer>> resultFilter = new HashSet<>();
            List<List<Integer>> results = new ArrayList<>();
            tempResults.stream().forEach(list -> {
                Set<Integer> listSet = new HashSet<>();
                list.stream().forEach(integer -> listSet.add(integer));
                if(!resultFilter.contains(listSet)) {
                    resultFilter.add(listSet);
                    results.add(list);
                }
            });

            return results;
        }

        Set<Integer> getPossibleSecondNumberSet(int[] nums, int firstIndex, Map<Integer, Set<Integer>> numToIndex) {
            Set<Integer> results = new HashSet<>();
            Set<Integer> filterSet = new HashSet<>();
            for(int i=0; i<nums.length; i++) {
                if(i == firstIndex) continue;
                int first = nums[firstIndex];
                int second = nums[i];
                int secondIndex = i;
                if(filterSet.contains(second)) continue;
                int target = 0-(first + second);
                if(numToIndex.containsKey(target)) {
                    if(numToIndex.get(target).stream().filter(targetIndex -> targetIndex != firstIndex && targetIndex != secondIndex).findAny().isPresent()) {
                        results.add(second);
                    }
                }
            }
            return results;
        }
    }
    static class SolutionOld {
//        Logger logger = LoggerFactory.getLogger(Problems15_3Sum.Solution.class);
        public List<List<Integer>> threeSum(int[] nums) {
            Map<Integer, List<Integer>> indexList = new HashMap<>();
            IntStream.range(0, nums.length).forEach(index -> {
                if(!indexList.containsKey(nums[index])) indexList.put(nums[index], new ArrayList<>());
                indexList.get(nums[index]).add(index);
            });
            final int TARGET_SUM = 0;
            Set<Set<Integer>> resultSets = new HashSet<>();
            for(int i=0; i<nums.length; i++) {
                int firstIndex = i;
                int first = nums[i];
                int othersSum = TARGET_SUM-first;
                Set<Set<Integer>> othersIndexSets = getOthersIndexSetsWithSumExceptGivenIndex(nums, firstIndex, othersSum, indexList);
                if(othersIndexSets.size() == 0) continue;
                for(Set<Integer> othersIndexSet : othersIndexSets) {
                    othersIndexSet.add(firstIndex);
                    resultSets.add(othersIndexSet);
                }
            }
            Set<Set<Integer>> filterResultSet = new HashSet<>();
            List<List<Integer>> results = new ArrayList<>();
            resultSets.stream().forEach(indexSet -> {
                Set<Integer> numsSet = indexSet.stream().mapToInt(index -> nums[index]).boxed().collect(Collectors.toSet());
                if(!filterResultSet.contains(numsSet)) {
                    filterResultSet.add(numsSet);
                    results.add(indexSet.stream().mapToInt(index -> nums[index]).boxed().collect(Collectors.toList()));
                }
            });
            return results;
        }

        Set<Set<Integer>> getOthersIndexSetsWithSumExceptGivenIndex(int[] nums, int givenIndex, int othersSum, Map<Integer, List<Integer>> indexList) {
            Set<Set<Integer>> othersSets = new HashSet<>();
            for(int i=0; i<nums.length; i++) {
                if(i == givenIndex) continue;
                int first = nums[i];
                int firstIndex = i;
                int target = othersSum-first;
                if(indexList.containsKey(target)) {
                    indexList.get(target).stream()
                            .filter(targetIndex -> targetIndex != givenIndex && targetIndex != firstIndex)
                            .forEach(targetIndex -> {
                                Set<Integer> others = new HashSet<>();
                                others.add(firstIndex);
                                others.add(targetIndex);
                                othersSets.add(others);
                            });
                }
            }
            return othersSets;
        }

    }
}