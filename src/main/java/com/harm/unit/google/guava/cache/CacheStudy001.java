package com.harm.unit.google.guava.cache;

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
	public void excute(Object[] objects) throws Exception {
		CacheRepository cr = new CacheRepository(100);
		
		LoadingCache<String, CacheTargetObject> lc = CacheBuilder.newBuilder()
			.maximumSize(3)
			.expireAfterWrite(20, TimeUnit.SECONDS)
			.build(
				new CacheLoader<String, CacheTargetObject>() {
					@Override
					public CacheTargetObject load(String key) throws Exception {
						return cr.getAfterSleep(key); 
					}
				}
			);
		while(true) {
			Thread.sleep(1000);
			int randomKey = (int) (Math.random()*100%5);
			this.logger.debug("randomKey : {} : lc.get : {}", randomKey, lc.get(CacheRepository.keyPrefix + randomKey));
		}
		
		
	}//END OF FUNCTION
	
}//END OF CLASS
