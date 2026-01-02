package com.in28minutes.mockito.mockito_demo.list;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    /**
     * Returns the last value every time
     */
    @Test
    void simpleTest() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(3);
        assertEquals(3, listMock.size());
        assertEquals(3, listMock.size());
        assertEquals(3, listMock.size());
    }

    /**
     * Here, multiple thenReturn() is used.
     * First call returns 1
     * All calls after that return the last value assigned, which is 2
     */
    @Test
    void multipleReturns() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(1).thenReturn(2);
        assertEquals(1, listMock.size());
        assertEquals(2, listMock.size());
        assertEquals(2, listMock.size());
        assertEquals(2, listMock.size());
    }

    /**
     * Since, only the value for get(0) is provided,
     * so, for get(1) it returns null as the initial object was a String
     */
    @Test
    void specificParameters() {
        List listMock = mock(List.class);
        when(listMock.get(0)).thenReturn("SomeString");
        assertEquals("SomeString", listMock.get(0));
        assertEquals(null, listMock.get(1));
    }

    /**
     * Mockito.anyInt() - So it will return the same string for any integer value passed as a parameter
     */
    @Test
    void genericParameters() {
        List listMock = mock(List.class);
        when(listMock.get(Mockito.anyInt())).thenReturn("SomeOtherString");
        assertEquals("SomeOtherString", listMock.get(0));
        assertEquals("SomeOtherString", listMock.get(1));
    }
}
