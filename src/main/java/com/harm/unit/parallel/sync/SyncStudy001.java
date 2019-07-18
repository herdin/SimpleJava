package com.harm.unit.parallel.sync;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class SyncStudy001 implements Unit {
    private Logger logger = LoggerFactory.getLogger(SyncStudy001.class);
    @Override
    public Object execute(Object[] obj) throws Exception {
        Long[] params = (Long[])obj[0];
        final long THREAD_CNT = params[0];
        final long PROCESS_CNT = params[1];
        final SyncTarget syncTarget = (SyncTarget)obj[1];

        ArrayList<Thread> threads = new ArrayList<>();
        for(int i=0; i<THREAD_CNT; i++) {
            threads.add(new Thread(() -> {
                for(int i1 = 0; i1 <PROCESS_CNT; i1++) {
                    syncTarget.manipulate();
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

        return new Long(syncTarget.getData());
    }
}
