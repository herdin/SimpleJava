package com.harm.unit.xml.jaxb.schema;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="message")
@XmlType(propOrder = {"messageId", "gateId", "cardId"})
public class SchemaObject002 {
	
	private String messageId;
	private String gateId;
	private String cardId;
	
	public String getMessageId() {
		return messageId;
	}
	@XmlElement
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getGateId() {
		return gateId;
	}
	@XmlElement
	public void setGateId(String gateId) {
		this.gateId = gateId;
	}
	public String getCardId() {
		return cardId;
	}
	@XmlElement
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
}
