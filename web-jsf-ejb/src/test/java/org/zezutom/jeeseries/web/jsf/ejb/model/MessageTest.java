package org.zezutom.jeeseries.web.jsf.ejb.model;

import org.zezutom.jeeseries.web.jsf.ejb.model.Message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MessageTest {
    
    private Message message;
    
    @Before
    public void setUp() {
        message = new Message();
    }
    
    @Test
    public void shouldSetMessage() {
        final String expected = "Heya!";
        message.setMessage(expected);
        Assert.assertEquals(expected, message.getMessage());
    }
    
}
