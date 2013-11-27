package com.kevintan.eventbussample;

/**
 * Created by kevintanhongann on 11/27/13.
 */
public class TestEvent {
    private TestObject mTestObject;

    public TestEvent(TestObject testObject) {
        mTestObject = testObject;
    }

    public TestObject getTestObject() {
        return mTestObject;
    }
}
