package com.harm.unit.algorithm.programers.level3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class C30L42577_ListOfPhoneNumber {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(C30L42577_ListOfPhoneNumber.class);
        class Data {
            final String[] phoneNumbers;
            final boolean answer;
            public Data(String[] phoneNumbers, boolean answer) {
                this.phoneNumbers = phoneNumbers;
                this.answer = answer;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "phoneNumbers=" + Arrays.toString(phoneNumbers) +
                        ", answer=" + answer +
                        '}';
            }
        }
        ArrayList<Data> datas = new ArrayList<>();
        datas.add(new Data(new String[]{"119", "97674223", "1195524421"}, false));
        datas.add(new Data(new String[]{"123", "456", "789"}, true));
        datas.add(new Data(new String[]{"12", "123", "1235", "567", "88"}, false));
        for(Data data : datas) {
            logger.debug("data {}, solution {}", data, new Solution().solution(data.phoneNumbers));
        }
    }
    static class Solution {
        public boolean solution(String[] phone_book) {
            for(String phone1 : phone_book) {
                for(String phone2 : phone_book) {
                    if(!phone1.equals(phone2) && phone1.length() <= phone2.length() && phone2.indexOf(phone1) == 0) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
