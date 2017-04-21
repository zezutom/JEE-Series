package org.zezutom.eshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_name")
    private String name;
    
    private BigDecimal price;
    
    private String description;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "last_updated")
    private Date lastUpdated;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "category_id")    
    private Category category;
    
    @OneToMany(mappedBy = "product")
    private List<OrderedProduct> orderedProducts;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

}
