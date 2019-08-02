package com.harm.unit.recruit.kakao.elevatorgame;

import com.harm.unit.recruit.kakao.elevatorgame.common.Commands;
import com.harm.unit.recruit.kakao.elevatorgame.common.Elevator;
import com.harm.unit.recruit.kakao.elevatorgame.common.ElevatorGameServerResponse;

import static com.harm.unit.recruit.kakao.elevatorgame.ElevatorGameStudy001.PROBLEMS;
import static com.harm.unit.recruit.kakao.elevatorgame.common.Command.CODE;

public interface ElevatorStrategy {
    public Commands getCommands(PROBLEMS problems, ElevatorGameServerResponse elevatorGameServerResponse);
    default CODE getDefaultElevatorBehavior(Elevator elevator) {
        return CODE.STOP;
    }
}
