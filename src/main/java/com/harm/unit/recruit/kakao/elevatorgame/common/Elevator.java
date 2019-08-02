package com.harm.unit.recruit.kakao.elevatorgame.common;

public class Elevator {
    public enum STATUS {
        STOPPED, OPENED, UPWARD, DOWNWARD,
    }
    private static final int MAX_CALL = 8;
    private int id;
    private int floor;
    private Call[] passengers;
    private STATUS status;

    public boolean isNotFull() {
        return Elevator.MAX_CALL > this.getPassengers().length;

    }
    public boolean isEmpty() {
        return this.getPassengers().length == 0;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Call[] getPassengers() {
        return passengers;
    }

    public void setPassengers(Call[] passengers) {
        this.passengers = passengers;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
//        this.status = STATUS.STOPPED;
//        for(STATUS stat: STATUS.values()) {
//            if(stat.toString().equals(status)) {
//                this.status = stat;
//                break;
//            }
//        }
    }
}
