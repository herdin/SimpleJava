package com.harm.unit.leetcode;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;

/*
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.
 */
public class Problems03LongestSubstringWithoutRepeatingCharacters implements Unit {
    public final Logger logger = LoggerFactory.getLogger(Problems03LongestSubstringWithoutRepeatingCharacters.class);

    @Override
    public Object execute(Object[] objs) throws Exception {


        String s = (String)objs[0];

        HashSet<Character> hs = new HashSet<>();
        StringBuffer sb = new StringBuffer();
        String maxStr = "";
        char[] chs = s.toCharArray();
        boolean isFrist = true;
        int firstIndex = 0;

        for(int i=0; i<chs.length; i++) {
            char ch = chs[i];
            if(!hs.contains(ch)) {
                if(isFrist) {
                    isFrist = false;
                    firstIndex = i;
                }
                sb.append(ch);
                hs.add(ch);
                if(maxStr.length() < sb.toString().length()) {
                    maxStr = sb.toString();
                }
            } else {
                String curStr = sb.toString();
                if(maxStr.length() < curStr.length()) {
                    maxStr = curStr;
                }
                sb.setLength(0);
                for(int j=0; j<curStr.indexOf(ch); j++) {
                    hs.remove(curStr.charAt(j));
                }
                sb.append(curStr.substring(curStr.indexOf(ch)+1));
                sb.append(ch);

            }
        }

        logger.debug("{} {}", maxStr, maxStr.length());

        return maxStr.length();
    }
}
