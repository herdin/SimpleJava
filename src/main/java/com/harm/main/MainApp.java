package com.harm.main;

import com.harm.unit.DefaultUnitHandler;
import com.harm.unit.rss.RssXmlParseStudy001;
public class MainApp {
	public static void main(String[] args) {
//		DefaultUnitHandler.start(new DefaultUnitHandler(new CacheStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new ProxyStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new NettyServerStudy()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new SAXBuilerStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new JaxBStudy001()));
//		DefaultUnitHandler.start(new DefaultUnitHandler(new JaxBStudy002()));
//		DefaultUnitHandler.start(new String[] {"http://dailytravel.co.kr/rss"}, new DefaultUnitHandler(new HttpUrlConnectionStudy01()));
		DefaultUnitHandler.start(new String[] {"http://dailytravel.co.kr/rss"}, new DefaultUnitHandler(new RssXmlParseStudy001()));
	}//END OF FUNCTIION
}//END OF CLASS
