package com.example.cst438_01_wk01hw02solo;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MainActivityTest {

    ArrayList<User> testUserList = new ArrayList<>();

    @Before
    public void testSetUp(){
        testUserList.add(new User("thisIsATest", "onlyATest", 666));
    }

    @Test
    public void verifyUN() {
        assertNotNull(MainActivity.verifyUN(testUserList, "thisIsATest"));
        assertNull(MainActivity.verifyUN(testUserList, "BoundToFail"));
    }

    @Test
    public void verifyPW() {
        assertTrue(MainActivity.verifyPW(testUserList.get(0), "onlyATest"));
        assertFalse(MainActivity.verifyPW(testUserList.get(0), "BoundToFail"));
    }
}