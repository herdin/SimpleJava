package com.harm.unit;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Unit {
	default void execute() throws Exception {}
	default Object execute(Object[] obj) throws Exception {
		execute();
		return null;
	};
	default void run() throws Exception {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		Stopwatch stopwatch = Stopwatch.createStarted();
		logger.debug("UNIT START, {}", stopwatch.toString());
		execute();
		logger.debug("UNIT END, {}", stopwatch.stop().toString());
	}
}//END OF INTERFACE