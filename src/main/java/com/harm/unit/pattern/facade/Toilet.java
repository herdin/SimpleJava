package com.harm.unit.pattern.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Toilet {
    private Logger logger = LoggerFactory.getLogger(Toilet.class);
    public int openCover(int sequence) throws Exception {
        if(sequence == 0) {
            this.logger.debug("OPEN COVER");
        } else {
            throw new Exception("NO PROPER SEQUENCE");
        }
        return ++sequence;
    }
}
