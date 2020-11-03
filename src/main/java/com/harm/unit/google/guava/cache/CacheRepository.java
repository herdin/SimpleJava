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
		initDefault(repoSize);
	}
	private void initDefault(long repoSize) {
		for(int i=0; i<repoSize; i++) {
			repo.put(keyPrefix + i, new CacheTargetObject("id"+i, "name"+i));
		}
		logger.debug("REPO LIST START");
		for (String s : repo.keySet()) {
			logger.debug("KEY[{}] ID[{}] NAME[{}]", s, repo.get(s).getId(), repo.get(s).getName());
		}
		logger.debug("REPO LIST END");
	}
	
	public CacheTargetObject getAfterSleep(String id, long sleepMilisec) {
		try {
			logger.debug("LOAD START FROM REPOSITORY...[{}]", id);
			Thread.sleep(sleepMilisec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.debug("LOAD END FROM REPOSITORY...[{}]", id);
		return repo.get(id);
	}
}//END OF CLASS
