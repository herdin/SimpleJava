package com.harm.unit.google.guava.cache;

import com.google.common.base.MoreObjects;

public class CacheTargetObject {
	
	private String id;
	private String name;
	
	public CacheTargetObject(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", this.id)
			.add("name", this.name)
			.toString();
	}
}
