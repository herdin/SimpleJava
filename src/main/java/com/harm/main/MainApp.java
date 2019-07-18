package com.harm.main;

import com.harm.unit.DefaultUnitHandler;
import com.harm.unit.google.guava.cache.CacheStudy001;
import com.harm.unit.io.HttpUrlConnectionStudy01;
import com.harm.unit.netty.NettyServerStudy;
import com.harm.unit.parallel.sync.SyncStudy001;
import com.harm.unit.parallel.sync.SyncWithClass;
import com.harm.unit.parallel.sync.SyncWithNever;
import com.harm.unit.parallel.sync.SyncWithThis;
import com.harm.unit.pattern.observer.ObserverPatternStudy001;
import com.harm.unit.proxy.ProxyStudy001;
import com.harm.unit.rss.RssXmlParseStudy001;
import com.harm.unit.UnitRunner;
import com.harm.unit.xml.jaxb.JaxBStudy001;
import com.harm.unit.xml.jaxb.JaxBStudy002;
import com.harm.unit.xml.saxbuilder.SAXBuilerStudy001;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;

public class MainApp {
	public static Logger logger = LoggerFactory.getLogger(MainApp.class);

	public static void main(String[] args) throws Exception {
//		UnitRunner.start(new DefaultUnitHandler(new CacheStudy001()));
//		UnitRunner.start(new DefaultUnitHandler(new ProxyStudy001()));
//		UnitRunner.start(new DefaultUnitHandler(new NettyServerStudy()));
//		UnitRunner.start(new DefaultUnitHandler(new SAXBuilerStudy001()));
//		UnitRunner.start(new DefaultUnitHandler(new JaxBStudy001()));
//		UnitRunner.start(new DefaultUnitHandler(new JaxBStudy002()));
//		UnitRunner.start(new DefaultUnitHandler(new HttpUrlConnectionStudy01()), new String[] {"http://dailytravel.co.kr/rss"});
//		UnitRunner.start(new DefaultUnitHandler(new RssXmlParseStudy001()), new String[] {"http://dailytravel.co.kr/rss"});
//		UnitRunner.start(new ObserverPatternStudy001(), new Integer[]{10, 200, 100});
		UnitRunner.start(new SyncStudy001(), new Object[]{new Long[]{10L, 100L}, new SyncWithThis(true)});

	}//END OF FUNCTION
}//END OF CLASS
