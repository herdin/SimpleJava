package com.harm.unit.lang.parallel.sync;

import com.harm.unit.Unit;
import com.harm.unit.lang.parallel.sync.SyncTarget.PARAMS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static com.harm.unit.lang.parallel.sync.SyncTarget.PARAMS.CLASS;

public class SyncStudy001 implements Unit {
    private Logger logger = LoggerFactory.getLogger(SyncStudy001.class);


    @Override
    public Object execute(Object[] obj) throws Exception {
        final long THREAD_CNT = (Long)obj[PARAMS.THREAD_CNT.ordinal()];
        final long PROCESS_CNT = (Long)obj[PARAMS.PROCESS_CNT.ordinal()];
        @SuppressWarnings("unchecked")
        Class<SyncTarget> SYNC_TARGET = (Class<SyncTarget>)obj[CLASS.ordinal()];
        final Boolean IS_PROTOTYPE = (Boolean)obj[PARAMS.IS_PROTOTYPE.ordinal()];
        final Boolean INSTANCE_FLAG = (Boolean)obj[PARAMS.CALL_NORMAL_METHOD.ordinal()];
        Constructor<SyncTarget> constructor = SYNC_TARGET.getConstructor(Boolean.class);
        final SyncTarget defaultInstance = constructor.newInstance(INSTANCE_FLAG);

        ArrayList<Thread> threads = new ArrayList<>();
        for(int i=0; i<THREAD_CNT; i++) {
            threads.add(new Thread(() -> {
                for(int i1 = 0; i1 <PROCESS_CNT; i1++) {
                    if(!IS_PROTOTYPE) {
                        defaultInstance.manipulate();
                    } else {
                        try {
                            constructor.newInstance(INSTANCE_FLAG).manipulate();
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
        for(int i=0; i<THREAD_CNT; i++) {
            threads.get(i).start();
        }
        for(int i=0; i<THREAD_CNT; i++) {
            threads.get(i).join();
        }

        return defaultInstance.getData();
    }
}
