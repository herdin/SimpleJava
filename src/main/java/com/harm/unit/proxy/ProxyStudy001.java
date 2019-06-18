package com.harm.unit.proxy;

import java.lang.reflect.Proxy;

import com.harm.unit.Unit;

public class ProxyStudy001 implements Unit {
	
	public void excute(Object[] objects) throws Exception {
		LoggingHandler lh = new LoggingHandler(new ProxyTargetImpl());
		ProxyTargetInf pt = (ProxyTargetInf) Proxy.newProxyInstance(lh.getClass().getClassLoader(), new Class[] { ProxyTargetInf.class}, lh);
		System.out.println(pt.targetAction1());
		System.out.println(pt.targetAction2());
	}
	
}//END OF CLASS