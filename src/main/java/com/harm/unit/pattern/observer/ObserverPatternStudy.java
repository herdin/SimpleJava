package com.harm.unit.pattern.observer;

import com.harm.unit.Unit;

public class ObserverPatternStudy implements Unit {
	@Override
	public Object execute(Object[] obj) throws Exception {
		boolean flag = true;
		final int observerCnt = 10;
		ObservableExte observable = new ObservableExte();
		for(int i=0; i<observerCnt; i++) {
			new ObserverImpl(observable);
		}
		
		while(flag) {
			int randomUnder100 = (int) (Math.random()*100);
			if(randomUnder100 < 30) {
				observable.somethingUpdate("[" + randomUnder100 + " GOT UNDER 30]");
				Thread.sleep(5000L);
			}
			Thread.sleep(1000L);
		}

		return null;
		
	}
}
