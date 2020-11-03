package com.harm.unit.lang.instance;

import com.harm.unit.Unit;

public class InitializeStudy001 implements Unit {
    @Override
    public Object execute(Object[] obj) throws Exception {
        return null;
    }

    public static void main(String[] args) {
        AbstractClass ac = new ConcreteClass();
        ac.doSomething();
    }
}
