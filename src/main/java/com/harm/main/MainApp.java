package com.harm.main;

import com.harm.unit.UnitRunner;
import com.harm.unit.lang.lambda.LambdaStudy001;
import com.harm.unit.lang.parallel.SemaphoreStudy001;
import com.harm.unit.lang.parallel.executorservice.ExecutorServiceStudy001;
import com.harm.unit.lang.serialize.SerializeStudy001;
import com.harm.unit.lang.stream.StreamStudy002;
import com.harm.unit.pattern.validate.custom002.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainApp {
	public static Logger logger = LoggerFactory.getLogger(MainApp.class);
	public static void main(String[] args) throws Exception {

		logger.debug("0={}, 9={}, a={}, z={}, A={}, Z={}", (int)'0', (int)'9', (int)'a', (int)'z', (int)'A', (int)'Z');
		logger.debug("A={}, Z={}, A-64={}, Z-64={}, 0={}", (int)'A', (int)'Z', (int)'A'-64, (int)'Z'-64, (int)'0');
		logger.debug("{}", "123456".substring(0));
		logger.debug("{}", "123456".substring(0, 2));
		logger.debug("{}", "123456".substring(1, 3));
		logger.debug("{} {}", (int)10000000000L%10, (int)(10000000000L%10));
		logger.debug("{}", new BigDecimal(123.456789).setScale(0, RoundingMode.FLOOR));
		logger.debug("{}", new BigDecimal(123.456789).setScale(1, RoundingMode.FLOOR));
		logger.debug("{}", new BigDecimal(123.456789).setScale(3, RoundingMode.FLOOR));
		logger.debug("{} {}", new BigDecimal(123.456789).setScale(4, RoundingMode.FLOOR), new BigDecimal(123.456789).setScale(4, RoundingMode.FLOOR).toString());
		String target = "123456";
		logger.debug("{} -> {} ", target, target.substring(0, target.length()));
		IntStream.range(10, 3).forEach(i -> logger.debug("{}", i));


//		UnitRunner.start(new DefaultUniExecutorServiceStudy001tHandler(new ProxyStudy001()));
//		UnitRunner.start(new DefaultUnitHandler(new NettyServerStudy()));
//		UnitRunner.start(new DefaultUnitHandler(new SAXBuilerStudy001()));
//		new SAXBuilerStudy001().execute(null);
//		UnitRunner.start(new DefaultUnitHandler(new JaxBStudy001()));
//		UnitRunner.start(new DefaultUnitHandler(new JaxBStudy002()));
//		UnitRunner.start(new DefaultUnitHandler(new HttpUrlConnectionStudy01()), new String[] {"https://rss.blog.naver.com/dailytravel12.xml"});
//		UnitRunner.start(new DefaultUnitHandler(new RssXmlParseStudy001()), new String[] {"https://rss.blog.naver.com/dailytravel12.xml"});
//		UnitRunner.start(new ObserverPatternStudy001(), new Integer[]{10, 200, 100});
//		UnitRunner.start(new SyncStudy001(), new Object[]{new Long[]{10L, 100L}, SyncWithThis.class, Boolean.TRUE});
//		UnitRunner.start(new ElevatorGameStudy001("122", ElevatorGameStudy001.PROBLEMS.RYAN, new ElevatorStrategyAdvancedSimple(), ElevatorGameStudy001.ELEVATOR_COUNT.FOUR));
//		UnitRunner.start(new FacadeStudy001());
//		UnitRunner.start(new SocketStudy0101());
//		UnitRunner.start(new SocketStudy0102());
//		UnitRunner.start(new Problems01TwoSum());
//		UnitRunner.start(new Problems02AddTwoNumbers());
//		UnitRunner.start(new ForkJoinStudyRecursiveTask001());
//		UnitRunner.start(new ReferenceStudy001());
//		UnitRunner.start(new LocalDateTimeStudy001());
//		UnitRunner.start(new ExecutorServiceStudy001());
//		UnitRunner.start(new ValidatePatternStudy001());
//		UnitRunner.start(new LambdaStudy001());
//		UnitRunner.start(new InnerClassStudy001());
//		UnitRunner.start(new ValidatePatternStudy002());
//		UnitRunner.start(new VolatileStudy001());
//		UnitRunner.start(new SemaphoreStudy001());
//		UnitRunner.start(new SerializeStudy001());
//		UnitRunner.start(new StreamStudy002());

//		ConcurrentLinkedQueue clq = new ConcurrentLinkedQueue();
//		ForkJoinPool
//		MainApp.logger.debug("time stamp {}", Timestamp.valueOf(LocalDateTime.now()).getTime());
//		MainApp.logger.debug("time stamp from token {}", new Date(1592196461000L));
//		MainApp.logger.debug("-> {}", "asdf".substring(2, 3));
	}//END OF FUNCTION

}//END OF CLASS
