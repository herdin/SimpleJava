package com.harm.unit.xml.jaxb.schema;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="conf")
@XmlType(propOrder = {"id", "name", "alias", "innerObject01", "innerObject02"})
public class SchemaObject003 {

	@XmlElement
	private final String id;
	@XmlElement
	private final String name;
	@XmlAttribute
	private final String alias;
	@XmlElement
	private final SchemaObject003001 innerObject01;
	@XmlElement
	private final SchemaObject003002 innerObject02;

	public static class Builder {
		private String id;
		private String name;
		private String alias;
		private SchemaObject003001 innerObject01;
		private SchemaObject003002 innerObject02;
		public Builder id(String id) {
			this.id = id;
			return this;
		}
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public Builder alias(String alias) {
			this.alias = alias;
			return this;
		}
		public Builder innerObject01(SchemaObject003001 innerObject01) {
			this.innerObject01 = innerObject01;
			return this;
		}
		public Builder innerOBject02(SchemaObject003002 innerObject02) {
			this.innerObject02 = innerObject02;
			return this;
		}
		public SchemaObject003 build() {
			return new SchemaObject003(this);
		}
	}
	public SchemaObject003() {
		this.id = null;
		this.name = null;
		this.alias = null;
		this.innerObject01 = null;
		this.innerObject02 = null;
	}
	private SchemaObject003(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.alias = builder.alias;
		this.innerObject01 = builder.innerObject01;
		this.innerObject02 = builder.innerObject02;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAlias() {
		return alias;
	}

	public SchemaObject003001 getInnerObject01() {
		return innerObject01;
	}

	public SchemaObject003002 getInnerObject02() {
		return innerObject02;
	}
}
