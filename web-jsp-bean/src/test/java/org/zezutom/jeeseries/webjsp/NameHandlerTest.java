package org.zezutom.jeeseries.webjsp;

import org.zezutom.jeeseries.webjsp.NameHandler;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NameHandlerTest {

    private NameHandler nameHandler;

    @Before
    public void setUp() {
        nameHandler = new NameHandler();
    }

    @Test
    public void shouldSetName() {
        String expected = "test";
        nameHandler.setName(expected);
        assertEquals(expected, nameHandler.getName());
    }
}
