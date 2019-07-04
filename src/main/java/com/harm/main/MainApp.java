package com.harm.main;

import com.harm.unit.DefaultUnitHandler;
import com.harm.unit.google.guava.cache.CacheStudy001;
import com.harm.unit.io.HttpUrlConnectionStudy01;

public class MainApp {
	public enum MAIL_PUR {
		HI, HELLO
	}
	public static void main(String[] args) throws Exception {
        DefaultUnitHandler.start(new DefaultUnitHandler(new CacheStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new ProxyStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new NettyServerStudy()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new SAXBuilerStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new JaxBStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new JaxBStudy002()));
		DefaultUnitHandler.start(new DefaultUnitHandler(new HttpUrlConnectionStudy01()), new String[] {"http://dailytravel.co.kr/rss"});
//		DefaultUnitHandler.start(new String[] {"http://dailytravel.co.kr/rss"}, new DefaultUnitHandler(new RssXmlParseStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new ObserverPatternStudy()));
		// System.out.println(System.getProperty("java.version"));
	}//END OF FUNCTIION
}//END OF CLASS
