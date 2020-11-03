package com.harm.unit.lang.proxy;

import java.lang.reflect.Proxy;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyStudy001 implements Unit {
	private Logger logger = LoggerFactory.getLogger(ProxyStudy001.class);
	
	public Object execute(Object[] objects) throws Exception {
		LoggingHandler lh = new LoggingHandler(new ProxyTargetImpl());
		ProxyTargetInf pt = (ProxyTargetInf) Proxy.newProxyInstance(lh.getClass().getClassLoader(), new Class[] { ProxyTargetInf.class}, lh);
		this.logger.debug(pt.targetAction1());
		this.logger.debug(pt.targetAction2());
		return null;
	}
	
}//END OF CLASS