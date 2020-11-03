package com.harm.unit.lang.autoclosable;

public class AutoclosableStudy001 {
    public static void main(String[] args) {
        try(AutoDoor autoDoor = new AutoDoor();) {
            autoDoor.doSomething();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
