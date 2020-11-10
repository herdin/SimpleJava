package com.harm.unit.google.guava.cache;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Preconditions;
import com.harm.unit.DefaultUnitHandler;
import com.harm.unit.UnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.harm.unit.Unit;

public class CacheStudy001 implements Unit {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Object execute(Object[] objects) throws Exception {
		Map<String, Long> paramMap = (Map<String, Long>) objects[0];

		long repoSize = paramMap.get("repoSize");
		long repoLoadMillisec = paramMap.get("repoLoadMillisec");

		long cacheSize = paramMap.get("cacheSize");
		long expiMilisec = paramMap.get("expiMilisec");

		long testRequestDurMillisec = paramMap.get("testRequestDurMillisec");
		long testMillisec = paramMap.get("testMillisec");
		long testSize = paramMap.get("testSize");
		long testLoop = paramMap.get("testLoop");

		final CacheRepository cr = new CacheRepository(repoSize);
//		Preconditions.checkNotNull(null);
		LoadingCache<String, CacheTargetObject> lc = CacheBuilder.newBuilder()
			.maximumSize(cacheSize)
//			.expireAfterAccess()
			.expireAfterWrite(expiMilisec, TimeUnit.MILLISECONDS)
			.build(
				new CacheLoader<String, CacheTargetObject>() {
					@Override
					public CacheTargetObject load(String key) throws Exception {
						return cr.getAfterSleep(key, repoLoadMillisec);
					}
				}
			);

		long fail = 0L;
		for(int i=0; i<testLoop; i++) {
			Thread.sleep(testRequestDurMillisec);
			int randomKey = (int) (Math.random()*paramMap.get("repoSize")%testSize);
			LocalDateTime before = LocalDateTime.now();
			this.logger.debug("{}/{} : randomKey : {} : lc.get : {}", i, testLoop, randomKey, lc.get(CacheRepository.keyPrefix + randomKey));
			LocalDateTime after = LocalDateTime.now();
			Duration between = Duration.between(before, after);
			this.logger.debug("{}/{} : load duration millisec : {}, before {}, after {}", i, testLoop,  between, before, after);
			if(between.get(ChronoUnit.NANOS)>testMillisec) {
				fail++;
			}
		}

		long total = paramMap.get("testLoop");
		this.logger.debug("total {} fail {}", total, fail);
		BigDecimal btotal = BigDecimal.valueOf(total);
		BigDecimal bfail = BigDecimal.valueOf(fail);
		BigDecimal successRatio = btotal.subtract(bfail).divide(btotal).multiply(BigDecimal.valueOf(100L));
		logger.debug("result -> {}", successRatio.longValue());
		return successRatio.longValue();
		
	}//END OF FUNCTION

	public static void main(String[] args) {
		Map<String, Long> paramMap = new HashMap<>();
		paramMap.put("repoSize", 100L);
		paramMap.put("repoLoadMillisec", 1000L);
		paramMap.put("cacheSize", 30L);
		paramMap.put("expiMilisec", 30*1000L);
		paramMap.put("testRequestDurMillisec", 50L);
		paramMap.put("testMillisec", 100L);
		paramMap.put("testSize", 10L);
		paramMap.put("testLoop", 1000L);
		UnitRunner.start(new CacheStudy001(), new Object[]{paramMap});
	}
}//END OF CLASS
