package com.harm.unit.algorithm.leetcode.medium;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  */
public class Problems1396DesignUndergroundSystem {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Problems1396DesignUndergroundSystem.class);
        class CommandInfo {
            final String command;
            final int id;
            final String stationName;
            final int time;
            final String fromStationName;
            final String toStationName;

            public CommandInfo(String command, int id, String stationName, int time, String fromStationName, String toStationName) {
                this.command = command;
                this.id = id;
                this.stationName = stationName;
                this.time = time;
                this.fromStationName = fromStationName;
                this.toStationName = toStationName;
            }

            @Override
            public String toString() {
                return "CommandInfo{" +
                        "command='" + command + '\'' +
                        ", id=" + id +
                        ", stationName='" + stationName + '\'' +
                        ", time=" + time +
                        ", fromStationName='" + fromStationName + '\'' +
                        ", toStationName='" + toStationName + '\'' +
                        '}';
            }
        }
        class Data {
            final List<CommandInfo> commandInfoList;
            final double[] expects;

            public Data(List<CommandInfo> commandInfoList, double[] expects) {
                this.commandInfoList = commandInfoList;
                this.expects = expects;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "commandInfoList=" + commandInfoList +
                        ", expects=" + Arrays.toString(expects) +
                        '}';
            }
        }
        List<Data> dataList = new ArrayList<>();
        List<CommandInfo> commandInfoList = List.of(
                new CommandInfo("checkIn", 45,"Leyton",3, null, null),
                new CommandInfo("checkIn", 32,"Paradise",8, null, null),
                new CommandInfo("checkIn", 27,"Leyton",10, null, null),
                new CommandInfo("checkOut", 45,"Waterloo",15, null, null),
                new CommandInfo("checkOut", 27,"Waterloo",20, null, null),
                new CommandInfo("checkOut", 32,"Cambridge",22, null, null),
                new CommandInfo("getAverageTime", 0, null, 0,  "Paradise","Cambridge"),
                new CommandInfo("getAverageTime", 0, null, 0, "Leyton","Waterloo"),
                new CommandInfo("checkIn", 10,"Leyton",24, null, null),
                new CommandInfo("getAverageTime", 0, null, 0,"Leyton","Waterloo"),
                new CommandInfo("checkOut", 10,"Waterloo",38, null, null),
                new CommandInfo("getAverageTime", 0, null, 0,"Leyton","Waterloo")
                );
        double[] expects = new double[]{-1.0,-1,-1,-1,-1,-1,-1,14.00000,11.00000,-1,11.00000,-1,12.00000};
        dataList.add(new Data(commandInfoList, expects));
        for(Data data : dataList) {
            UndergroundSystem undergroundSystem = new UndergroundSystem();
            List<Double> results = new ArrayList<>();
            results.add(null);
            for(CommandInfo commandInfo : data.commandInfoList) {
                switch (commandInfo.command) {
                    case "checkIn":
                        undergroundSystem.checkIn(commandInfo.id, commandInfo.stationName, commandInfo.time);
                        results.add(null);
                        break;
                    case "checkOut":
                        undergroundSystem.checkOut(commandInfo.id, commandInfo.stationName, commandInfo.time);
                        results.add(null);
                        break;
                    case "getAverageTime":
                        results.add(undergroundSystem.getAverageTime(commandInfo.fromStationName, commandInfo.toStationName));
                        break;
                    default:
                        throw new IllegalArgumentException("not applicable command -> " + commandInfo.command);
                }
            }
            logger.debug("data {}", data);
            logger.debug("solution {}", results);
        }

    }
    static class UndergroundSystem {
        class CheckIn {
            int id;
            String stationName;
            int t;

            public CheckIn(int id, String stationName, int t) {
                this.id = id;
                this.stationName = stationName;
                this.t = t;
            }
        }
        Map<Integer, CheckIn> checkInMap = new HashMap<>();
        class AverageTime {
            String fromStationName;
            String toStationName;
            int count;
            int totalT;

            public AverageTime(String fromStationName, String toStationName) {
                this.fromStationName = fromStationName;
                this.toStationName = toStationName;
            }
        }
        Map<String, AverageTime> averageTimeMap = new HashMap<>();
        public UndergroundSystem() { }

        public void checkIn(int id, String stationName, int t) {
            checkInMap.put(id, new CheckIn(id, stationName, t));
        }

        public void checkOut(int id, String stationName, int t) {
            if(!checkInMap.containsKey(id)) return;

            CheckIn checkIn = checkInMap.get(id);
            String averageTimeMapKey = checkIn.stationName + "-" + stationName;
            if(!averageTimeMap.containsKey(averageTimeMapKey)) {
                averageTimeMap.put(averageTimeMapKey, new AverageTime(checkIn.stationName, stationName));
            }
            AverageTime averageTime = averageTimeMap.get(averageTimeMapKey);
            averageTime.count++;
            averageTime.totalT += t-checkIn.t;
        }

        public double getAverageTime(String startStation, String endStation) {
            String averageTimeMapKey = startStation + "-" + endStation;
            AverageTime averageTime = averageTimeMap.get(averageTimeMapKey);
            return averageTime.totalT/(double)averageTime.count;
        }
    }
}