package com.harm.unit.recruit.kakao.elevatorgame;

import com.harm.unit.recruit.kakao.elevatorgame.common.*;

import java.util.ArrayList;

/*
1. 끝까지 올라갔다 내려갔다 하면서 쭉 싣고 쭉 내리는 전략.

STOP, OPEN, ENTER, EXIT, CLOSE, UP, DOWN

if elevator empty
    if elevator.getFloor = top then DOWN

    if (prev.status = UP and elevator.getFloor != problem.getTopFloor) or (prev.status = DOWN and elevator.getFloor > 1)
        then UP, DOWN
    else
        if prev.status = STOP then if
        if prev.status = EXIT
        if prev.status = CLOSE

        if prev.status = OPEN then close
        if prev.status = ENTER then ??

else



*/

public class SimpleElevatorStrategy implements ElevatorStrategy {
    @Override
    public Commands getCommands(ElevatorGameStudy001.PROBLEMS problems, ElevatorGameServerResponse elevatorGameServerResponse) {
        ArrayList<Command> commandList = new ArrayList<>();

        Elevator[] elevators = elevatorGameServerResponse.getElevators();
        Call[] calls = elevatorGameServerResponse.getCalls();

        for(Elevator elevator : elevators) {
            Command command = new Command();
            commandList.add(command);
            command.setElevator_id(elevator.getId());
            command.setCommand(this.getDefaultElevatorBehavior(elevator));
//            command

            if(elevator.isNotFull()) {
                for(Call call : calls) {

                }
            } else {

            }
        }

//        commandList.add(new Command.Builder().elevatorId(0).command(Command.CODE.UP).callIds(new int[]{}).build());
        return new Commands(commandList.toArray(new Command[commandList.size()]));
    }

    @Override
    public Command.CODE getDefaultElevatorBehavior(Elevator elevator) {
        Command.CODE code = Command.CODE.STOP;
        switch(elevator.getId()) {
            case 0: code = Command.CODE.STOP; break;
            case 1: code = Command.CODE.UP; break;
            case 2: code = Command.CODE.DOWN; break;
            case 3: code = Command.CODE.STOP; break;
        }
        return code;
    }
}
