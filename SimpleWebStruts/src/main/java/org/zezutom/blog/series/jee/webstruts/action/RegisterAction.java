package org.zezutom.blog.series.jee.webstruts.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import org.apache.struts2.interceptor.validation.SkipValidation;

public class RegisterAction extends ActionSupport {
     
    private String name;
    
    private String email;
    
    private String password;

    @SkipValidation
    @Override
    public String execute() {
        return (hasAllFields()) ? SUCCESS : INPUT;
    }
    
    public User getUser() {
        return new User(name, email, password);
    }

    @RequiredStringValidator(key = "name.required")
    public void setName(String name) {
        this.name = name;
    }

    @RequiredStringValidator(key = "email.required")
    @EmailValidator(key = "email.invalid")
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Password rules:
     * #1 .{6,}         at least 6 characters
     * #2 (?=.*[0-9])   must contain at least one digit
     * #3 (?=.*[A-Z])   must contain at least one upper-case character
     * 
     * courtesy: http://stackoverflow.com/questions/3802192/regexp-java-for-password-validation
     * 
     * @param password 
     */
    @RequiredStringValidator(key = "password.required")
    @RegexFieldValidator(key = "password.rules", regexExpression = "^(?=.*[0-9])(?=.*[A-Z]).{6,}$")
    public void setPassword(String password) {
        this.password = password;
    }
    
    private boolean hasAllFields() {
        return name != null && email != null && password != null;
    }
}
