package com.harm.unit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultUnitHandler implements InvocationHandler {
	public static Logger logger = LoggerFactory.getLogger(DefaultUnitHandler.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private final String div = "##############################################";
	private long startTimeLong = -1L;
	private Unit unit = null;
	
	public DefaultUnitHandler(Unit unit) {
		this.unit = unit;
	}

	public static void start(DefaultUnitHandler duh) {
		DefaultUnitHandler.start(null, duh);
	}//END OF FUNCTION
	
	public static void start(Object[] objects, DefaultUnitHandler duh) {
		Unit proxyUnit = (Unit)Proxy.newProxyInstance(duh.getClass().getClassLoader(), new Class[] {Unit.class}, duh);
		try {
			proxyUnit.excute(objects);
		} catch (Exception e) {
			DefaultUnitHandler.logger.error(e.getMessage());
		}
	}//END OF FUNCTION
	
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
		DefaultUnitHandler.logger.debug("----------------------------------------");
		DefaultUnitHandler.logger.debug("START TIME : {}", sdf.format(new Date()));
		DefaultUnitHandler.logger.debug("{} STARTED", this.unit.getClass().getSimpleName());
		DefaultUnitHandler.logger.debug("........................................");
	}//END OF FUNCTION
	
	private void handleException(Exception e) {
		DefaultUnitHandler.logger.debug("........................................");
		DefaultUnitHandler.logger.debug("EXCEPTION HANDLE : {}", e.getMessage());
		DefaultUnitHandler.logger.debug("........................................");
	}//END OF FUNCTION
	
	private void afterExcute() {
		DefaultUnitHandler.logger.debug("........................................");
		DefaultUnitHandler.logger.debug("END TIME : {}", sdf.format(new Date()));
		DefaultUnitHandler.logger.debug("RUN TIME : {} SEC", (System.currentTimeMillis()-this.startTimeLong)/1000L);
		DefaultUnitHandler.logger.debug("----------------------------------------");
	}//END OF FUNCTION
	
}//END OF CLASS