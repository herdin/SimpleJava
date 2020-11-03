package com.harm.unit.algorithm.recruit.kakao.elevatorgame.common;

public class ElevatorGameServerResponse {

    private String token;
    private int timestamp;
    private Elevator[] elevators;
    private Call[] calls;
    private boolean is_end;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public Elevator[] getElevators() {
        return elevators;
    }

    public void setElevators(Elevator[] elevators) {
        this.elevators = elevators;
    }

    public Call[] getCalls() {
        return calls;
    }

    public void setCalls(Call[] calls) {
        this.calls = calls;
    }

    public boolean isIs_end() {
        return is_end;
    }

    public void setIs_end(boolean is_end) {
        this.is_end = is_end;
    }
}
