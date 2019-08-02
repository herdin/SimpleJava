package com.harm.unit.recruit.kakao.elevatorgame.common;

public class Command {
    public enum CODE {
        STOP, OPEN, ENTER, EXIT, CLOSE, UP, DOWN
    }
    private int elevator_id;
    private CODE command;
    private int[] call_ids;

    public static class Builder {
        private int elevator_id;
        private CODE command;
        private int[] call_ids;
        public Builder elevatorId(int elevator_id) {
            this.elevator_id = elevator_id;
            return this;
        }
        public Builder command(CODE command) {
            this.command = command;
            return this;
        }
        public Builder callIds(int[] call_ids) {
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
//        for(CODE code : CODE.values()) {
//            if(code.name().equals(command)) {
//                this.command = code;
//                break;
//            }
//        }
    }

    public int[] getCall_ids() {
        return call_ids;
    }

    public void setCall_ids(int[] call_ids) {
        this.call_ids = call_ids;
    }
}
