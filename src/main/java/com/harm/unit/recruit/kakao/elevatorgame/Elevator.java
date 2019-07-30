package com.harm.unit.recruit.kakao.elevatorgame;

public class Elevator {
    public enum STATUS {
        STOPPED,
        OPENED,
        UPWARD,
        DOWNWARD,
    }
    private int id;
    private int floor;
    public Call[] passengers;
    public STATUS status;

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

    public void setStatus(String status) {
        this.status = STATUS.STOPPED;
        for(STATUS stat: STATUS.values()) {
            if(stat.toString().equals(status)) {
                this.status = stat;
                break;
            }
        }
    }
}
