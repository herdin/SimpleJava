package com.harm.unit.algorithm.recruit.kakao.elevatorgame.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Command {
    private static Logger logger = LoggerFactory.getLogger(Command.class);
    public enum CODE {
        STOP, OPEN, ENTER, EXIT, CLOSE, UP, DOWN
    }
    private int elevator_id;
    private CODE command;
    private Integer[] call_ids;

    public static CODE getProperNextCode(Elevator elevator, CODE targetCode) {
        Elevator.STATUS currentStatus = elevator.getStatus();
        CODE properCode = targetCode;
        switch(currentStatus) {
            case UPWARD:
                switch(targetCode) {
                    case UP:    properCode = CODE.UP;break;
                    case DOWN:  properCode = CODE.STOP; break;
                    case STOP:  properCode = CODE.STOP; break;
                    case OPEN:  properCode = CODE.STOP; break;
                    case ENTER: properCode = CODE.STOP; break;
                    case EXIT:  properCode = CODE.STOP; break;
                    case CLOSE: properCode = CODE.STOP; break;
                }
                break;
            case DOWNWARD:
                switch(targetCode) {
                    case UP:    properCode = CODE.STOP;break;
                    case DOWN:  properCode = CODE.DOWN; break;
                    case STOP:  properCode = CODE.STOP; break;
                    case OPEN:  properCode = CODE.STOP; break;
                    case ENTER: properCode = CODE.STOP; break;
                    case EXIT:  properCode = CODE.STOP; break;
                    case CLOSE: properCode = CODE.STOP; break;
                }
                break;
            case STOPPED:
                switch(targetCode) {
                    case UP:    properCode = CODE.UP;break;
                    case DOWN:  properCode = CODE.DOWN; break;
                    case STOP:  properCode = CODE.STOP; break;
                    case OPEN:  properCode = CODE.OPEN; break;
                    case ENTER: properCode = CODE.OPEN; break;
                    case EXIT:  properCode = CODE.OPEN; break;
                    case CLOSE: properCode = CODE.STOP; break;
                }
                break;
            case OPENED:
                switch(targetCode) {
                    case UP:    properCode = CODE.CLOSE;break;
                    case DOWN:  properCode = CODE.CLOSE; break;
                    case STOP:  properCode = CODE.CLOSE; break;
                    case OPEN:  properCode = CODE.OPEN; break;
                    case ENTER: properCode = CODE.ENTER; break;
                    case EXIT:  properCode = CODE.EXIT; break;
                    case CLOSE: properCode = CODE.CLOSE; break;
                }
                break;
        }
        Command.logger.trace("CURRENT ELEVATOR STATUS {}, TARGET CODE {}, PROPER CODE {}", currentStatus.toString(), targetCode.toString(), properCode.toString());
        return properCode;
    }

    public static class Builder {
        private int elevator_id;
        private CODE command;
        private Integer[] call_ids;
        public Builder elevatorId(int elevator_id) {
            this.elevator_id = elevator_id;
            return this;
        }
        public Builder command(CODE command) {
            this.command = command;
            return this;
        }
        public Builder callIds(Integer[] call_ids) {
            this.call_ids = call_ids;
            return this;
        }
        public Command build() {
            Command command = new Command();
            command.setElevator_id(this.elevator_id);
            command.setCommand(this.command);
            command.setCall_ids(this.call_ids);
            return command;
        }
    }

    public int getElevator_id() {
        return elevator_id;
    }

    public void setElevator_id(int elevator_id) {
        this.elevator_id = elevator_id;
    }

    public CODE getCommand() {
        return command;
    }

    public void setCommand(CODE command) {
        this.command = command;
    }

    public Integer[] getCall_ids() {
        return call_ids;
    }

    public void setCall_ids(Integer[] call_ids) {
        this.call_ids = call_ids;
    }
}
