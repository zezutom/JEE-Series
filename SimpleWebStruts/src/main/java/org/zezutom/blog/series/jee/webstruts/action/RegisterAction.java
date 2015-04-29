package org.zezutom.blog.series.jee.webstruts.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

public class RegisterAction extends ActionSupport {
     
    private User user;
   
    // Don't provide any message, it's supplied in the User entity
    @VisitorFieldValidator(message = "")
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public String execute() {
        return (user == null) ? INPUT : SUCCESS;
    }
        
}
