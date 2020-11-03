package com.harm.unit.algorithm.recruit.kakao.elevatorgame;

import com.harm.unit.algorithm.recruit.kakao.elevatorgame.common.*;

import java.util.ArrayList;

import static com.harm.unit.algorithm.recruit.kakao.elevatorgame.common.Command.CODE;

public interface ElevatorStrategy {

    Commands getCommands(ElevatorGameServerResponse elevatorGameServerResponse);

    default CODE getDefaultElevatorBehavior(Elevator elevator) {
        return CODE.STOP;
    }

    default boolean isFirstNearFromElevator(int firstFloor, int secondFloor, Elevator elevator) {
        return Math.abs(elevator.getFloor()-firstFloor) < Math.abs(elevator.getFloor()-secondFloor);
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
                    if(this.isFirstNearFromElevator(call.getStart(), nearestCall.getStart(), elevator)) {
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
                if(this.isFirstNearFromElevator(passenger.getEnd(), nearestPassenger.getEnd(), elevator)) {
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

    default CODE getCodeByElevatorPositionWithTargetFloor(Elevator elevator, int targetFloor, CODE elevatorReachCode) {
        CODE properCode = null;
        if(targetFloor > elevator.getFloor()) {
            properCode = Command.getProperNextCode(elevator, Command.CODE.UP);
        } else if(targetFloor < elevator.getFloor()) {
            properCode = Command.getProperNextCode(elevator, Command.CODE.DOWN);
        } else {
            properCode = Command.getProperNextCode(elevator, elevatorReachCode);
        }
        return properCode;
    }
}
