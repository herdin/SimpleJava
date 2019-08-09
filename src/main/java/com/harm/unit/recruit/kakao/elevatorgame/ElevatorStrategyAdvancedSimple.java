package com.harm.unit.recruit.kakao.elevatorgame;

import com.harm.unit.recruit.kakao.elevatorgame.common.*;

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
                if(elevator.isEmpty()) {
                    nearestCallList = this.getNearestCallListFromElevatorCurrentFloor(elevator, calls);
                    if(nearestCallList.size() > 0) {
                        if(nearestCallList.get(0).getStart() > elevator.getFloor()) {
                            code = Command.getProperNextCode(elevator, Command.CODE.UP);
                        } else if(nearestCallList.get(0).getStart() < elevator.getFloor()) {
                            code = Command.getProperNextCode(elevator, Command.CODE.DOWN);
                        } else {
                            code = Command.getProperNextCode(elevator, Command.CODE.ENTER);
                            while(nearestCallList.size() > elevator.capablePassengerCount()) {
                                nearestCallList.remove(nearestCallList.size()-1);
                            }
                        }
                    }
                } else {
                    nearestPassengerList = this.getNearestCallListFromElevatorPassenger(elevator);
                    nearestCallList = this.getNearestCallListFromElevatorCurrentFloor(elevator, calls);

                    if(elevator.isFull() || nearestCallList.size() == 0) {
                        //elevator is full or nearest call list is empty
                        if(nearestPassengerList.get(0).getEnd() > elevator.getFloor()) {
                            code = Command.getProperNextCode(elevator, Command.CODE.UP);
                        } else if(nearestPassengerList.get(0).getEnd() < elevator.getFloor()) {
                            code = Command.getProperNextCode(elevator, Command.CODE.DOWN);
                        } else {
                            code = Command.getProperNextCode(elevator, Command.CODE.EXIT);
                            nearestCallList = nearestPassengerList;
                        }
                    } else if(nearestCallList.size() > 0){
                        if(this.isFirstFloorNearFromElevator(nearestPassengerList.get(0).getEnd(), nearestCallList.get(0).getEnd(), elevator)) {
                            if(nearestPassengerList.get(0).getEnd() > elevator.getFloor()) {
                                code = Command.getProperNextCode(elevator, Command.CODE.UP);
                            } else if(nearestPassengerList.get(0).getEnd() < elevator.getFloor()) {
                                code = Command.getProperNextCode(elevator, Command.CODE.DOWN);
                            } else {
                                code = Command.getProperNextCode(elevator, Command.CODE.EXIT);
                                nearestCallList = nearestPassengerList;
                            }
                        } else {
                            if(nearestCallList.get(0).getStart() > elevator.getFloor()) {
                                code = Command.getProperNextCode(elevator, Command.CODE.UP);
                            } else if(nearestCallList.get(0).getStart() < elevator.getFloor()) {
                                code = Command.getProperNextCode(elevator, Command.CODE.DOWN);
                            } else {
                                code = Command.getProperNextCode(elevator, Command.CODE.ENTER);
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
