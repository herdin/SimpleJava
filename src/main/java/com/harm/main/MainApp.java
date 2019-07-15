package com.harm.main;

import com.harm.unit.DefaultUnitHandler;
import com.harm.unit.pattern.observer.ObserverPatternStudy001;
import com.harm.unit.xml.UnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp {
	public static Logger logger = LoggerFactory.getLogger(MainApp.class);

	public static void main(String[] args) throws Exception {
//        DefaultUnitHandler.start(new DefaultUnitHandler(new CacheStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new ProxyStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new NettyServerStudy()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new SAXBuilerStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new JaxBStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new JaxBStudy002()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new HttpUrlConnectionStudy01()), new String[] {"http://dailytravel.co.kr/rss"});
//		DefaultUnitHandler.start(new String[] {"http://dailytravel.co.kr/rss"}, new DefaultUnitHandler(new RssXmlParseStudy001()));
		UnitRunner.start(new DefaultUnitHandler(new ObserverPatternStudy001()), new Integer[]{10, 200, 100});
		// System.out.println(System.getProperty("java.version"));

	}//END OF FUNCTION
}//END OF CLASS
