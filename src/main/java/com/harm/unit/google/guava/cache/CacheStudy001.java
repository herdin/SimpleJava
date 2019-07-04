package com.harm.unit.google.guava.cache;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

		LoadingCache<String, CacheTargetObject> lc = CacheBuilder.newBuilder()
			.maximumSize(cacheSize)
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
			long before = System.currentTimeMillis();
			this.logger.debug("randomKey : {} : lc.get : {}", randomKey, lc.get(CacheRepository.keyPrefix + randomKey));
			long after = System.currentTimeMillis();
			this.logger.debug("load duration millisec : {}, before {}, after {}", (after-before), before, after);
			if((after-before)>testMillisec) {
				fail++;
			}
		}

		long total = paramMap.get("testLoop");
		this.logger.debug("total {} fail {}", total, fail);
		BigDecimal btotal = BigDecimal.valueOf(total);
		BigDecimal bfail = BigDecimal.valueOf(fail);
		BigDecimal successRatio = btotal.subtract(bfail).divide(btotal).multiply(BigDecimal.valueOf(100L));

		return successRatio.longValue();
		
	}//END OF FUNCTION
	
}//END OF CLASS
