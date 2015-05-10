package org.zezutom.blog.series.jee.ajaxrest.model;

import java.util.Objects;
import java.util.UUID;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tom
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Composer {

    private final String id;
    
    @XmlElement(name = "fname")
    private String firstName;
    
    @XmlElement(name = "lname")
    private String lastName;

    private Genre genre;

    public Composer() {
        this.id = UUID.randomUUID().toString();
    }
        
    public Composer(String firstName, String lastName, Genre category) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.genre = category;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Genre getGenre() {
        return genre;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Composer other = (Composer) obj;
        
        return Objects.equals(this.id, other.id);
    }       
}
