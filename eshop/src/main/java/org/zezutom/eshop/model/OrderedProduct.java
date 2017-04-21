package org.zezutom.eshop.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderedProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Short quantity;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "customer_order_id")    
    private CustomerOrder customerOrder;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product_id")    
    private Product product;
}
