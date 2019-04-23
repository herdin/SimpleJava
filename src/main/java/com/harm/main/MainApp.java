package com.harm.main;

import com.harm.unit.DefaultUnitHandler;
import com.harm.unit.google.guava.cache.CacheStudy001;
import com.harm.unit.io.HttpUrlConnectionStudy;
import com.harm.unit.netty.NettyServerStudy;
import com.harm.unit.proxy.ProxyStudy001;
import com.harm.unit.xml.jaxb.JaxBStudy001;
import com.harm.unit.xml.saxbuilder.SAXBuilerStudy001;
public class MainApp {
	public static void main(String[] args) {
//		DefaultUnitHandler.start(new DefaultUnitHandler(new CacheStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new ProxyStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new NettyServerStudy()));
//		DefaultUnitHandler.start(new String[] {"http://dailytravel.co.kr/rss"}, new DefaultUnitHandler(new HttpUrlConnectionStudy()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new SAXBuilerStudy001()));
		DefaultUnitHandler.start(new DefaultUnitHandler(new JaxBStudy001()));
	}//END OF FUNCTIION
}//END OF CLASS
