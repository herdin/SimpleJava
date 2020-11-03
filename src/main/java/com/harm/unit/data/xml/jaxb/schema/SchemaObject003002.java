package com.harm.unit.data.xml.jaxb.schema;

import javax.xml.bind.annotation.XmlElement;

public class SchemaObject003002 {
    @XmlElement
	private final String[] innerInnerStrings;
	public SchemaObject003002(String[] innerInnerStrings) {
	    this.innerInnerStrings = innerInnerStrings;
    }
    public String[] getInnerInnerStrings() {
        return innerInnerStrings;
    }
}
