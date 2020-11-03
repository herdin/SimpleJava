package com.harm.unit.lang.innerclass;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InnerClassStudy001 implements Unit {
    private Logger logger = LoggerFactory.getLogger(InnerClassStudy001.class);
    @Override
    public Object execute(Object[] obj) throws Exception {
        OuterClass oc = new OuterClass("i'm outer");
        logger.debug("outer class set name -> {}", oc.getName());
//        OuterClass.InnerClass ic = new OuterClass.InnerClass("i'm inner"); //compile error
//        OuterClass.InnerClass ic = OuterClass.new InnerClass("i'm inner"); //compile error
        OuterClass.InnerClass ic = oc.new InnerClass("i'm inner");
        logger.debug("inner class set name -> {}", ic.getName());
        OuterClass.InnerStaticClass isc = new OuterClass.InnerStaticClass("i'm static inner");
        logger.debug("static inner class set name -> {}", isc.getName());
        return null;
    }
}
