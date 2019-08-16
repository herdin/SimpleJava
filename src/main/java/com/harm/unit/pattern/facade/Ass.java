package com.harm.unit.pattern.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ass {
    private Logger logger = LoggerFactory.getLogger(Ass.class);
    public int bigShoot(int sequence) throws Exception {
        if(sequence == 3) {
            this.logger.debug("POOOOOOOOOOOOOOOO-POOOOOOOOOOOOOOOO-");
        } else {
            throw new Exception("NO PROPER SEQUENCE");
        }
        return ++sequence;
    }
}
