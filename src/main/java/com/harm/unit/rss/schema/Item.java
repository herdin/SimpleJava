package com.harm.unit.rss.schema;

import javax.xml.bind.annotation.XmlElement;

public class Item {
	private String author;
	private String category;
	private String title;
	private String link;
	private String guid;
	private String description;
	private String pubDate;
	private String tag;
	public String getAuthor() {
		return author;
	}
	public String getCategory() {
		return category;
	}
	public String getTitle() {
		return title;
	}
	public String getLink() {
		return link;
	}
	public String getGuid() {
		return guid;
	}
	public String getDescription() {
		return description;
	}
	public String getPubDate() {
		return pubDate;
	}
	public String getTag() {
		return tag;
	}
	@XmlElement
	public void setAuthor(String author) {
		this.author = author;
	}
	@XmlElement
	public void setCategory(String category) {
		this.category = category;
	}
	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}
	@XmlElement
	public void setLink(String link) {
		this.link = link;
	}
	@XmlElement
	public void setGuid(String guid) {
		this.guid = guid;
	}
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}
	@XmlElement
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	@XmlElement
	public void setTag(String tag) {
		this.tag = tag;
	}
}
