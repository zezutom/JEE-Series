package org.zezutom.blog.series.jee.ajaxrest.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
    
    @XmlElement(name = "fname")
    private String firstName;
    
    @XmlElement(name = "lname")
    private String lastName;

    private Genre genre;

    private String thumbnail;
    
    private Integer born;
    
    private Integer died;
    
    private List<String> masterpieces;
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getBorn() {
        return born;
    }

    public Integer getDied() {
        return died;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.firstName);
        hash = 89 * hash + Objects.hashCode(this.lastName);
        hash = 89 * hash + Objects.hashCode(this.born);
        hash = 89 * hash + Objects.hashCode(this.died);
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
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.born, other.born)) {
            return false;
        }
        if (!Objects.equals(this.died, other.died)) {
            return false;
        }
        return true;
    }

    public static class Builder {
    
        private final Composer composer = new Composer();
                
        public Builder setFirstName(String firstName) {
            composer.firstName = firstName;
            return this;
        }
        
        public Builder setLastName(String lastName) {
            composer.lastName = lastName;
            return this;
        }
        
        public Builder setBorn(Integer born) {
            composer.born = born;
            return this;
        }
        
        public Builder setDied(Integer died) {
            composer.died = died;
            return this;
        }
        
        public Builder setGenre(Genre genre) {
            composer.genre = genre;
            return this;
        }
        public Builder setThumbnail(String thumbnail) {
            composer.thumbnail = thumbnail;
            return this;
        }
        
        public Builder setMasterpieces(String... masterpieces) {
            if (masterpieces == null || masterpieces.length == 0)
                return this;
                
            if (composer.masterpieces == null)
                composer.masterpieces = new ArrayList<>();
            
            composer.masterpieces.addAll(Arrays.asList(masterpieces));
            
            return this;
        }
        public Composer build() {
            return composer;
        }
        
    }
}
