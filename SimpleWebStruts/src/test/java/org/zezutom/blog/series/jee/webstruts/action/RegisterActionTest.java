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
    public void incomplete_input_should_not_be_accepted() {
        action.setEmail("test@test.com");
        action.setPassword("Test123");
        assertEquals(Action.INPUT, action.execute());
    }
    
    @Test
    public void complete_input_should_be_accepted() {
        action.setName("test");
        action.setEmail("test@test.com");
        action.setPassword("Test123");        
        assertEquals(Action.SUCCESS, action.execute());    
    }
    
    @Test
    public void user_should_be_as_expected() {
        action.setName("test");
        action.setEmail("test@test.com");
        action.setPassword("Test123");        
        
        final User user = action.getUser();
        
        assertNotNull(user);
        assertEquals("test", user.getName());
        assertEquals("test@test.com", user.getEmail());
        assertEquals("Test123", user.getPassword());
    }
}
