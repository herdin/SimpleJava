package com.harm.unit.pattern.observer;

import com.harm.unit.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObserverPatternStudy001 implements Unit {

	private Logger logger = LoggerFactory.getLogger(ObserverPatternStudy001.class);

	@Override
	public Object execute(Object[] obj) throws Exception {
		final int observerCnt = (Integer)obj[0];
		final int maxRandomValue = (Integer)obj[1];
		final int testCriteria = (Integer)obj[2];

		this.logger.debug("OBSERVER COUNT : {}", observerCnt);
		this.logger.debug("MAX RANDOM VALUE : {}", maxRandomValue);
		this.logger.debug("TEST CRITERIA : {}", testCriteria);
		boolean flag = true;

		/* ObservableExte extends java.util.Observable
		 * ObservableExte can change its status and notify to observer
		 * by calling Observable.setChanged() and Observable.notifyObservers(Object arg);
		 *
		 */
		ObservableExte observable = new ObservableExte();
		for(int i=0; i<observerCnt; i++) {
			observable.addObserver(new ObserverImpl());
		}
		
		while(flag) {
			int randomValue = (int) (Math.random()*maxRandomValue);
			this.logger.debug("RANDOM GET {}", randomValue);
			if(randomValue < testCriteria) {
				observable.somethingUpdate("[ OBSERVERABLE RESULT : " + randomValue);
				flag = false;
				break;
			}
			Thread.sleep(1000L);
		}

		return flag;
		
	}
}
