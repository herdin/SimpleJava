package com.harm.unit.recruit.kakao.elevatorgame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harm.unit.Unit;
import com.harm.utils.HttpUtils;
import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElevatorGame implements Unit {
    private Logger logger = LoggerFactory.getLogger(ElevatorGame.class);
    public static class API {
        public static final String SERVER = "http://gcp.anmani.link:8000";
        public static final String USER_KEY = "tester";
        public static class START {
            private final String prefix = "/start";
            public final HttpMethod HTTP_METHOD = HttpMethod.POST;
            private String userKey;
            private String problemId;
            private String numberOfElevators;
            public START(String userKey, String problemId, String numberOfElevators) {
                this.userKey = userKey;
                this.problemId = problemId;
                this.numberOfElevators = numberOfElevators;
            }
            @Override
            public String toString() {
                return this.prefix + "/" + this.userKey + "/" + this.problemId + "/" + this.numberOfElevators;
            }
        }
        public static class ONCALLS {
            private final String prefix = "/oncalls";
            public final HttpMethod HTTP_METHOD = HttpMethod.GET;
            public final String[] header = new String[]{"X-Auth-Token"};

            @Override
            public String toString() {
                return this.prefix;
            }
        }
    }

    @Override
    public Object execute(Object[] obj) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();

            API.START start = new API.START(API.USER_KEY, "0", "3");
            String res = HttpUtils.sendAndRecv(API.SERVER + start.toString(), start.HTTP_METHOD, null);

            ElevatorGameServerResponse resObj = mapper.readValue(res, ElevatorGameServerResponse.class);
            this.logger.debug("RESPONSE TOKEN : " + resObj.getToken());

            String recvToken = resObj.getToken();

            API.ONCALLS oncalls = new API.ONCALLS();
            res = HttpUtils.sendAndRecv(API.SERVER + oncalls.toString(), oncalls.HTTP_METHOD, oncalls.header, new String[]{recvToken}, null);

            resObj = mapper.readValue(res, ElevatorGameServerResponse.class);

            for(Elevator elevators : resObj.getElevators()) {
                this.logger.debug("ELEVATOR : {} : {}", elevators.getId(),   elevators.getStatus().toString());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }


        return true; //always true
    }
}
