package com.harm.unit.recruit.kakao.elevatorgame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.harm.unit.Unit;
import com.harm.unit.recruit.kakao.elevatorgame.common.*;
import com.harm.utils.HttpUtils;
import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ElevatorGameStudy001 implements Unit {
    private Logger logger = LoggerFactory.getLogger(ElevatorGameStudy001.class);
    private final String userKey;
    private final PROBLEMS problems;
    private final ELEVATOR_COUNT elevatorCount;
    private final ElevatorStrategy elevatorStrategy;

    public ElevatorGameStudy001(String userKey, PROBLEMS problems, ElevatorStrategy elevatorStrategy, ELEVATOR_COUNT elevatorCount) {
        this.userKey = userKey;
        this.problems = problems;
        this.elevatorStrategy = elevatorStrategy;
        this.elevatorCount = elevatorCount;
    }

    public enum PROBLEMS {
        APEACH(0, 5), JAY_G(1, 25), RYAN(2, 25),
        ;
        private int problemId;
        private int topFloor;
        PROBLEMS(int problemId, int topFloor) {
            this.problemId = problemId;
            this.topFloor = topFloor;
        }
        public int getProblemId() {
            return this.problemId;
        }
        public int getTopFloor() {
            return this.topFloor;
        }
    }
    public enum ELEVATOR_COUNT {
        DUMMY, ONE, TWO, THREE, FOUR
    }
    public static class API {
        public interface COMMUNICABLE {
            ElevatorGameServerResponse communicate() throws IOException;
        }
        public static final String SERVER = "http://gcp.anmani.link:8000";
        public static String TOKEN;

        public static class START implements COMMUNICABLE {
            private Logger logger = LoggerFactory.getLogger(ElevatorGameStudy001.API.START.class);
            private final String prefix = "/start";
            private final HttpMethod httpMethod = HttpMethod.POST;
            private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            private String userKey;
            private int problemId;
            private int numberOfElevators;

            public START(String userKey, int problemId, int numberOfElevators) {
                this.userKey = userKey;
                this.problemId = problemId;
                this.numberOfElevators = numberOfElevators;
            }

            @Override
            public ElevatorGameServerResponse communicate() throws IOException {
                String requestUrl = API.SERVER + this.prefix + "/" + this.userKey + "/" + this.problemId + "/" + this.numberOfElevators;
                String res = HttpUtils.sendAndRecv(requestUrl, this.httpMethod, null);
                ElevatorGameServerResponse resObj = this.mapper.readValue(res, ElevatorGameServerResponse.class);
                this.logger.trace("recv json = {}", this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resObj));
                API.TOKEN = resObj.getToken();
                return resObj;
            }
        }
        public static class ONCALLS implements COMMUNICABLE {
            private Logger logger = LoggerFactory.getLogger(ElevatorGameStudy001.API.ONCALLS.class);
            private final String prefix = "/oncalls";
            private final HttpMethod httpMethod = HttpMethod.GET;
            private final String[] headerKeys = new String[]{"X-Auth-Token"};
            private final String[] headerValues;
            private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            public ONCALLS() {
                this.headerValues = new String[]{API.TOKEN};
            }
            public ElevatorGameServerResponse communicate() throws IOException {
                String requestUrl = API.SERVER + this.prefix;
                String res = HttpUtils.sendAndRecv(requestUrl, this.httpMethod, this.headerKeys, this.headerValues, null);
                ElevatorGameServerResponse resObj = mapper.readValue(res, ElevatorGameServerResponse.class);
                this.logger.trace("recv json = {}", this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resObj));
                return resObj;
            }
        }
        public static class ACTION implements COMMUNICABLE {
            private Logger logger = LoggerFactory.getLogger(ElevatorGameStudy001.API.ACTION.class);
            private final String prefix = "/action";
            private final HttpMethod httpMethod = HttpMethod.POST;
            public final String[] headerKeys = new String[]{"X-Auth-Token", "Content-Type"};
            private final String[] headerValues;
            private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            private Commands action;
            public ACTION(Commands action) {
                this.headerValues = new String[]{API.TOKEN, "application/json"};
                this.action = action;
            }
            public ElevatorGameServerResponse communicate() throws IOException {
                String requestUrl = API.SERVER + this.prefix;
                String res = HttpUtils.sendAndRecv(requestUrl, this.httpMethod, this.headerKeys, this.headerValues, this.mapper.writeValueAsString(this.action));
                ElevatorGameServerResponse resObj = mapper.readValue(res, ElevatorGameServerResponse.class);
                this.logger.trace("recv json = {}", this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(resObj));
                return resObj;
            }
        }
    }

    @Override
    public Object execute(Object[] obj) throws Exception {

        try {

            ElevatorGameServerResponse resObj = null;

            API.START start = new API.START(this.userKey, this.problems.getProblemId(), this.elevatorCount.ordinal());
            resObj = start.communicate();

            API.ONCALLS oncalls = new API.ONCALLS();

            while(!resObj.isIs_end()) {

                resObj = oncalls.communicate();
                Elevator[] elevators = resObj.getElevators();

                for(Elevator elevator : elevators) {
                    this.logger.debug("TIMESTAMP : {} : ELEVATOR {} : {} F : {}", resObj.getTimestamp(), elevator.getId(), elevator.getFloor(), elevator.getStatus().toString());
                }

                for(Call call : resObj.getCalls()) {
                    this.logger.debug("TIMESTAMP : {} : CALL {} : {}, {} >> {}", resObj.getTimestamp(), call.getId(), call.getTimestamp(), call.getStart(), call.getEnd());
                }

                Commands commands = this.elevatorStrategy.getCommands(resObj);
                API.ACTION action = new API.ACTION(commands);
                resObj = action.communicate();

                Thread.sleep(5L);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return true; //always true
    }


}
