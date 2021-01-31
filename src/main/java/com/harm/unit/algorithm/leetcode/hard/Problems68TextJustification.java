package com.harm.unit.algorithm.leetcode.hard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  */
public class Problems68TextJustification {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems68TextJustification.class);
        class Data {
            final String[] words;
            final int maxWidth;

            @Override
            public String toString() {
                return "Data{" +
                        "words=" + Arrays.toString(words) +
                        ", maxWidth=" + maxWidth +
                        '}';
            }

            public Data(String[] words, int maxWidth) {
                this.words = words;
                this.maxWidth = maxWidth;
            }
        }
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));
        dataList.add(new Data(new String[]{"What","must","be","acknowledgment","shall","be"}, 16));
        dataList.add(new Data(new String[]{"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"}, 20));
        dataList.add(new Data(new String[]{"a"}, 1));

        for(Data data : dataList) {
            logger.debug("data {}", data);
            List<String> results = new Problems68TextJustification.Solution().fullJustify(data.words, data.maxWidth);
            logger.debug("solution");
            results.stream().forEach(result -> logger.debug("[{}], {}", result, result.length()));
        }

    }
    static class Solution {
        public List<String> fullJustify(String[] words, int maxWidth) {
            List<String> results = new ArrayList<>();
            List<Integer> lineIndexBucket = new ArrayList<>();

            int lineWidth = 0;
            for(int i=0; i<words.length; i++) {
                if(i == words.length-1) {
                    if(lineWidth + words[i].length() < maxWidth) {
                        lineIndexBucket.add(i);
                        results.add(makeLast(words, lineIndexBucket, maxWidth));
                    } else {
                        if(lineIndexBucket.size() > 0) {
                            results.add(makeLine(words, lineIndexBucket, maxWidth));
                            lineIndexBucket.clear();
                        }
                        lineIndexBucket.add(i);
                        results.add(makeLast(words, lineIndexBucket, maxWidth));
                    }
                } else if(lineWidth == 0 && words[i].length() == maxWidth) {
                    lineIndexBucket.add(i);
                    lineWidth+=words[i].length();
                } else if(lineWidth + words[i].length() < maxWidth) {
                    int spacing = 0;
                    if(lineIndexBucket.size() > 0) {
                        spacing = 1;
                    }
                    lineIndexBucket.add(i);
                    lineWidth+=words[i].length()+spacing;
                } else {
                    i--;
                    results.add(makeLine(words, lineIndexBucket, maxWidth));
                    lineIndexBucket.clear();
                    lineWidth = 0;
                }
            }
            return results;
        }
        String makeLine(String[] words, List<Integer> lineIndexBucket, int maxWidth) {
            StringBuffer lineBuffer = new StringBuffer();
            if(lineIndexBucket.size() == 1) {
                String spacing = getSpacing(maxWidth-words[lineIndexBucket.get(0)].length());
                lineBuffer.append(words[lineIndexBucket.get(0)]).append(spacing);
            } else {
                int lineWidth = lineIndexBucket.stream().mapToInt(i -> words[i].length()).sum();
                int spacePositionCount = lineIndexBucket.size()-1;
                int spaceCountPerSpacePosition = (maxWidth-lineWidth)/spacePositionCount;
                int spaceRest = (maxWidth-lineWidth)%spacePositionCount;
                String spacing = getSpacing(spaceCountPerSpacePosition);
                String spacingPlus = getSpacing(spaceCountPerSpacePosition+1);
                lineBuffer.append(words[lineIndexBucket.get(0)]);
                for(int i=1; i<lineIndexBucket.size(); i++) {
                    if(spaceRest > 0) {
                        lineBuffer.append(spacingPlus).append(words[lineIndexBucket.get(i)]);
                        spaceRest--;
                    } else {
                        lineBuffer.append(spacing).append(words[lineIndexBucket.get(i)]);
                    }
                }
            }
            return lineBuffer.toString();
        }
        String makeLast(String[] words, List<Integer> lineIndexBucket, int maxWidth) {
            int lineWidth = lineIndexBucket.stream().mapToInt(i -> words[i].length()).sum();
            int spacePositionCount = lineIndexBucket.size()-1;
            StringBuffer lineBuffer = new StringBuffer();
            for(int i=0; i<lineIndexBucket.size(); i++) {
                if(i!=0) {
                    lineBuffer.append(getSpacing(1));
                }
                lineBuffer.append(words[lineIndexBucket.get(i)]);
            }
            lineBuffer.append(getSpacing(maxWidth-lineBuffer.length()));
            return lineBuffer.toString();
        }
        String getSpacing(int num) {
            final String SPACE = " ";
            StringBuffer spacingBuffer = new StringBuffer();
            for(int i=0; i<num; i++) {
                spacingBuffer.append(SPACE);
            }
            return spacingBuffer.toString();
        }
    }
}