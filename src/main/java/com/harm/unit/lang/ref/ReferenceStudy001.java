package com.harm.unit.lang.ref;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class ReferenceStudy001 implements Unit {
    private static Logger logger = LoggerFactory.getLogger(ReferenceStudy001.class);

    public static void main(String[] args) throws Exception {
        new ReferenceStudy001().execute(null);
    }
    public static void collect() throws InterruptedException {
        logger.debug("collect() gc");
        System.gc();
        logger.debug("collect() sleep");
        TimeUnit.SECONDS.sleep(1);
    }
    @Override
    public Object execute(Object[] obj) throws Exception {

        ReferenceQueue<ReferenceTarget> rq = new ReferenceQueue<>();

        String tag = "strong";
        logger.debug("{} reference create.", tag);
        ReferenceTarget strong = new ReferenceTarget("strong");
        logger.debug("{} before check {}", tag, (strong == null)? "null":strong.hashCode());
        ReferenceStudy001.collect();
        logger.debug("{} after check {}", tag, (strong == null)? "null":strong.hashCode());
        logger.debug("{} reference remove.", tag);
        strong = null;
        ReferenceStudy001.collect();

        tag = "soft";
        logger.debug("{} reference create.", tag);
        SoftReference<ReferenceTarget> soft = new SoftReference<>(new ReferenceTarget(tag), rq);
        logger.debug("{} before check {}", tag, (soft.get() == null)? "null":soft.get().hashCode());
        ReferenceStudy001.collect();
        logger.debug("{} after check {}", tag, (soft.get() == null)? "null":soft.get().hashCode());
        Object enqueueObject = rq.poll();
        logger.debug("enqueue to ReferenceQueue ? -> {}, equal? -> {}", enqueueObject, soft.equals(enqueueObject));
        soft.clear();
        ReferenceStudy001.collect();

        tag = "weak";
        logger.debug("{} reference create.", tag);
        ReferenceTarget rt = new ReferenceTarget(tag);
        WeakReference<ReferenceTarget> weak = new WeakReference<>(rt, rq);
        logger.debug("{} before check {}", tag, (weak.get() == null)? "null":weak.get().hashCode());

        ReferenceStudy001.collect();
        logger.debug("{} after check {}", tag, (weak.get() == null)? "null":weak.get().hashCode());
        enqueueObject = rq.poll();
        logger.debug("enqueue to ReferenceQueue ? -> {}, equal? -> {}", enqueueObject, weak.equals(enqueueObject));
        weak.clear();

        ReferenceStudy001.collect();

        tag = "phantom";
        logger.debug("{} reference create.", tag);
        PhantomReference<ReferenceTarget> phantom = new PhantomReference<>(new ReferenceTarget(tag), rq);
        logger.debug("{} before check {}", tag, (phantom.get() == null)? "null":phantom.get().hashCode());
        ReferenceStudy001.collect();
        logger.debug("{} after check {}", tag, (phantom.get() == null)? "null":phantom.get().hashCode());
        enqueueObject = rq.poll();
        logger.debug("enqueue to ReferenceQueue ? -> {}, equal? -> {}", enqueueObject, phantom.equals(enqueueObject));
        weak.clear();
        ReferenceStudy001.collect();


        return null;
    }
}
