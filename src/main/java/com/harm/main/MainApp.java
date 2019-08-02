package com.harm.main;

import com.harm.unit.UnitRunner;
import com.harm.unit.recruit.kakao.elevatorgame.ElevatorGameStudy001;
import com.harm.unit.recruit.kakao.elevatorgame.ElevatorGameStudy001.ELEVATOR_COUNT;
import com.harm.unit.recruit.kakao.elevatorgame.ElevatorGameStudy001.PROBLEMS;
import com.harm.unit.recruit.kakao.elevatorgame.SimpleElevatorStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
//		UnitRunner.start(new SyncStudy001(), new Object[]{new Long[]{10L, 100L}, SyncWithThis.class, Boolean.TRUE});
		UnitRunner.start(new ElevatorGameStudy001(PROBLEMS.APEACH, new SimpleElevatorStrategy(), ELEVATOR_COUNT.ONE));
//		MainApp.logger.debug("ordinal {}", ElevatorGameStudy001.PROBLEMS.JAY_G.ordinal());

	}//END OF FUNCTION
}//END OF CLASS
