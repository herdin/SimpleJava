package com.harm.unit.google.guava.cache;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheRepository {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private HashMap<String, CacheTargetObject> repo;
	public final static String keyPrefix = "repokey";
	
	public CacheRepository(long repoSize) {
		repo = new HashMap<>((int)repoSize);
		this.initDefault(repoSize);
	}
	private void initDefault(long repoSize) {
		for(int i=0; i<repoSize; i++) {
			repo.put(keyPrefix + i, new CacheTargetObject("id"+i, "name"+i));
		}
		this.logger.debug("REPO LIST START");
		for (String s : repo.keySet()) {
			this.logger.debug("KEY[{}] ID[{}] NAME[{}]", s, repo.get(s).getId(), repo.get(s).getName());
		}
		this.logger.debug("REPO LIST END");
	}
	
	public CacheTargetObject getAfterSleep(String id, long sleepSec) {
		try {
			this.logger.debug("LOAD FROM REPOSITORY...[{}]", id);
			Thread.sleep(sleepSec*1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this.repo.get(id);
	}
}//END OF CLASS
