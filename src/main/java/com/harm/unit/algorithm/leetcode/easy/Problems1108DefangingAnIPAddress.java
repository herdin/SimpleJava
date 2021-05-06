package com.harm.unit.algorithm.leetcode.easy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/** Easydlald
 * 끝
 * */
public class Problems1108DefangingAnIPAddress {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems1108DefangingAnIPAddress.class);
        String address = "127.0.0.1";
        logger.debug("address {}, solution {}", address, new Solution().defangIPaddr(address));
    }
    static class Solution {
        public String defangIPaddr(String address) {
            return address.replaceAll("[\\.]", "[.]");
        }
    }
}
