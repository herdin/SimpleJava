package com.harm.unit.rss.schema;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rss")
public class Rss {
	
	private Channel channel;

	public Channel getChannel() {
		return channel;
	}

	@XmlElement
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
}
