package com.harm.unit.algorithm.programers.level1;

import java.util.HashMap;
import java.util.Stack;

public class C30L42576_Player {
    public static void main(String[] args) {
        String[] participant = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki"};
//        String[] participant = {"mislav", "stanko", "mislav", "ana"};
//        String[] completion = {"stanko", "ana", "mislav"};

        System.out.println("solution -> " + new C30L42576_Player().solution(participant, completion));
    }

    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Stack<String>> participantsMap = new HashMap<>();
        for(String pname : participant) {
            if(participantsMap.get(pname) == null) {
                Stack<String> stack = new Stack<>();
                stack.push(pname);
                participantsMap.put(pname, stack);
            } else {
                participantsMap.get(pname).push(pname);
            }
        }

        for(String cname : completion) {
            participantsMap.get(cname).pop();
            if(participantsMap.get(cname).size()==0)
                participantsMap.remove(cname);

        }

        System.out.println(participantsMap.size());

        return participantsMap.get(participantsMap.keySet().iterator().next()).pop();
    }
}
