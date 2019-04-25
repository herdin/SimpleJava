package com.harm.unit.rss.schema;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Channel {
	
	private List<Item> items;

	public List<Item> getItems() {
		return items;
	}

	@XmlElement(name="item")
	public void setItems(List<Item> items) {
		this.items = items;
	}
}
