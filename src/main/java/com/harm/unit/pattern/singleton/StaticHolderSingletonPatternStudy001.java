package com.harm.unit.pattern.singleton;

import com.harm.unit.Unit;
import com.harm.unit.UnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*Singleton pattern 을 구현하는 여러가지 중 2가지 방법*/
public class StaticHolderSingletonPatternStudy001 implements Unit {
    private Logger logger = LoggerFactory.getLogger(StaticHolderSingletonPatternStudy001.class);
    public static void main(String[] args) throws Exception {
        UnitRunner.start(new StaticHolderSingletonPatternStudy001());
    }
    @Override
    public Object execute(Object[] obj) throws Exception {
        /*static holder 를 사용하는 방식은 클래스를 로드해도 객체가 초기화되지 않는다.*/
        logger.debug("load {}", StaticHolderSingletonResource.class.getSimpleName());
        Class.forName("com.harm.unit.pattern.singleton.StaticHolderSingletonPatternStudy001$StaticHolderSingletonResource");
        /*이른 초기화 방식은 클래스를 로드할 때, 내부 static 변수의 객체가 초기화 된다.*/
        logger.debug("load {}", EagerInitializationSingletonResource.class.getSimpleName());
        Class.forName("com.harm.unit.pattern.singleton.StaticHolderSingletonPatternStudy001$EagerInitializationSingletonResource");
        logger.debug("sleep 1 sec");
        Thread.sleep(1000L);
        logger.debug("singleton resource get count -> {}", StaticHolderSingletonResource.getInstance().getCount());
        logger.debug("singleton resource get count -> {}", StaticHolderSingletonResource.getInstance().getCount());
        logger.debug("singleton resource get count -> {}", EagerInitializationSingletonResource.getInstance().getCount());
        logger.debug("singleton resource get count -> {}", EagerInitializationSingletonResource.getInstance().getCount());
        return null;
    }

    /* 1. static holder 를 사용하는 방법*/
    public static class StaticHolderSingletonResource {
        private static Logger logger = LoggerFactory.getLogger(StaticHolderSingletonResource.class);
        private int count = -1;
        private StaticHolderSingletonResource() {
            logger.debug("called");
            count = 0;
        }
        public static StaticHolderSingletonResource getInstance() {
            logger.debug("called");
            return SingletonHelper.INSTANCE;
        }
        public int getCount() { return count; }
        private static class SingletonHelper {
            private static final StaticHolderSingletonResource INSTANCE = new StaticHolderSingletonResource();
        }
    }
    /*2. eager initialization - 이른 초기화를 사용하는 방법*/
    public static class EagerInitializationSingletonResource {
        private static Logger logger = LoggerFactory.getLogger(EagerInitializationSingletonResource.class);
        private static final EagerInitializationSingletonResource eagerInitializationSingletonResource = new EagerInitializationSingletonResource();
        private int count = -1;
        private EagerInitializationSingletonResource() {
            logger.debug("called");
            count = 0;
        }
        public static EagerInitializationSingletonResource getInstance() {
            logger.debug("called");
            return eagerInitializationSingletonResource;
        }
        public int getCount() { return count; }
    }
}

