package com.harm.unit.algorithm.leetcode.medium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 끝
 * */
public class Problems146LRUCache {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems146LRUCache.class);
//        String[] commands = {"LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"};
//        int[][] inputs =    {{2},  {1, 1}, {2, 2}, {1}, {3, 3}, {2}, {4, 4}, {1}, {3}, {4}};
//        Integer[] expects = {null, null,   null,   1,   null,   -1,  null,   -1,  3,   4};
        String[] commands = {"LRUCache","get","put","get","put","put","get","get"};
        int[][] inputs =    {{2},{2},{2,6},{1},{1,5},{1,2},{1},{2}};
        Integer[] expects = {null,-1,null,-1,null,null,2,6};

        List<Integer> outputs = new ArrayList<>();
        LRUCache lruCache = null;
        logger.debug("command -> {}", Arrays.toString(commands));
        logger.debug("inputs  -> {}", Arrays.stream(inputs).map(Arrays::toString).collect(Collectors.joining(", ")));
        for(int i=0; i<commands.length; i++) {
            Integer output = null;
            String command = commands[i];
            if("LRUCache".equals(command)) {
                lruCache = new LRUCache(inputs[i][0]);
            } else if("put".equals(command)) {
                lruCache.put(inputs[i][0], inputs[i][1]);
            } else if("get".equals(command)) {
                output = lruCache.get(inputs[i][0]);
            } else {
                throw new IllegalArgumentException();
            }
            outputs.add(output);
        }
        logger.debug("expects -> {}", Arrays.toString(expects));
        logger.debug("results -> [{}]", outputs.stream().map(String::valueOf).collect(Collectors.joining(", ")));
    }
    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    static class CacheUnit {
        final int key;
        final int value;
        public CacheUnit(int key, int value) {
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString() {
            return "CacheUnit{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
    static class LRUCache {
        LinkedHashMap<Integer, CacheUnit> linkedHashMap = null;
        final int capacity;
        public LRUCache(int capacity) {
            linkedHashMap = new LinkedHashMap<>(capacity, 0.75f, true);
            this.capacity = capacity;
        }
        public int get(int key) {
            CacheUnit cacheUnit = linkedHashMap.get(key);
            if(cacheUnit == null) return -1;
            return cacheUnit.value;
        }

        public void put(int key, int value) {
            if(linkedHashMap.containsKey(key)) {
                linkedHashMap.put(key, new CacheUnit(key, value));
            } else {
                if(linkedHashMap.size() >= capacity) {
                    CacheUnit first = linkedHashMap.entrySet().stream().findFirst().get().getValue();
                    linkedHashMap.remove(first.key);
                }
                linkedHashMap.put(key, new CacheUnit(key, value));
            }
        }
    }
    static class LRUCacheOld {
//        Logger logger = LoggerFactory.getLogger(Problems146LRUCache.LRUCache.class);
        final int capacity;
        Map<Integer, CacheUnit> cacheRepository = new HashMap<>();
        List<CacheUnit> lruRepository = new ArrayList<>();
        public LRUCacheOld(int capacity) {
            this.capacity = capacity;
        }
        public int get(int key) {
            CacheUnit foundCacheUnitFromCacheRepository = cacheRepository.get(key);
//            logger.debug("get -> key {}, found {}", key, foundCacheUnitFromCacheRepository);
            if(foundCacheUnitFromCacheRepository == null) {
                return -1;
            } else {
//                logger.debug("before lru repository -> {}", lruRepository.toString());
                int foundCacheUnitIndex = findIndex(key);
                if(foundCacheUnitIndex != -1) {
                    CacheUnit cacheUnit = lruRepository.remove(foundCacheUnitIndex);
                    lruRepository.add(cacheUnit);
                }
//                logger.debug("after lru repository -> {}", lruRepository.toString());
                return foundCacheUnitFromCacheRepository.value;
            }
        }
        public void put(int key, int value) {
            if(cacheRepository.containsKey(key)) {
                int foundCacheUnitIndex = findIndex(key);
                lruRepository.remove(foundCacheUnitIndex);
                cacheRepository.remove(key);
            }
            if(cacheRepository.size() >= capacity) {
                CacheUnit removedCacheUnit = lruRepository.remove(0);
                cacheRepository.remove(removedCacheUnit.key);
            }
            CacheUnit cacheUnit = new CacheUnit(key, value);
            cacheRepository.put(key, cacheUnit);
            lruRepository.add(cacheUnit);
        }
        int findIndex(int key) {
            int foundIndex = -1;
            for(int i=0; i<lruRepository.size(); i++) {
                CacheUnit lruItem = lruRepository.get(i);
                if(lruItem.key == key) {
                    foundIndex = i;
                    break;
                }
            }
            return foundIndex;
        }
    }
}