package com.harm.unit.lang.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyTargetImpl implements ProxyTargetInf {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public String targetAction1() throws Exception {
		logger.debug("PROXY TARGET START1");
		try {
			logger.debug("PROXY TARGET DO SOMETHING1");
			return "TARGET_RETURN_STRING1";
		} catch (Exception e) {
			throw e;
		} finally {
			logger.debug("PROXY TARGET END1");
		}
	}//END OF FUNCTION
	
	public String targetAction2() throws Exception {
		logger.debug("PROXY TARGET START2");
		try {
			logger.debug("PROXY TARGET DO SOMETHING2");
			return "TARGET_RETURN_STRING2";
		} catch (Exception e) {
			throw e;
		} finally {
			logger.debug("PROXY TARGET END2");
		}
	}//END OF FUNCTION
}//END OF CLASS