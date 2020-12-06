package com.careydevelopment.contact.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Hello {

    private String hello = "world";
    private String foo = "bar";
    
    public String getHello() {
        return hello;
    }
    public void setHello(String hello) {
        this.hello = hello;
    }
    public String getFoo() {
        return foo;
    }
    public void setFoo(String foo) {
        this.foo = foo;
    }
    
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
