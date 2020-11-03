package com.harm.unit.algorithm.recruit.kakao.elevatorgame;

import com.harm.unit.algorithm.recruit.kakao.elevatorgame.common.*;

import java.util.ArrayList;

/*
1. 끝까지 올라갔다 내려갔다 하면서 쭉 싣고 쭉 내리는 전략. 을 하려고 했는데..
2. 일단 문열고 있다가 손님이 생기면 태우고 그쪽방향으로 끝까지 가는걸로..


*/

public class ElevatorStrategyAdvancedSimple implements ElevatorStrategy {
    @Override
    public Commands getCommands(ElevatorGameServerResponse elevatorGameServerResponse) {
        ArrayList<Command> commandList = new ArrayList<>();

        Elevator[] elevators = elevatorGameServerResponse.getElevators();
        Call[] calls = elevatorGameServerResponse.getCalls();

        for(Elevator elevator : elevators) {
            Command command = new Command();
            commandList.add(command);
            command.setElevator_id(elevator.getId());
            command.setCommand(this.getDefaultElevatorBehavior(elevator));

            ArrayList<Call> nearestCallList = null;
            ArrayList<Call> nearestPassengerList = null;
            ArrayList<Integer> call_id_list = new ArrayList<>();
            Command.CODE code = Command.getProperNextCode(elevator, Command.CODE.OPEN);

            if(elevator.isEmpty() && calls.length == 0) {
                code = Command.getProperNextCode(elevator, Command.CODE.OPEN);
            } else {
                /*
                elevator is not empty || call is exists
                  - elevator is     empty, call is not empty -> enter (other can take call, so actually can be empty(all checked call))
                  - elevator is not empty, call is     empty -> exit
                  - elevator is not empty, call is not empty -> exit and enter
                */
                /* elevator is empty */
                if(elevator.isEmpty()) {
                    nearestCallList = this.getNearestCallListFromElevatorCurrentFloor(elevator, calls);
                    /* unchecked call is not empty */
                    if(nearestCallList.size() > 0) {
                        code = this.getCodeByElevatorPositionWithTargetFloor(elevator, nearestCallList.get(0).getStart(), Command.CODE.ENTER);
                        if(Command.CODE.ENTER.equals(code)) {
                            while(nearestCallList.size() > elevator.capablePassengerCount()) {
                                nearestCallList.remove(nearestCallList.size()-1);
                            }
                        }
                    }
                    /* unchecked call is empty */
                    else {
                        code = Command.getProperNextCode(elevator, Command.CODE.OPEN);
                    }
                }
                /* elevator is not empty */
                else {
                    nearestPassengerList = this.getNearestCallListFromElevatorPassenger(elevator);
                    nearestCallList = this.getNearestCallListFromElevatorCurrentFloor(elevator, calls);

                    /* elevator is full || unchecked call is empty */
                    if(elevator.isFull() || nearestCallList.size() == 0) {
                        code = this.getCodeByElevatorPositionWithTargetFloor(elevator, nearestPassengerList.get(0).getEnd(), Command.CODE.EXIT);
                        if(Command.CODE.EXIT.equals(code)) {
                            nearestCallList = nearestPassengerList;
                        }
                    }
                    /* elevator is not full && unchecked call is not empty */
                    else /*if(nearestCallList.size() > 0)*/ {
                        if(this.isFirstNearFromElevator(nearestPassengerList.get(0).getEnd(), nearestCallList.get(0).getStart(), elevator)) {
                            code = this.getCodeByElevatorPositionWithTargetFloor(elevator, nearestPassengerList.get(0).getEnd(), Command.CODE.EXIT);
                            if(Command.CODE.EXIT.equals(code)) {
                                nearestCallList = nearestPassengerList;
                            }
                        } else {
                            code = this.getCodeByElevatorPositionWithTargetFloor(elevator, nearestCallList.get(0).getStart(), Command.CODE.ENTER);
                            if(Command.CODE.ENTER.equals(code)) {
                                while(nearestCallList.size() > elevator.capablePassengerCount()) {
                                    nearestCallList.remove(nearestCallList.size()-1);
                                }
                            }
                        }
                    }
                }
            }

            switch(code) {
                case ENTER:
                    for(Call nearestCall : nearestCallList) {
                        for(Call recvCall : calls) {
                            if(nearestCall.getId() == recvCall.getId()) {
                                recvCall.checked();
                            }
                        }
                    }
                case EXIT:
                    for(Call nearestCall : nearestCallList) {
                        call_id_list.add(nearestCall.getId());
                    }
            }

            command.setCommand(code);
            command.setCall_ids(call_id_list.toArray(new Integer[call_id_list.size()]));
        }//END OF FOR-LOOP FOR ELEVATOR

        return new Commands(commandList.toArray(new Command[commandList.size()]));
    }

    @Override
    public Command.CODE getDefaultElevatorBehavior(Elevator elevator) {
        Command.CODE code = Command.CODE.STOP;
        switch(elevator.getStatus()) {
            case UPWARD:   code = Command.CODE.UP; break;
            case DOWNWARD: code = Command.CODE.DOWN; break;
            case OPENED:   code = Command.CODE.OPEN; break;
            case STOPPED:  code = Command.CODE.STOP; break;
        }
        return code;
    }
}
