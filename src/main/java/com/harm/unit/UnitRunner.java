package com.harm.unit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;

public class UnitRunner {
    private static Logger logger = LoggerFactory.getLogger(UnitRunner.class);

    public static Object start(Object obj, Object[] objs) {
        if(obj instanceof DefaultUnitHandler) {
            return UnitRunner.start((DefaultUnitHandler)obj);
        } else if(obj instanceof Unit){
            return UnitRunner.start(new DefaultUnitHandler((Unit)obj), objs);
        } else {
            UnitRunner.logger.debug("NOT SUPPORTED CLASS. CAN NOT START.");
            return null;
        }
    }

    public static Object start(DefaultUnitHandler duh, Object[] objects) {
        Unit proxyUnit = (Unit) Proxy.newProxyInstance(duh.getClass().getClassLoader(), new Class[] {Unit.class}, duh);
        Object result = null;
        try {
            result = proxyUnit.execute(objects);
        } catch (Exception e) {
            UnitRunner.logger.error("NOT SUPPORTED ERROR HANDLE.");
        }
        return result;
    }//END OF FUNCTION

    public static Object start(DefaultUnitHandler duh) {
        return UnitRunner.start(duh, null);
    }//END OF FUNCTION

    public static Object start(Unit unit) {
        return UnitRunner.start(new DefaultUnitHandler(unit), null);
    }
    public static Object start(Unit unit, Object[] objects) {
        return UnitRunner.start(new DefaultUnitHandler(unit), objects);
    }
}
