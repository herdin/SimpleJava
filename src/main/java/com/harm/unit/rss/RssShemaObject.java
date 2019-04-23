package com.harm.unit.rss;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rss")
public class RssShemaObject {
	
	private ChannelShemaObject channel;

	public ChannelShemaObject getChannel() {
		return channel;
	}

	@XmlElement
	public void setChannel(ChannelShemaObject channel) {
		this.channel = channel;
	}
	
}
