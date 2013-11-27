package com.kevintan.eventbussample;

/**
 * Created by kevintanhongann on 11/27/13.
 */
public class TestObject {

    private String name;

    private String email;

    public TestObject(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
