package com.harm.unit.lang.innerclass;

public class OuterClass {
    final String name;
    public OuterClass(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public class InnerClass {
        final String name;
        public InnerClass(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }
    public static class InnerStaticClass {
        final String name;
        public InnerStaticClass(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }
}
