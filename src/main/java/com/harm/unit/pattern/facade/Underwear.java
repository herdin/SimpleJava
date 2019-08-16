package com.harm.unit.pattern.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Underwear {
    private Logger logger = LoggerFactory.getLogger(Underwear.class);
    public int lower(int sequence) throws Exception {
        if(sequence == 2) {
            this.logger.debug("LOWER UNDERWEAR");
        } else {
            throw new Exception("NO PROPER SEQUENCE");
        }
        return ++sequence;
    }
}
