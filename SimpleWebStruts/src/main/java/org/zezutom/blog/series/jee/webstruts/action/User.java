package org.zezutom.blog.series.jee.webstruts.action;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import java.util.Objects;

public class User {

    private String name;
    
    private String email;
    
    private String password;

    public User() {
    }
    
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    @RequiredStringValidator(key = "name.required")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @RequiredStringValidator(key = "email.required")
    @EmailValidator(key = "email.invalid")    
    public String getEmail() {
        return email;
    }

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
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }        
}
