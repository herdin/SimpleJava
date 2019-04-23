package com.harm.unit.rss;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class ChannelShemaObject {
	
	private List<ItemShemaObject> items;

	public List<ItemShemaObject> getItems() {
		return items;
	}

	@XmlElement(name="item")
	public void setItems(List<ItemShemaObject> items) {
		this.items = items;
	}
}
