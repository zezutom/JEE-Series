package org.zezutom.blog.series.jee.webstruts.action;

import com.opensymphony.xwork2.Action;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterActionTest {

    private RegisterAction action;
    
    @Before
    public void setUp() {
        action = new RegisterAction();
    }
    
    @Test
    public void input_should_be_required_by_default() {
        assertEquals(Action.INPUT, action.execute());
    }
        
    @Test
    public void returned_user_should_match_the_input() {
        final User user = new User("test", "test@test.com", "Test123");
        action.setUser(user);
        assertTrue(user.equals(action.getUser()));
    }
}
