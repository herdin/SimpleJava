package com.harm.unit.google.guava.cache;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheRepository {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private HashMap<String, CacheTargetObject> repo;
	public final static String keyPrefix = "repokey";
	
	public CacheRepository(int repoSize) {
		repo = new HashMap<>(repoSize);
		this.initDefault(repoSize);
	}
	private void initDefault(int repoSize) {
		for(int i=0; i<repoSize; i++) {
			repo.put(keyPrefix + i, new CacheTargetObject("key"+i, "name"+i));
		}
	}
	
	public CacheTargetObject getAfterSleep(String id) {
		try {
			this.logger.debug("LOAD FROM REPOSITORY...");
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this.repo.get(id);
	}
}//END OF CLASS
