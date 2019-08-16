package com.harm.unit.pattern.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pants {
    private Logger logger = LoggerFactory.getLogger(Pants.class);
    public int lower(int sequence) throws Exception {
        if(sequence == 1) {
            this.logger.debug("LOWER PANTS");
        } else {
            throw new Exception("NO PROPER SEQUENCE");
        }
        return ++sequence;
    }
}
