package com.harm.unit.data.xml.jaxb.schema;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class SchemaObject003001 {
    @XmlElement
    private final String innerId;
    @XmlElement
    private final String innerName;
    @XmlAttribute
    private final String innerAlias;
    public static class Builder {
        private String innerId;
        private String innerName;
        private String innerAlias;
        public Builder innerId(String innerId) {
            this.innerId = innerId;
            return this;
        }
        public Builder innerName(String innerName) {
            this.innerName = innerName;
            return this;
        }
        public Builder innerAlias(String innerAlias) {
            this.innerAlias = innerAlias;
            return this;
        }
        public SchemaObject003001 build() {
            return new SchemaObject003001(this);
        }
    }
    private SchemaObject003001(Builder builder) {
        this.innerId = builder.innerId;
        this.innerName = builder.innerName;
        this.innerAlias = builder.innerAlias;
    }
    public String getInnerId() {
        return innerId;
    }

    public String getInnerName() {
        return innerName;
    }

    public String getInnerAlias() {
        return innerAlias;
    }
}
