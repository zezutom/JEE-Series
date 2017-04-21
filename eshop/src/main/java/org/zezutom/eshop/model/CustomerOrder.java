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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class CustomerOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private BigDecimal amount;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date created;
    
    @Column(name = "confirmation_number")
    private Integer confirmationNumber;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "customer_id")    
    private Customer customer;
    
    @OneToMany(mappedBy = "customerOrder")
    private List<OrderedProduct> orderedProducts;    
    
}
