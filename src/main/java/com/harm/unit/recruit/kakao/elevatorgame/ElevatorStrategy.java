package com.harm.unit.recruit.kakao.elevatorgame;

import com.harm.unit.recruit.kakao.elevatorgame.common.Call;
import com.harm.unit.recruit.kakao.elevatorgame.common.Commands;
import com.harm.unit.recruit.kakao.elevatorgame.common.Elevator;
import com.harm.unit.recruit.kakao.elevatorgame.common.ElevatorGameServerResponse;

import java.util.ArrayList;

import static com.harm.unit.recruit.kakao.elevatorgame.common.Command.CODE;

public interface ElevatorStrategy {
    Commands getCommands(ElevatorGameServerResponse elevatorGameServerResponse);
    default CODE getDefaultElevatorBehavior(Elevator elevator) {
        return CODE.STOP;
    }
    //TODO 방향을 구하기위해서 call 하나만 구하고 나중에 나머지를 구해도 될것 같은데
    default ArrayList<Call> getNearestCallListFromElevatorCurrentFloor(Elevator elevator, Call[] calls) {
        ArrayList<Call> nearestCallList = new ArrayList<>();

        Call nearestCall = null;
        for(Call call : calls) {
            if(!call.isChecked()) {
                if(nearestCall == null) {
                    nearestCall = call;
                } else {
                    int diffOld = Math.abs(elevator.getFloor()-nearestCall.getStart());
                    int diffNew = Math.abs(elevator.getFloor()-call.getStart());
                    if(diffNew < diffOld) {
                        nearestCall = call;
                    }
                }

            }
        }

        for(Call call : calls) {
            if (!call.isChecked()) {
                if(nearestCall.getStart() == call.getStart()) {
                    nearestCallList.add(call);
                }
            }
        }
        return nearestCallList;
    }

    //TODO 방향을 구하기위해서 call 하나만 구하고 나중에 나머지를 구해도 될것 같은데
    default ArrayList<Call> getNearestCallListFromElevatorPassenger(Elevator elevator) {
        ArrayList<Call> nearestPassengerList = new ArrayList<>();
        Call[] passengers = elevator.getPassengers();

        Call nearestPassenger = null;
        for(Call passenger : passengers) {
            if(nearestPassenger == null) {
                nearestPassenger = passenger;
            } else {
                int diffOld = Math.abs(elevator.getFloor()-nearestPassenger.getEnd());
                int diffNew = Math.abs(elevator.getFloor()-passenger.getEnd());
                if(diffNew < diffOld) {
                    nearestPassenger = passenger;
                }
            }
        }

        for(Call passenger : passengers) {
            if(nearestPassenger.getEnd() == passenger.getEnd()) {
                nearestPassengerList.add(passenger);
            }
        }
        return nearestPassengerList;
    }
}
