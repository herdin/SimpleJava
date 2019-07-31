package com.harm.unit.recruit.kakao.elevatorgame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harm.unit.Unit;
import com.harm.utils.HttpUtils;
import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

public class ElevatorGame implements Unit {
    private Logger logger = LoggerFactory.getLogger(ElevatorGame.class);
    public enum PROBLEMS {
        APEACH, JAY_G, RYAN
    }
    public static class API {
        public interface COMMUNICAPABLE {
            ElevatorGameServerResponse communicate() throws IOException;
        }
        public static final String SERVER = "http://gcp.anmani.link:8000";
        public static final String USER_KEY = "testerherdin";
        public static String TOKEN;

        public static class START implements COMMUNICAPABLE {
            private final String prefix = "/start";
            private final HttpMethod httpMethod = HttpMethod.POST;
            private final ObjectMapper mapper = new ObjectMapper();
            private String userKey;
            private int problemId;
            private int numberOfElevators;

            public START(String userKey, int problemId, int numberOfElevators) {
                this.userKey = userKey;
                this.problemId = problemId;
                this.numberOfElevators = numberOfElevators;
            }

            public ElevatorGameServerResponse communicate() throws IOException {
                String requestUrl = API.SERVER + this.prefix + "/" + this.userKey + "/" + this.problemId + "/" + this.numberOfElevators;
                String res = HttpUtils.sendAndRecv(requestUrl, this.httpMethod, null);
                ElevatorGameServerResponse resObj = this.mapper.readValue(res, ElevatorGameServerResponse.class);
                API.TOKEN = resObj.getToken();
                return resObj;
            }
        }
        public static class ONCALLS implements COMMUNICAPABLE {
            private final String prefix = "/oncalls";
            private final HttpMethod httpMethod = HttpMethod.GET;
            private final String[] headerKeys = new String[]{"X-Auth-Token"};
            private final String[] headerValues;
            private final ObjectMapper mapper = new ObjectMapper();
            public ONCALLS() {
                this.headerValues = new String[]{API.TOKEN};
            }
            public ElevatorGameServerResponse communicate() throws IOException {
                String requestUrl = API.SERVER + this.prefix;
                String res = HttpUtils.sendAndRecv(requestUrl, this.httpMethod, this.headerKeys, this.headerValues, null);
                return mapper.readValue(res, ElevatorGameServerResponse.class);
            }
        }
        public static class ACTION implements COMMUNICAPABLE {
            private final String prefix = "/action";
            private final HttpMethod httpMethod = HttpMethod.POST;
            public final String[] headerKeys = new String[]{"X-Auth-Token", "Content-Type"};
            private final String[] headerValues;
            private final ObjectMapper mapper = new ObjectMapper();
            private Commands action;
            public ACTION(Commands action) {
                this.headerValues = new String[]{API.TOKEN, "application/json"};
                this.action = action;
            }
            public ElevatorGameServerResponse communicate() throws IOException {
                String requestUrl = API.SERVER + this.prefix;
                String res = HttpUtils.sendAndRecv(requestUrl, this.httpMethod, this.headerKeys, this.headerValues, this.mapper.writeValueAsString(this.action));
                return mapper.readValue(res, ElevatorGameServerResponse.class);
            }
        }
    }



    @Override
    public Object execute(Object[] obj) throws Exception {
        try {

            ElevatorGameServerResponse resObj = null;
            Elevator[] prevElevator = null;

            API.START start = new API.START(API.USER_KEY, PROBLEMS.APEACH.ordinal(), 1);
            resObj = start.communicate();

            API.ONCALLS oncalls = new API.ONCALLS();

            while(!resObj.isIs_end()) {

                resObj = oncalls.communicate();
                prevElevator = resObj.getElevators();
                Elevator[] elevators = resObj.getElevators();

                for(Elevator elevator : elevators) {
                    this.logger.debug("TIMESTAMP : {} : ELEVATOR : {} : {}", resObj.getTimestamp(), elevator.getId(), elevator.getStatus().toString());
                }

                ArrayList<Command> commandList = new ArrayList<>();
                if(resObj.getCalls().length > 0) {

                    for(Call call : resObj.getCalls()) {
                        this.logger.debug("TIMESTAMP : {} : CALL : {} at {}, {} -> {}", resObj.getTimestamp(), call.getId(), call.getTimestamp(), call.getStart(), call.getEnd());

//                        Elevator nearestElevator = null;
//                        for(int i=0; i<elevators.length; i++) {
//                            if(nearestElevator == null) {
//                                nearestElevator = elevators[i];
//                            } else {
//                                if(nearestElevator.getPassengers().length < 8 && elevators[i].getPassengers().length < 8) {
//                                    if(nearestElevator.getFloor() == call.getStart()) {
//                                        break;
//                                    } else if(elevators[i].getFloor() == call.getStart()) {
//                                        nearestElevator = elevators[i];
//                                        break;
//                                    }
//                                    int nearAndCall = call.getStart() - nearestElevator.getFloor();
//                                    int nextAndCall = call.getStart() - elevators[i].getFloor();
//                                    if(Math.abs(nearAndCall) > Math.abs(nearAndCall)) {
//                                        nearestElevator = elevators[i];
//                                    }
//                                }
//                            }
//                        }
//                        if(nearestElevator.getFloor() - call.getStart() > 0) { //elevator > call
//                            Command.CODE code = null;
//                            switch (nearestElevator.getStatus()) {
//                                case OPENED: code = Command.CODE.CLOSE; break;
//                                case STOPPED: code = Command.CODE.DOWN; break;
//                                case UPWARD: code = Command.CODE.STOP; break;
//                                case DOWNWARD:
//                            }
//                            commandList.add(
//                                    new Command.Builder()
//                                    .elevatorId(nearestElevator.getId())
//                                    .command(Command.CODE.CLOSE)
//                                    .build()
//                            );
//                        }

                    }



                } else {

                }
                Commands commands = new Commands();
                commands.setCommands(new Command[]{
                        new Command.Builder()
                            .elevatorId(0)
                            .command(Command.CODE.UP)
                            .callIds(new int[]{})
                            .build()
                });
                API.ACTION action = new API.ACTION(commands);
                resObj = action.communicate();

                Thread.sleep(3000L);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return true; //always true
    }

}
