package com.example.leejunbeom.test;

import com.example.leejunbeom.bookMarker.A;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by leejunbeom on 16. 3. 11..
 */
public class ATest {

    @Test
    public void logTest() {
        A a = new A();
        A.log("string");
       // assertEquals(A.getLog(), "string");
    }
}
