package com.kevintan.eventbussample.data;

/**
 * Created by kevintanhongann on 11/27/13.
 *
 * <p/>
 * Update to move to package {@code com.kevintan.eventbussample.bus} and renamed.
 * <p/>
 * @author Xinyue Zhao
 */
public class DataObject {

    private String name;

    private String email;

    public DataObject(String name, String email) {
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
