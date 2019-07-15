package com.harm.unit.pattern.observer;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObserverImpl implements Observer {
	private Logger logger = LoggerFactory.getLogger(ObserverImpl.class);
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof String) {
			this.logger.debug("{} : got this : {}", this.hashCode(), (String)arg);
		}
	}
}
