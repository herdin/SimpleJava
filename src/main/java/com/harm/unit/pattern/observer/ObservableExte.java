package com.harm.unit.pattern.observer;

import java.util.Observable;

public class ObservableExte extends Observable {
	public void somethingUpdate(String textInfo) {
		this.setChanged();
		this.notifyObservers(textInfo);
	}
}
