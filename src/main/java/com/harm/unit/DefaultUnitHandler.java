package com.harm.unit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultUnitHandler implements InvocationHandler {
	private Logger logger = LoggerFactory.getLogger(DefaultUnitHandler.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private long startTimeLong = -1L;
	private Unit unit = null;
	
	public DefaultUnitHandler(Unit unit) {
		this.unit = unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			this.beforeExcute();
			return method.invoke(this.unit, args);
		} catch (Exception e) {
			this.handleException(e);
			throw e;
		} finally {
			this.afterExcute();
		}
	}//END OF FUNCTION
	
	private void beforeExcute() {
		this.startTimeLong = System.currentTimeMillis();
		this.logger.debug("----------------------------------------");
		this.logger.debug("| START TIME : {}", sdf.format(new Date()));
		this.logger.debug("| {} STARTED", this.unit.getClass().getSimpleName());
		this.logger.debug("........................................");
	}//END OF FUNCTION
	
	private void handleException(Exception e) {
		this.logger.debug("........................................");
		this.logger.debug("| EXCEPTION HANDLE");
		for(StackTraceElement ste : e.getStackTrace()) {
			this.logger.debug("| {}", ste.toString());
		}
		this.logger.debug("........................................");
	}//END OF FUNCTION
	
	private void afterExcute() {
		this.logger.debug("........................................");
		this.logger.debug("| END TIME : {}", sdf.format(new Date()));
		this.logger.debug("| RUN TIME : {} SEC", (System.currentTimeMillis()-this.startTimeLong)/1000L);
		this.logger.debug("| {} END ", this.unit.getClass().getSimpleName());
		this.logger.debug("----------------------------------------");
	}//END OF FUNCTION
	
}//END OF CLASS