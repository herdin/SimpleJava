package com.harm.unit.lang.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingHandler implements InvocationHandler {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProxyTargetInf pt;
	
	public LoggingHandler(ProxyTargetInf pt) {
		this.pt = pt;
	}//END OF FUNCTIOIN
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		this.logger.debug("LOGGING HANDLER START");
		try {
			this.logger.debug("LOGGING HANDLER ENTER TRY");
			return method.invoke(this.pt, args);
		} catch (Exception e) {
			throw e;
		} finally {
			this.logger.debug("LOGGING HANDLER ENTER END");
		}
	}//END OF FUNCTIOIN
	
}//END OF CLASS