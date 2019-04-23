package com.harm.main;

import com.harm.unit.DefaultUnitHandler;
import com.harm.unit.google.guava.cache.CacheStudy001;
import com.harm.unit.netty.NettyServerStudy;
import com.harm.unit.proxy.ProxyStudy001;
public class MainApp {
	public static void main(String[] args) {
//		DefaultUnitHandler.start(new DefaultUnitHandler(new CacheStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new ProxyStudy001()));
		DefaultUnitHandler.start(new DefaultUnitHandler(new NettyServerStudy()));
//		new ProxyStudy001().start();
//		new CacheStudy001().start();
	}//END OF FUNCTIION
}//END OF CLASS