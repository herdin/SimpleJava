package com.harm.unit.xml.jaxb.schema;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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
