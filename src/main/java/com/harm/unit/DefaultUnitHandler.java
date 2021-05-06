package com.harm.unit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultUnitHandler implements InvocationHandler {
	private Logger logger = LoggerFactory.getLogger(DefaultUnitHandler.class);
	private Stopwatch stopwatch;
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
			return null;
		} finally {
			this.afterExcute();
		}
	}//END OF FUNCTION

	private void beforeExcute() {
		stopwatch = Stopwatch.createStarted();
		this.logger.debug("----------------------------------------");
		this.logger.debug("| START TIME : {}", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		this.logger.debug("| {} STARTED", stopwatch.toString());
		this.logger.debug("|");
	}//END OF FUNCTION

	private void handleException(Exception e) {
		this.logger.debug("|");
		this.logger.debug("| EXCEPTION HANDLE");
		if(e instanceof InvocationTargetException) {
			Throwable t = ((InvocationTargetException)e).getTargetException();
			this.logger.error("| EXCEPTION MESSAGE {}", t.getMessage());
			for(StackTraceElement ste : t.getStackTrace()) {
				this.logger.error("| {}", ste.toString());
			}
		} else {
			this.logger.error("CAN'T HANDLE EXCEPTION.");
		}
		this.logger.debug("|");
	}//END OF FUNCTION

	private void afterExcute() {
		this.logger.debug("|");
		this.logger.debug("| END TIME : {}", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		this.logger.debug("| RUN TIME : {}", stopwatch.stop().toString());
		this.logger.debug("| {} END ", this.unit.getClass().getSimpleName());
		this.logger.debug("----------------------------------------");
	}//END OF FUNCTION

}//END OF CLASS