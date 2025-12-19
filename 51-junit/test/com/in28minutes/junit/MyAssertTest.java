package com.in28minutes.junit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class MyAssertTest {

    List<String> todos = Arrays.asList("AWS", "Azure", "DevOps");

    @Test
    void test() {
        boolean test1 = todos.contains("AWS");
        boolean test2 = todos.contains("GCP");

        assertTrue(test1); // Assert test1 == true
        assertTrue(test1, "Error: AWS is missing"); // The message is printed if it fails
        assertFalse(test2); // Assert test2 == false
//        assertNull(); assertNotNull();
        assertArrayEquals(new int[] {1, 2}, new int[] {1, 2});

        assertEquals(3, todos.size());
    }
}
