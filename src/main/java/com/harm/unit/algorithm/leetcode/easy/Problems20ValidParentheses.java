package com.harm.unit.algorithm.leetcode.easy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.IntStream;

/** Easy
 *  */
public class Problems20ValidParentheses {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems20ValidParentheses.class);
        class Data {
            final String s;
            final boolean expect;

            public Data(String s, boolean expect) {
                this.s = s;
                this.expect = expect;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "s='" + s + '\'' +
                        ", expect=" + expect +
                        '}';
            }
        }
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data("()", true));
        dataList.add(new Data("()[]{}", true));
        dataList.add(new Data("([)]", false));
        dataList.add(new Data("{[]}", true));

        for(Data data : dataList) {
            logger.debug("data {}, solution {}", data, new Problems20ValidParentheses.Solution().isValid(data.s));
        }

    }

    static class Solution {
        enum BRACKET {
            SMALL('(', ')'),
            MIDDLE('{', '}'),
            BIG('[', ']'),
            ;
            char start;
            char end;
            BRACKET(char start, char end) {
                this.start = start;
                this.end = end;
            }
            public char start() { return start; }
            public char end() { return end; }
        }
        public boolean isValid(String s) {
            if(s.length()%2 != 0) return false;

            Map<Character, BRACKET> startBracketMap = new HashMap<>();
            Map<Character, BRACKET> endBracketMap = new HashMap<>();
            Arrays.stream(BRACKET.values()).forEach(bracket -> {
                startBracketMap.put(bracket.start, bracket);
                endBracketMap.put(bracket.end, bracket);
            });
            Stack<BRACKET> stack = new Stack<>();
            boolean stackValid = IntStream.range(0, s.length())
                    .allMatch(index -> {
                        boolean somethingWrong = false;
                        char bracketCh = s.charAt(index);
                        if(startBracketMap.containsKey(bracketCh)) {
                            stack.push(startBracketMap.get(bracketCh));
                        } else if(endBracketMap.containsKey(bracketCh)) {
                            BRACKET endBracket = endBracketMap.get(bracketCh);
                            if(stack.size() > 0 && stack.peek().start == endBracket.start) {
                                stack.pop();
                            } else {
                                somethingWrong = true;
                            }
                        } else {
                            somethingWrong = true;
                        }
                        return !somethingWrong;
                    });
            return stackValid && stack.size() == 0;
        }
    }
}