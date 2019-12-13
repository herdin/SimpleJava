package com.harm.main;

import com.harm.unit.UnitRunner;
import com.harm.unit.io.network.SocketStudy0101;
import com.harm.unit.io.network.SocketStudy0102;
import com.harm.unit.leetcode.Problems01TwoSum;
import com.harm.unit.leetcode.Problems02AddTwoNumbers;
import com.harm.unit.pattern.facade.FacadeStudy001;
import com.harm.unit.recruit.kakao.elevatorgame.ElevatorGameStudy001;
import com.harm.unit.recruit.kakao.elevatorgame.ElevatorStrategyAdvancedSimple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;

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
//		UnitRunner.start(new ElevatorGameStudy001("122", ElevatorGameStudy001.PROBLEMS.RYAN, new ElevatorStrategyAdvancedSimple(), ElevatorGameStudy001.ELEVATOR_COUNT.FOUR));
//		UnitRunner.start(new FacadeStudy001());
//		UnitRunner.start(new SocketStudy0101());
//		UnitRunner.start(new SocketStudy0102());
//		UnitRunner.start(new Problems01TwoSum());
        UnitRunner.start(new Problems02AddTwoNumbers());
	}//END OF FUNCTION

}//END OF CLASS
