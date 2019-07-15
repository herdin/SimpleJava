package com.harm.unit.xml;

import com.harm.unit.DefaultUnitHandler;
import com.harm.unit.Unit;

import java.lang.reflect.Proxy;

public class UnitRunner {
    public static Object start(DefaultUnitHandler duh) {
        return UnitRunner.start(duh, null);
    }//END OF FUNCTION

    public static Object start(DefaultUnitHandler duh, Object[] objects) {
        Unit proxyUnit = (Unit) Proxy.newProxyInstance(duh.getClass().getClassLoader(), new Class[] {Unit.class}, duh);
        Object result = null;
        try {
            result = proxyUnit.execute(objects);
        } catch (Exception e) {
            DefaultUnitHandler.logger.error(e.getMessage());
        }
        return result;
    }//END OF FUNCTION
}
